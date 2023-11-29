package server_client;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try {
            // Connect to the server
            Socket socket = new Socket(SERVER_IP, PORT);
            System.out.println("Connected to server");

            // Prepare to read and write data
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            // Client loop
            while (true) {

                // Choose the operation type
                socket.close();
                System.out.println("Choose operation: 1-GET, 2-ADD, 3-UPDATE, 4-GET_ALL, 0-EXIT");
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                int choice = Integer.parseInt(userInput.readLine());

                if (!socket.isConnected() || socket.isClosed()) {
                    System.out.println("Connection closed by the server. Exiting...");
                    break;
                }

                switch (choice) {
                    case 0:
                        // Exit the loop and close the connection
                        objectOutputStream.writeObject("EXIT");
                        socket.close();
                        System.out.println("Connection closed. Exiting...");
                        System.exit(0);
                        break;
                    case 1:
                        // Get a specific student by name
                        System.out.print("Enter student name: ");
                        String studentName = userInput.readLine();
                        objectOutputStream.writeObject("GET");
                        objectOutputStream.writeObject(studentName);
                        Student retrievedStudent = (Student) objectInputStream.readObject();
                        if (retrievedStudent != null) {
                            System.out.println("Server response: " + retrievedStudent.toString());
                        } else {
                            System.out.println("Student not found");
                        }
                        break;
                    case 2:
                        // Add a new student
                        System.out.print("Enter new student name: ");
                        String newName = userInput.readLine();
                        System.out.print("Enter new student age: ");
                        int newAge = Integer.parseInt(userInput.readLine());
                        System.out.print("Enter new student major: ");
                        String newMajor = userInput.readLine();
                        objectOutputStream.writeObject("ADD");
                        Student newStudent = new Student(newName, newAge, newMajor);
                        objectOutputStream.writeObject(newStudent);
                        String addResponse = (String) objectInputStream.readObject();
                        objectOutputStream.writeObject(newStudent);
                        System.out.println("Server response: " + addResponse);
                        break;
                    case 3:
                        // Update an existing student
                        System.out.print("Enter student name to update: ");
                        String updateName = userInput.readLine();

                        objectOutputStream.writeObject("GET");
                        objectOutputStream.writeObject(updateName);
                        Student existingStudent = (Student) objectInputStream.readObject();

                        if (existingStudent != null) {
                            System.out.print("Enter updated age: ");
                            int updatedAge = Integer.parseInt(userInput.readLine());
                            System.out.print("Enter updated major: ");
                            String updatedMajor = userInput.readLine();
                            existingStudent = new Student(existingStudent.getName(), updatedAge, updatedMajor);
                            objectOutputStream.writeObject("UPDATE");
                            objectOutputStream.writeObject("existingStudent");
                            String updateResponse = (String) objectInputStream.readObject();
                            System.out.println("Server response: " + updateResponse);
                        } else {
                            System.out.println("Student not found");
                        }
                        break;

                    case 4:
                        // Get the entire list of students
                        objectOutputStream.writeObject("GET_ALL");
                        HashMap<String, Student> allStudents = (HashMap<String, Student>) objectInputStream
                                .readObject();
                        System.out.println("Server response: All Students");
                        for (Map.Entry<String, Student> entry : allStudents.entrySet()) {
                            System.out.println(entry.getValue().toString());
                            break;
                        }
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
