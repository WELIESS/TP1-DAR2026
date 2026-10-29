Compte rendu – TP1 DAR 2026
Application Client / Serveur avec les Sockets Java
Ilyess Nsir LSI1_TP2 
1. Objectif
Créer deux programmes Java qui communiquent par socket : un serveur qui attend, et un client qui s'y connecte. Le projet est versionné avec Git.
2. Serveur
Code (serverPackage/Server.java) :

Capture d'écran 1 : img/Screenshot 2026-10000415.png
 

Résultat : le serveur affiche son message d'attente puis reste bloqué sur accept() tant qu'aucun client ne se connecte.
3. Client
Code (clientPackage/Client.java) :
Capture d'écran 2 : img/Screenshot 2026-10000806.png
 

Résultat : le client affiche « pas encore connecté », se connecte, puis affiche « connecté ». Au même moment, le serveur affiche « un client est connecté ». Si le serveur n'est pas lancé, on obtient Connection refused.
4. Avec une adresse IP
J'ai remplacé localhost par l'adresse IP de la machine (ipconfig) : new Socket("192.168.x.x", 1234). Le résultat est le même.

6. Conclusion
Le serveur attend avec ServerSocket.accept() et le client se connecte avec Socket(adresse, port). Le TP m'a permis de comprendre ce principe de base.