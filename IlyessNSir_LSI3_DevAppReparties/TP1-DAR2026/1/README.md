TP 2 : Les sockets en mode connecté TCP : E/R de CC & objets
Activité 2-2 : Échange des objets entre Client/Serveur via les Sockets TCP

Ilyess Nsir LSI3


Étape 2 : Implémentation de l'échange d'objets

Principe :

a) La classe Operation implémente Serializable. Elle contient le premier opérande, l'opérateur et le deuxième opérande.
b) Le client crée un objet Operation et l'envoie avec ObjectOutputStream.writeObject().
c) Le serveur reçoit l'objet avec ObjectInputStream.readObject().
d) Le serveur récupère les données avec les getters.
e) Le serveur effectue le calcul.
f) Le serveur renvoie le résultat au client.
g) Le client lit et affiche le résultat.

Serializable permet de transformer l'objet en suite d'octets pour le faire passer dans le socket, puis de le reconstruire de l'autre côté. La classe Operation doit être identique (même package) chez le client et chez le serveur.

img/1.png
img/2.png

Étape 3 : Dépôt des livrables

Le projet est déposé sur GitHub et le lien du repository est remis sur l'ENT.

Conclusion

Au lieu d'envoyer trois valeurs séparées, le client envoie un seul objet Operation sérialisé. Le serveur le reçoit, lit ses données, calcule et renvoie le résultat.