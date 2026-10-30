package clientPackage;
 
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import modelPackage.Operation;
 
public class Client {
 
    // A MODIFIER si le serveur est sur une autre machine
    static final String IP_SERVEUR = "192.168.1.12";
    static final int PORT = 1234;
 
    public static void main(String[] args) {
        Pattern syntaxe = Pattern.compile("^\\s*(-?\\d+)\\s*([+\\-*/])\\s*(-?\\d+)\\s*$");
 
        try {
            System.out.println("Je suis un client pas encore connecté...");
            Socket socket = new Socket(IP_SERVEUR, PORT);
            System.out.println("je suis un client connecté");
 
            // Important : ObjectOutputStream d'abord (puis flush), ensuite ObjectInputStream
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);
 
            while (true) {
                System.out.print("Entrez une opération (ex : 55 * 25) ou 'q' pour quitter : ");
                String ligne = sc.nextLine().trim();
 
                if (ligne.equalsIgnoreCase("q")) {
                    out.writeObject("FIN");
                    out.flush();
                    break;
                }
 
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
 
                // b) le client envoie UN objet qui contient les 3 éléments
                Operation operation = new Operation(a, op, b);
                out.writeObject(operation);
                out.flush();
 
                // g) lecture et affichage du résultat
                String resultat = (String) in.readObject();
                System.out.println(resultat);
            }
 
            System.out.println("Fin de la communication");
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}