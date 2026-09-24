import java.io.*;
import java.net.*;
import java.util.*;

public class server5 {
    static Vector<ClientHandler> clients = new Vector<>();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Chat Server Started...");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("New client connected");

            ClientHandler handler = new ClientHandler(socket);
            clients.add(handler);
            handler.start();
        }
    }
}

class ClientHandler extends Thread {
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    String name;

    ClientHandler(Socket socket) throws IOException {
        this.socket = socket;

        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        out = new PrintWriter(
                socket.getOutputStream(), true);
    }

    public void run() {
        try {
            name = in.readLine();

            broadcast(name + " joined the chat");

            String message;

            while ((message = in.readLine()) != null) {
                broadcast(name + ": " + message);
            }

        } catch (Exception e) {

        } finally {
            try {
                server5.clients.remove(this);
                broadcast(name + " left the chat");
                socket.close();

            } catch (Exception e) {

            }
        }
    }

    void broadcast(String message) {
        for (ClientHandler client : server5.clients) {
            if (client != this) {
                client.out.println(message);
            }
        }
    }
}

