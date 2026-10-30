package serverPackage;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
 
// Un objet ClientHandler = un thread qui s'occupe d'UN client
public class ClientHandler extends Thread {
 
    private final Socket socket;
    private final int numero;
 
    public ClientHandler(Socket socket, int numero) {
        this.socket = socket;
        this.numero = numero;
    }
 
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
 
            // c) envoi au client de son numéro d'ordre
            out.println("Bienvenue ! Vous etes le client n°" + numero);
            out.println("Tapez un message (ou 'quit' pour quitter).");
 
            String ligne;
            while ((ligne = in.readLine()) != null) {
                if (ligne.trim().equalsIgnoreCase("quit")) {
                    break;
                }
                System.out.println("Client n°" + numero + " dit : " + ligne);
                out.println("Serveur -> client n°" + numero + " : " + ligne);
            }
        } catch (IOException e) {
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
}