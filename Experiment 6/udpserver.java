import java.net.*;

public class udpserver {

    public static void main(String args[]) throws Exception {

        // Create a UDP socket on port 9876
        DatagramSocket socket = new DatagramSocket(9876);

        // Create a byte array to receive client data
        byte[] receiveData = new byte[1024];

        System.out.println("Server Started...");

        // Create an empty packet to receive data
        DatagramPacket receivePacket =
                new DatagramPacket(receiveData, receiveData.length);

        // Wait for a message from the client
        socket.receive(receivePacket);

        // Convert received bytes into String
        String sentence = new String(
                receivePacket.getData(),
                0,
                receivePacket.getLength()
        );

        System.out.println("Received: " + sentence);

        // Replace abbreviations with their full forms
        sentence = sentence.replace(" tbh", "to be honest");
        sentence = sentence.replace(" ig", "I guess");
        sentence = sentence.replace(" tbf", "to be fair");
        sentence = sentence.replace(" atm", "at the moment");
        sentence = sentence.replace(" irl", "in real life");
        sentence = sentence.replace(" lol", "laughing out loud");
        sentence = sentence.replace(" asap", "as soon as possible");
        sentence = sentence.replace(" omg", "oh my God");
        sentence = sentence.replace(" ttyl", "talk to you later");
        sentence = sentence.replace(" idk", "I don't know");
        sentence = sentence.replace(" nvm", "never mind");
        sentence = sentence.replace(" idc", "I don't care");

        // Convert translated sentence into bytes
        byte[] sendData = sentence.getBytes();

        // Create packet to send response to the client
        DatagramPacket sendPacket =
                new DatagramPacket(
                        sendData,
                        sendData.length,
                        receivePacket.getAddress(),
                        receivePacket.getPort()
                );

        // Send translated sentence
        socket.send(sendPacket);

        System.out.println("Translation Sent.");

        // Close socket
        socket.close();
    }
}

