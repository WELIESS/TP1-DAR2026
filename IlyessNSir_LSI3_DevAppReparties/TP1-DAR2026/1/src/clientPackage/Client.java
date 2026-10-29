package clientPackage;
 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
 
public class Client {
 
    // A MODIFIER : adresse IP de la machine SERVEUR (plus de localhost)
    static final String IP_SERVEUR = "192.168.1.12";
    static final int PORT = 1234;
 
    public static void main(String[] args) {
        try {
            System.out.println("Je suis un client pas encore connecté...");
            InetAddress adresse = InetAddress.getByName(IP_SERVEUR);
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(adresse, PORT), 5000);
            System.out.println("je suis un client connecté à " + IP_SERVEUR + ":" + PORT);
 
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
 
            int choix;
            do {
                System.out.println("\n--- MENU ---");
                System.out.println("1. Addition       (x + 5)");
                System.out.println("2. Soustraction   (x - 5)");
                System.out.println("3. Multiplication (x * 5)");
                System.out.println("4. Division       (x / 5)");
                System.out.println("0. Quitter");
                System.out.print("Votre choix : ");
                choix = sc.nextInt();
 
                if (choix < 0 || choix > 4) {
                    System.out.println("Choix invalide, recommencez.");
                    continue;
                }
                out.writeInt(choix);          // envoi du choix
                if (choix != 0) {
                    System.out.print("Entrez un entier x : ");
                    int x = sc.nextInt();
                    out.writeInt(x);          // envoi de x
                    double resultat = in.readDouble();
                    System.out.println("Résultat = " + resultat);
                }
            } while (choix != 0);
 
            System.out.println("Fin de la communication");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}