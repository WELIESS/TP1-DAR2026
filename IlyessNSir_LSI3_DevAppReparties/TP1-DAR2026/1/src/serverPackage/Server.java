package serverPackage;
 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
 
public class Server {
 
    // A MODIFIER : adresse IP reelle de la machine serveur (ipconfig -> Adresse IPv4)
    static final String IP_SERVEUR = "192.168.1.12";
    static final int PORT = 1234;
 
    public static void main(String[] args) {
        try {
            InetAddress adresse = InetAddress.getByName(IP_SERVEUR);
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(adresse, PORT));
            System.out.println("Serveur en attente sur " + IP_SERVEUR + ":" + PORT);
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connecté : " + socket.getInetAddress().getHostAddress());
 
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
 
                try {
                    while (true) {
                        int choix = in.readInt();
                        if (choix == 0) {
                            System.out.println("Choix 0 : fin de la communication avec ce client");
                            break;
                        }
                        int x = in.readInt();
                        double resultat = 0;
                        String op = "";
 
                        switch (choix) {
                            case 1: resultat = x + 5; op = "+"; break;
                            case 2: resultat = x - 5; op = "-"; break;
                            case 3: resultat = x * 5; op = "*"; break;
                            case 4: resultat = x / 5.0; op = "/"; break;
                            default: System.out.println("Choix inconnu : " + choix);
                        }
                        System.out.println(x + " " + op + " 5 = " + resultat);
                        out.writeDouble(resultat);
                    }
                } catch (IOException e) {
                    System.out.println("Connexion interrompue par le client");
                }
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}