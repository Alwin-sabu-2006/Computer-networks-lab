import java.io.*;
import java.net.*;
import java.util.Scanner;

public class clent5 {

    public static void main(String[] args) throws Exception {

        // Connect to server
        Socket socket = new Socket("localhost", 5000);

        System.out.println("Connected to chat server.");

        // Receive messages from server
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        // Send messages to server
        PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);

        // Read keyboard input
        Scanner sc = new Scanner(System.in);

        // Get user's name
        System.out.print("Enter your name: ");

        String name = sc.nextLine();

        // Send name to server
        out.println(name);

        // Thread for receiving messages
        Thread receive = new Thread(() -> {

            try {

                String msg;

                while ((msg = in.readLine()) != null) {

                    System.out.println(msg);
                }

            } catch (Exception e) {

                System.out.println("Disconnected from server.");
            }
        });

        // Start receiving thread
        receive.start();

        // Send messages
        while (true) {

            String message = sc.nextLine();

            out.println(message);
        }
    }
}

