package serverPackage;
 
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import modelPackage.Operation;
 
public class Server {
 
    static final int PORT = 1234;
 
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Serveur en attente sur le port " + PORT);
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connecté : " + socket.getInetAddress().getHostAddress());
 
                // Important : créer ObjectOutputStream en premier et faire flush()
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.flush();
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
 
                try {
                    while (true) {
                        Object recu = in.readObject();            // c) réception de l'objet
                        if (recu instanceof String) {             // "FIN" : le client arrête
                            System.out.println("Le client a terminé la communication");
                            break;
                        }
                        Operation op = (Operation) recu;
                        // d) récupération des données de l'objet
                        int a = op.getOperande1();
                        char o = op.getOperateur();
                        int b = op.getOperande2();
                        System.out.println("Objet reçu : " + op);
 
                        String reponse = calculer(a, o, b);       // e) calcul
                        System.out.println("Résultat envoyé : " + reponse);
                        out.writeObject(reponse);                 // f) envoi du résultat
                        out.flush();
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Connexion interrompue : " + e.getMessage());
                }
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
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