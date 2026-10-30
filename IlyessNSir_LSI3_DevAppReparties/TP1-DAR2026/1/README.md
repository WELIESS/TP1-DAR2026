TP2 : Les sockets en mode connecté TCP : E/R de CC & objets
Activité 2-1 : Échange de CC entre Client/Serveur via les Sockets TCP

Ilyess Nsir LSI3


Étape 2 : Flux de traitement

Principe :

Le client saisit une opération, par exemple 55 * 25.
La syntaxe est validée côté client (entier, opérateur + - * /, entier) avant l'envoi.
Le client envoie le premier opérande, l'opérateur et le deuxième opérande au serveur (writeInt, writeChar, writeInt).
Le serveur lit les trois éléments, calcule l'opération et renvoie le résultat.
Le client lit le résultat et l'affiche.

Le serveur vérifie aussi l'opérateur et refuse la division par zéro. Le client tape q pour terminer.

img/1.png
img/2.png

Étape 3 : Dépôt des livrables

Le projet est déposé sur GitHub et le lien du repository est remis sur l'ENT.


Conclusion

Le client envoie une opération en trois éléments (opérande, opérateur, opérande) avec DataOutputStream. Le serveur la calcule avec DataInputStream et renvoie le résultat. La validation de la syntaxe évite d'envoyer des chaînes incorrectes.