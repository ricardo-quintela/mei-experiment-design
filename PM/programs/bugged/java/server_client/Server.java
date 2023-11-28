package server_client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 8080;
    private static final Map<String, Product> storeInventory = new HashMap<>();

    public static void main(String[] args) {
        storeInventory.put("Laptop", new Product("Laptop", 1200.0, 10));
        storeInventory.put("Smartphone", new Product("Smartphone", 800.0, 20));
        storeInventory.put("Headphones", new Product("Headphones", 100.0, 30));

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Store Server listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection from " + clientSocket.getInetAddress());

                // Handle client request using a new thread
                new Thread(() -> handleClientRequest(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClientRequest(Socket clientSocket) {
        try (
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
            String requestType = (String) in.readObject();
            switch (requestType) {
                case "GET":
                    // Read the product ID sent by the client
                    int productId = (int) in.readObject();
                    System.out.println("Received GET request for product ID: " + productId);
                    Product product = storeInventory.getOrDefault(productId, new Product("Not found", 0.0, 0));
                    out.writeObject(product);
                    break;
                case "UPDATE":
                    // Read the product sent by the client for update
                    Product updatedProduct = (Product) in.readObject();
                    System.out.println("Received UPDATE request for product ID: " + updatedProduct.getId());
                    updateProduct(updatedProduct);
                    break;
            }

            // Close the connection
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void updateProduct(Product updatedProduct) {
        String productName = updatedProduct.getName();
        if (storeInventory.containsKey(productName)) {
            storeInventory.put(productName, updatedProduct);
            System.out.println("Product updated: " + updatedProduct);
        } else {
            System.out.println("Product not found: " + productName);
        }
    }
}
