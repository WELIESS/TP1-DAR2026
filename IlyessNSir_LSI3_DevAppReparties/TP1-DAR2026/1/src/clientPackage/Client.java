package clientPackage;
 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class Client {
 
    // A MODIFIER si le serveur est sur une autre machine
    static final String IP_SERVEUR = "192.168.1.12";
    static final int PORT = 1234;
 
    public static void main(String[] args) {
        // Syntaxe valide : entier, opérateur (+ - * /), entier   Ex : 55 * 25
        Pattern syntaxe = Pattern.compile("^\\s*(-?\\d+)\\s*([+\\-*/])\\s*(-?\\d+)\\s*$");
 
        try {
            System.out.println("Je suis un client pas encore connecté...");
            Socket socket = new Socket(IP_SERVEUR, PORT);
            System.out.println("je suis un client connecté");
 
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
 
            while (true) {
                System.out.print("Entrez une opération (ex : 55 * 25) ou 'q' pour quitter : ");
                String ligne = sc.nextLine().trim();
 
                if (ligne.equalsIgnoreCase("q")) {
                    out.writeBoolean(false);            // prévient le serveur de l'arrêt
                    break;
                }
 
                // Validation de la syntaxe AVANT l'envoi au serveur
                Matcher m = syntaxe.matcher(ligne);
                if (!m.matches()) {
                    System.out.println("Syntaxe invalide. Format attendu : entier opérateur entier (+ - * /)");
                    continue;
                }
 
                int a, b;
                try {
                    a = Integer.parseInt(m.group(1));
                    b = Integer.parseInt(m.group(3));
                } catch (NumberFormatException e) {
                    System.out.println("Nombre trop grand.");
                    continue;
                }
                char op = m.group(2).charAt(0);
 
                out.writeBoolean(true);                 // une opération arrive
                out.writeInt(a);                        // premier opérande
                out.writeChar(op);                      // opérateur
                out.writeInt(b);                        // deuxième opérande
 
                System.out.println(in.readUTF());       // lecture et affichage du résultat
            }
 
            System.out.println("Fin de la communication");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}