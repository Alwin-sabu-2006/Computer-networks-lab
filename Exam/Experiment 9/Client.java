
import java.io.*;
import java.net.*;
import java.util.*;

class FileClient {
    public static void main(String args[]) throws Exception {
        Socket s = new Socket("localhost", 5000);

        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));

        PrintWriter out = new PrintWriter(
            s.getOutputStream(), true);

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter filename: ");
        String name = sc.nextLine();

        out.println(name);

        String line;
        while ((line = in.readLine()) != null) {
            if (line.equals("EOF"))
                break;

            System.out.println(line);
        }

        s.close();
        sc.close();
    }
}
