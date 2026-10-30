TP 3 : Serveur Multi-threads - sockets en mode connecté TCP
Activité 3-2 : Amélioration de l’application calculatrice

Ilyess Nsir LSI3

Étape 1 : Serveur multi-thread pour le service de calculatrice

Principe :

Le service de calculatrice est intégré dans le serveur multi-thread. Chaque client est géré par un thread ClientProcess.
Le client communique avec le serveur par objets : il envoie un objet Operation (premier opérande, opérateur, deuxième opérande) avec ObjectOutputStream, et reçoit le résultat.
Le serveur affiche l'adresse IP et le numéro de chaque client connecté.


Serveur démarré :

Test 3 : plusieurs clients sur la machine locale

Console du serveur :

img/1.png


Test 4 : plusieurs machines du réseau local

Adresse IP de la machine serveur (ipconfig) :


Client sur une autre machine :

img/2.png

Étape 3 : Gestion d'un accès concurrent

Un compteur global (classe Compteur) indique le nombre total d'opérations traitées par le serveur. Il est partagé entre tous les threads ClientProcess : c'est une ressource critique.

À chaque opération calculée :

le compteur est incrémenté,
sa nouvelle valeur est affichée dans la console du serveur.

L'incrémentation et l'affichage sont dans une méthode synchronized. Un seul thread à la fois peut l'exécuter.

Code de la classe Compteur :

Test 1 : service avec plusieurs clients en parallèle

img/3-3.png

Test 2 : le compteur reste cohérent

img/3.png

Chaque opération incrémente le compteur de 1, sans valeur perdue ni doublon (1, 2, 3, 4, ...).


Test 3 : plusieurs machines du même réseau

img/3-2.png
"le test a été fait avec votre IP réelle. pas d'une autre machine"

Étape 4 : Dépôt des livrables

Le projet est déposé sur GitHub et le lien du repository est remis sur l'ENT.


Synthèse
Multi-thread : le serveur crée un thread ClientProcess par client. Chaque client a sa propre communication, indépendante des autres, et le serveur reste disponible pour de nouvelles connexions.
Communication par objets : l'objet Operation (Serializable) regroupe les trois éléments de l'opération et circule dans les flux ObjectOutputStream / ObjectInputStream.
Accès concurrent : sans protection, deux threads qui incrémentent le compteur en même temps peuvent lire la même valeur et écrire le même résultat, donc une opération est perdue (condition de concurrence). Avec le mot-clé synchronized, un seul thread à la fois modifie le compteur, et sa valeur reste cohérente, même avec plusieurs clients en parallèle ou sur plusieurs machines.