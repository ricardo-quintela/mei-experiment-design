import numpy as np
from utils import rgb_to_lab, ciede2000_distance

def k_means(centroids: list, img: np.ndarray):
    """Separates the given colors into clusters using the k means algorithm\n
    
    This algorithm only used one iteration due to the centrois being the predefined rainbow colors

    Args:
        centroids (list): the list of centroid colors
        img (np.ndarray): the array of colors to process

    Returns:
        List[List[np.ndarray]]: a list of clusters with their colors
    """

    num_centroids = len(centroids)
    clusters = [list() for _ in range(num_centroids)]

    # iterate through the image pixels
    for i in range(img.shape[0]):
        for j in range(img.shape[1]):
            # initialize to the max distance possible
            min_distance = 500

            # the index of the group where the color is going to be added to
            centroid = 0

            # calculate the distance of the current color to each centroid
            for c in range(num_centroids):

                # calculate the distance between the two colors
                distance = ciede2000_distance(centroids[c], rgb_to_lab(img[i,j,:]))

                # update the new min distance and the centroid index
                if distance < min_distance:
                    min_distance = distance
                    centroid = c

            # add the color to it's respective cluster
            clusters[centroid].append(img[i,j,:])


    return clusters
