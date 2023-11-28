# pylint: skip-file
"""Contains functions to calculate the distance between two colors based on the
delta E distance formula from CIE 2000
"""

from numpy import sqrt, cbrt, sin, cos, arctan2, ndarray, radians, degrees, power, exp, array, matmul

def ciede2000_distance(color1: ndarray, color2: ndarray) -> float:
    """Calculates the cie2000 delta E distance in the CIELAB color space

    Args:
        color1 (ndarray): the first color in the CIELAB colorspace
        color2 (ndarray): the second color in the CIELAB colorspace

    Returns:
        float: the delta E distance between the two colors
    """

    delta_L_dash = color2[0] - color1[0]
    median_L = (color1[0] + color2[0]) / 2
    
    C1 = sqrt(power(color1[1], 2) + power(color1[2], 2))
    C2 = sqrt(power(color2[1], 2) + power(color2[2], 2))
    median_C = (C1 + C2) / 2

    median_C7 = power(median_C, 7)
    a1_const = 1 - sqrt(median_C7 / (median_C7 + power(25, 7)))

    a1_dash = color1[1] + (color1[1] / 2) * a1_const
    a2_dash = color2[1] + (color2[1] / 2) * a1_const

    C1_dash = sqrt(power(a1_dash, 2) + power(color1[2], 2))
    C2_dash = sqrt(power(a2_dash, 2) + power(color2[2], 2))

    delta_C_dash = C2_dash - C1_dash

    median_C_dash = (C1_dash + C2_dash) / 2

    h1_dash = degrees(arctan2(color1[2], a1_dash)) % 360
    h2_dash = degrees(arctan2(color2[2], a2_dash)) % 360

    h_dash_abs = abs(h1_dash - h2_dash)

    if h_dash_abs <= 180:
        delta_h_dash = h2_dash - h1_dash
    elif h_dash_abs > 180 and h2_dash <= h1_dash:
        delta_h_dash = h2_dash - h1_dash + 360
    elif h_dash_abs > 180 and h2_dash > h1_dash:
        delta_h_dash = h2_dash - h1_dash - 360

    delta_H_dash = 2 * sqrt(C1_dash * C2_dash) * sin(radians(delta_h_dash) / 2)

    if h1_dash == 0 or h2_dash == 0:
        median_H_dash = h1_dash + h2_dash

    else:
        if h_dash_abs <= 180:
            median_H_dash = (h1_dash + h2_dash) / 2
        elif h_dash_abs > 180 and h1_dash + h2_dash < 360:
            median_H_dash = (h1_dash + h2_dash + 360) / 2
        elif h_dash_abs > 180 and h1_dash + h2_dash >= 360:
            median_H_dash = (h1_dash + h2_dash - 360) / 2


    T = 1 - 0.17 * cos(radians(median_H_dash - 30)) + 0.24 * cos(radians(2 * median_H_dash)) + 0.32 * cos(radians(3 * median_H_dash + 6)) - 0.20 * cos(radians(4 * median_H_dash - 63))

    S_L = 1 + (0.015 * power(median_L - 50, 2)) / sqrt(20 + power(median_L - 50, 2))
    S_C = 1 + 0.045 * median_C_dash
    S_H = 1 + 0.015 * median_C_dash * T

    median_C_dash7 = power(median_C_dash, 7)

    R_T = -2 * sqrt(median_C_dash7 / (median_C_dash7 + power(25, 7))) * sin(radians(60) * exp(-power((median_H_dash - 275) / 25, 2)))


    delta_e_00 = sqrt(
    
        power(delta_L_dash / S_L, 2) + 
        power(delta_C_dash / S_C, 2) + 
        power(delta_H_dash / S_H, 2) + 
        (R_T * (delta_C_dash / S_C) * (delta_H_dash / S_H))

    )

    return delta_e_00


# ================================ RGB TO LAB ================================

def f(t: float) -> float:
    if t > 0.008856:
        return cbrt(t)
    return (((29/3)**3 * t) + 16) / 116

def rgb_to_lab(rgb_color: ndarray) -> ndarray:
    """Converts a color from the rgb [0,255] color space to the
    CIELAB [0,1] color space

    Args:
        rgb_color (ndarray): the RGB color [0, 255]

    Returns:
        ndarray: the CIELAB color [0,1]
    """

    # normalize between 0 and 1
    rgb_normalized = rgb_color / 255

    # apply color corrections if any of the values is
    # greater than 0.04045
    rgb_normalized[rgb_normalized > 0.04045] = power(((rgb_normalized[rgb_normalized > 0.04045] + 0.055) / 1.055), 2.4)

    xyz_matrix = array([[0.4124564, 0.3575761, 0.1804375], [0.2126729, 0.7151522, 0.0721750], [0.0193339, 0.1191920, 0.9503041]])

    xyz_color = matmul(xyz_matrix, rgb_normalized)

    L = 116 * f(xyz_color[1]) - 16
    a = 500 * (f(xyz_color[0]) - f(xyz_color[1]))
    b = 200 * (f(xyz_color[1]) - f(xyz_color[2]))

    return array([L,a,b])