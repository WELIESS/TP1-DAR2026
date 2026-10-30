package serverPackage;
 
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import modelPackage.Operation;
 
// Un thread = la communication indépendante avec UN client
public class Clientprocess extends Thread {
 
    private final Socket socket;
    private final int numero;
    private final Compteur compteur;
 
    public Clientprocess(Socket socket, int numero, Compteur compteur) {
        this.socket = socket;
        this.numero = numero;
        this.compteur = compteur;
    }
 
    @Override
    public void run() {
        try {
            // ObjectOutputStream d'abord (avec flush), puis ObjectInputStream
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
 
            out.writeObject("Bienvenue ! Vous etes le client n°" + numero);
            out.flush();
 
            while (true) {
                Object recu = in.readObject();
                if (recu instanceof String) {            // "FIN" : le client arrête
                    break;
                }
                Operation op = (Operation) recu;
 
                String reponse = calculer(op.getOperande1(), op.getOperateur(), op.getOperande2());
                System.out.println("Client n°" + numero + " : " + op + " -> " + reponse);
 
                // accès concurrent : incrémentation synchronisée du compteur global
                int total = compteur.incrementerEtAfficher(numero, op.toString());
 
                out.writeObject(reponse + "   (opérations traitées par le serveur : " + total + ")");
                out.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Connexion perdue avec le client n°" + numero);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                // rien à faire
            }
            System.out.println("Client n°" + numero + " déconnecté");
        }
    }
 
    private static String calculer(int a, char op, int b) {
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