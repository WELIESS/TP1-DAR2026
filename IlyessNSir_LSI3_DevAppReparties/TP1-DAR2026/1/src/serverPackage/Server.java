package serverPackage;
 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
 
public class Server {
 
    static final int PORT = 1234;
 
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Serveur en attente sur le port " + PORT);
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connecté : " + socket.getInetAddress().getHostAddress());
 
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
 
                try {
                    while (in.readBoolean()) {          // true = une opération arrive
                        int a = in.readInt();           // premier opérande
                        char op = in.readChar();        // opérateur
                        int b = in.readInt();           // deuxième opérande
 
                        String reponse = calculer(a, op, b);
                        System.out.println(a + " " + op + " " + b + " -> " + reponse);
                        out.writeUTF(reponse);          // envoi du résultat
                    }
                    System.out.println("Le client a terminé la communication");
                } catch (IOException e) {
                    System.out.println("Connexion interrompue par le client");
                }
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    // Calcule l'opération et renvoie le texte à afficher chez le client
    static String calculer(int a, char op, int b) {
        switch (op) {
            case '+': return "Résultat = " + ((long) a + b);
            case '-': return "Résultat = " + ((long) a - b);
            case '*': return "Résultat = " + ((long) a * b);
            case '/':
                if (b == 0) return "Erreur : division par zéro";
                if (a % b == 0) return "Résultat = " + (a / b);
                return "Résultat = " + ((double) a / b);
            default:
                return "Erreur : opérateur invalide";
        }
    }
}