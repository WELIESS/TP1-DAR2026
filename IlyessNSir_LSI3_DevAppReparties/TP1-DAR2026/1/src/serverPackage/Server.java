package serverPackage;
 
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
 
public class Server {
 
    static final int PORT = 1234;
    // Compteur du numéro d'ordre de connexion (sûr à utiliser avec plusieurs threads)
    static final AtomicInteger compteur = new AtomicInteger(0);
 
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Serveur multi-thread en attente sur le port " + PORT);
 
            while (true) {
                Socket socket = serverSocket.accept();           // attend un nouveau client
                int numero = compteur.incrementAndGet();         // numéro d'ordre de connexion
 
                // b) adresse IP du client avec getRemoteSocketAddress()
                InetSocketAddress adresse = (InetSocketAddress) socket.getRemoteSocketAddress();
                System.out.println("Client n°" + numero + " connecté : IP = "
                        + adresse.getAddress().getHostAddress() + ", port = " + adresse.getPort());
 
                // a) un thread par client : le serveur retourne aussitôt à accept()
                new ClientHandler(socket, numero).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}