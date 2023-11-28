package server_client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            // Ask the server for information about a product
            String requestType = "GET";
            out.writeObject(requestType);

            int productId = 1; // Replace with the actual product ID
            out.writeObject(productId);

            // Receive and print the server's response
            Product product = (Product) in.readObject();
            System.out.println("Server response (GET): " + product);

            // Update the quantity of a product
            requestType = "UPDATE";
            out.writeObject(requestType);

            // Replace with the actual product details
            Product updatedProduct = new Product("Not important", 0.0, 5);
            out.writeObject(updatedProduct);

            // Receive and print the server's response
            product = (Product) in.readObject();
            System.out.println("Server response (UPDATE): " + product);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
