import java.util.Scanner;

public class PatternPrinter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows for the pattern: ");
        int rows = scanner.nextInt();

        if (rows <= 0) {
            System.out.println("Please enter a valid number of rows.");
            scanner.close();
            return;
        }

        System.out.println("Pattern:");

        for (int i = 1; i <= rows; i++) {
            // Print spaces
            for (int j = 1; j <= rows - i; j++) {
                System.out.print("  ");
            }

            // Print numbers in increasing order
            for (int k = 1; k <= i; k++) {
                System.out.print(k + " ");
            }

            // Print numbers in decreasing order (excluding the last number)
            for (int l = i - 1; l >= 1; l--) {
                System.out.print(l + " ");
            }

            System.out.println(); // Move to the next line
        }

        scanner.close();
    }
}
