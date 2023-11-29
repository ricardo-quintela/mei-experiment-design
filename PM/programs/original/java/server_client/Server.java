package server_client;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 12345;
    private static HashMap<String, Student> studentInfo = new HashMap<>();

    public static void main(String[] args) {
        try {
            // Start the server
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is running on port " + PORT);

            // Add some sample student information to the HashMap
            studentInfo.put("John Doe", new Student("John Doe", 20, "Mathematics"));
            studentInfo.put("Alice Smith", new Student("Alice Smith", 22, "Computer Science"));
            studentInfo.put("Bob Johnson", new Student("Bob Johnson", 21, "Physics"));

            while (true) {
                // Listen for client connections
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Handle client requests in a new thread
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

            while (true) {
                // Read the operation type from the client
                String operation = (String) objectInputStream.readObject();

                switch (operation) {
                    case "GET":
                        // Read the student name from the client and send the corresponding student
                        // object
                        String studentName = (String) objectInputStream.readObject();
                        Student student = getStudent(studentName);
                        objectOutputStream.writeObject(student);
                        break;
                    case "ADD":
                        // Read the new student object from the client and add it to the HashMap
                        Student newStudent = (Student) objectInputStream.readObject();
                        addStudent(newStudent);
                        objectOutputStream.writeObject("Student added successfully");
                        break;
                    case "UPDATE":
                        // Read the updated student object from the client and update it in the HashMap
                        Student updatedStudent = (Student) objectInputStream.readObject();
                        updateStudent(updatedStudent);
                        objectOutputStream.writeObject("Student updated successfully");
                        break;
                    case "GET_ALL":
                        // Send the entire list of students to the client
                        objectOutputStream.writeObject(new HashMap<>(studentInfo));
                        break;
                    case "EXIT":
                        // Close the connection if the client requests an exit
                        System.out.println("Connection closed for client: " + clientSocket);
                        clientSocket.close();
                        return; // Exit the handleClient method
                    default:
                        // Invalid operation
                        objectOutputStream.writeObject("Invalid operation");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Student getStudent(String name) {
        return studentInfo.get(name);
    }

    private static void addStudent(Student student) {
        studentInfo.put(student.getName(), student);
    }

    private static void updateStudent(Student student) {
        studentInfo.put(student.getName(), student);
    }
}
