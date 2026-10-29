package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Je suis un serveur en attente la connexion d'un client ");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("un client est connecté");

                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    int x = in.readInt();
                    if (x == 0) {
                        System.out.println("0 reçu : fin de la communication avec ce client");
                        break;
                    }
                    int resultat = x * 5;
                    System.out.println("Nombre reçu : " + x + " -> résultat envoyé : " + resultat);
                    out.writeInt(resultat);
                }
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}