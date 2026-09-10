import java.net.*;
import java.util.Scanner;

public class udpclient {

    public static void main(String args[]) throws Exception {

        // Create Scanner object to read user input
        Scanner sc = new Scanner(System.in);

        // Create UDP socket
        DatagramSocket socket = new DatagramSocket();

        // Get server IP address
        InetAddress ip = InetAddress.getByName("localhost");

        // Ask user for a sentence
        System.out.print("Enter a sentence: ");

        // Read complete sentence
        String sentence = sc.nextLine();

        // Convert sentence into bytes
        byte[] sendData = sentence.getBytes();

        // Create packet for sending data to server
        DatagramPacket sendPacket =
                new DatagramPacket(
                        sendData,
                        sendData.length,
                        ip,
                        9876
                );

        // Send packet
        socket.send(sendPacket);

        // Create byte array for receiving response
        byte[] receiveData = new byte[1024];

        // Create empty packet
        DatagramPacket receivePacket =
                new DatagramPacket(receiveData, receiveData.length);

        // Wait for server response
        socket.receive(receivePacket);

        // Convert received bytes into String
        String translated = new String(
                receivePacket.getData(),
                0,
                receivePacket.getLength()
        );

        // Display translated sentence
        System.out.println("\nTranslated Sentence:");
        System.out.println(translated);

        // Close resources
        socket.close();
        sc.close();
    }
}

