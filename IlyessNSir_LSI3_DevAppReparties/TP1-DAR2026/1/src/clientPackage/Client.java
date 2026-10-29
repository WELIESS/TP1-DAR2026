package clientPackage;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // La première étape : le client n'est pas encore connecté
            System.out.println("Je suis un client pas encore connecté...");

            // La deuxième étape : connexion au serveur (adresse + port)
            Socket socket = new Socket("localhost", 1234);
            System.out.println("je suis un client connecté");

            // La dernière étape : fermer socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}