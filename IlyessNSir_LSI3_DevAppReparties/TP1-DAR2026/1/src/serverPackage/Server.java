package serverPackage;
 
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
 
public class Server {
 
    static final int PORT = 1234;
 
    public static void main(String[] args) {
        Compteur compteur = new Compteur();   // un seul compteur, partagé par tous les threads
        int numero = 0;
 
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Serveur multi-thread en attente sur le port " + PORT);
 
            while (true) {
                Socket socket = serverSocket.accept();
                numero++;
 
                InetSocketAddress adresse = (InetSocketAddress) socket.getRemoteSocketAddress();
                System.out.println("Client n°" + numero + " connecté : IP = "
                        + adresse.getAddress().getHostAddress() + ", port = " + adresse.getPort());
 
                // un thread dédié par client
                new Clientprocess(socket, numero, compteur).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
 