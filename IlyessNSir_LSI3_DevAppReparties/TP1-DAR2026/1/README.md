Activité 1-3 : Connexion entre deux machines et calculatrice améliorée

ilyess nsir LSI3 1 tp 2

Étape 1 : Connexion Client/Serveur sur deux machines distinctes

Principe : localhost est remplacé par l'adresse IP réelle de la machine serveur. Les deux machines sont sur le même réseau local.

Côté serveur : ServerSocket est lié à l'adresse IP de la machine avec InetSocketAddress(InetAddress, port) et bind().
Côté client : la connexion se fait vers l'adresse IP du serveur avec InetAddress.getByName(...) et InetSocketAddress.
Port utilisé : 1234.

img/1.png

Étape 2 : Service de calculatrice amélioré

Le client affiche un menu : 1. Addition, 2. Soustraction, 3. Multiplication, 4. Division, 0. Quitter.

img/2.png

Le client envoie le choix puis la valeur x au serveur.
Le serveur calcule selon l'opération (x + 5, x - 5, x * 5, x / 5).
Le serveur renvoie le résultat, le client l'affiche.
Le choix 0 arrête proprement la communication.


img/3.png

Conclusion:

L'application fonctionne entre deux machines du même réseau grâce à l'adresse IP du serveur. Le client choisit une opération, le serveur effectue le calcul et renvoie le résultat.