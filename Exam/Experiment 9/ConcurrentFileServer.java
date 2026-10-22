
import java.io.*;
import java.net.*;

class ConcurrentFileServer {
    public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        long pid = ProcessHandle.current().pid();

        System.out.println("Server Started");

        while (true) {
            Socket s = ss.accept();
            new ClientHandler(s, pid).start();
        }
    }
}

class ClientHandler extends Thread {
    Socket s;
    long pid;

    ClientHandler(Socket s, long pid) {
        this.s = s;
        this.pid = pid;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(s.getInputStream()));

            PrintWriter out = new PrintWriter(
                s.getOutputStream(), true);

            String name = in.readLine();

            out.println("Server PID: " + pid);
            out.println("Thread ID: " + getId());

            File f = new File(name);

            if (f.exists() && f.isFile()) {
                BufferedReader br = new BufferedReader(
                    new FileReader(f));

                String line;
                while ((line = br.readLine()) != null)
                    out.println(line);

                br.close();
            } else {
                out.println("File Not Found");
            }

            out.println("EOF");
            s.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
