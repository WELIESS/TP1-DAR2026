TP 3 : Serveur Multi-threads - sockets en mode connecté TCP
Activité 3-1 : Acceptation de plusieurs clients


Ilyess Nsir LSI3

Étape 2 : Serveur multi-thread
1. Implémentation
a) Serveur multi-thread : le serveur boucle sur accept(). À chaque nouveau client, il crée un thread (ClientHandler) qui s'occupe de ce client, puis retourne attendre le suivant. Plusieurs clients sont donc servis en même temps.
b) Informations côté serveur : pour chaque client connecté, le serveur affiche son adresse IP (obtenue avec getRemoteSocketAddress()) et son numéro d'ordre de connexion (client n°1, n°2...).
c) Numéro d'ordre : le serveur envoie à chaque client son numéro d'ordre avec un message de bienvenue.



Serveur démarré :

2. Tests locaux

a) Clients Telnet (telnet localhost 1234 dans une invite de commandes)

img/1.png

b) Clients Java (plusieurs exécutions de Client.java)

img/2.png
img/3.png
img/4.png

Le projet est déposé sur GitHub et le lien du repository est remis sur l'ENT.

Synthèse

Un serveur à un seul thread ne peut servir qu'un client à la fois : tant qu'il traite le premier, les autres attendent. En créant un thread par client (ClientHandler), le serveur revient tout de suite à accept() et accepte de nouveaux clients pendant que les précédents sont servis. Le numéro d'ordre de connexion est géré par un compteur partagé (AtomicInteger) pour éviter les erreurs quand plusieurs threads l'utilisent. Les tests avec Telnet et avec le client Java ont montré que plusieurs clients peuvent être connectés simultanément, chacun avec son numéro, en local comme sur plusieurs machines du même réseau.