import java.util.ArrayList;

class Product {
    private String name; // Product name
    private double price; // Product price
    private int quantity; // Quantity in stock of the product

    // Constructor of the Product class
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Access methods for attributes of the Product class
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Method to update the quantity of the product
    public void updateQuantity(int change) {
        // Simulating a complex update process, e.g., interacting with external systems
        // For simplicity, we just print a message here
        System.out.println("Updating quantity for " + name + "...");
        quantity += change;
        System.out.println("Quantity updated to: " + quantity);
    }

    // Intentionally complex method to make direct access difficult
    public double calculateTotalValue() {
        double discountRate = 0.1; // 10% discount for demonstration purposes
        // Simulating a complex calculation process
        return price * quantity * (1 - discountRate);
    }
}

class InventoryManager {
    ArrayList<Product> products; // List of products in inventory

    // Constructor of the InventoryManager class
    public InventoryManager() {
        this.products = new ArrayList<>();
    }

    // Method to add a product to the inventory
    public void addProduct(Product product) {
        products.add(product);
    }

    // Method to display the inventory
    public void displayInventory() {
        System.out.println("Inventory:");
        for (Product product : products) {
            System.out.println(product.getName() + " - Price: $" + product.getPrice() +
                    " - Quantity: " + product.getQuantity());
        }
        return;
    }

    public void performInventoryCheck() {
        System.out.println("Performing inventory check...");
        // Simulating a complex inventory verification process
        for (Product product : products) {
            System.out.println("Checking availability for " + product.getName() + "...");
            if (product.getQuantity() > 0) {
                System.out.println("In stock.");
            } else {
                System.out.println("Out of stock.");
            }
        }
    }
}

public class Store {

    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();

        // Adding example products to the inventory
        inventoryManager.addProduct(new Product("Laptop", 999.99, 5));
        inventoryManager.addProduct(new Product("Smartphone", 399.99, 10));
        inventoryManager.addProduct(new Product("Headphones", 79.99, 20));

        // Displaying the initial inventory
        inventoryManager.displayInventory();

        // Performing an inventory check
        inventoryManager.performInventoryCheck();

        // Updating the quantity of a product (simulation)
        String productName = "Smartphone"; // Product name to be updated
        Product selectedProduct = null;

        // Finding the product in the inventory
        for (Product product : inventoryManager.products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                selectedProduct = product;
                break;
            }
        }

        if (selectedProduct != null) {
            // Simulating a user updating the quantity
            int quantityChange = 3; // Change in quantity (positive for addition, negative for subtraction)
            selectedProduct.updateQuantity(quantityChange);

            // Displaying the updated inventory
            inventoryManager.displayInventory();

            // Calculating the total value of the updated product
            double totalValue = selectedProduct.calculateTotalValue();
            System.out.println("Total value of " + selectedProduct.getName() + ": $" + totalValue);
        } else {
            System.out.println("Product not found in the inventory.");
        }
    }
}
