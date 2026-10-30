package clientPackage;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
 
public class Client {
 
    // Test local : "localhost". Test en réseau : adresse IP de la machine serveur
    static final String IP_SERVEUR = "192.168.1.12";
    static final int PORT = 1234;
 
    public static void main(String[] args) {
        try {
            System.out.println("Je suis un client pas encore connecté...");
            InetAddress adresse = InetAddress.getByName(IP_SERVEUR);
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(adresse, PORT), 5000);
            System.out.println("je suis un client connecté à " + IP_SERVEUR + ":" + PORT);
 
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);
 
            // Message d'accueil avec le numéro d'ordre (2 lignes)
            System.out.println(in.readLine());
            System.out.println(in.readLine());
 
            while (true) {
                System.out.print("> ");
                String message = sc.nextLine();
                out.println(message);
                if (message.trim().equalsIgnoreCase("quit")) {
                    break;
                }
                System.out.println(in.readLine());
            }
 
            System.out.println("Fin de la communication");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}