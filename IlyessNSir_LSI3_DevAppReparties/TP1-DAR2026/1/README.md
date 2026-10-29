Activité 1-2 : Service de multiplication (Client / Serveur)

Nom : [Nom Prénom]
Groupe : [à compléter]

Étape 1 : Préparation de l'espace de travail

Le projet est versionné avec Git. Chaque étape est enregistrée par un commit, puis envoyée sur GitHub avec un push.

img/0.png

Étape 2 : Création du service de multiplication

Principe :

Le client saisit un entier x au clavier.
Le client envoie x au serveur.
Le serveur reçoit x, calcule x * 5 et renvoie le résultat.
Le client reçoit et affiche le résultat.

Le serveur est lancé en premier, puis le client.

Console du client :

img/1.png


Étape 3 : Tests avec différentes valeurs
Test 1 : x positif (x = 4)

img/2.png
img/3.png

Résultat : 20. Le produit d'un nombre positif par 5 est positif.

Test 2 : x négatif (x = -5)

img/4.png

Résultat : -25. Le signe est conservé.

Test 3 : x = 0

img/5.png


Étape 4 : DataInputStream et DataOutputStream

Avec un flux simple (read() / write()), un seul octet (0 à 255) est envoyé : un entier négatif serait déformé.

DataOutputStream.writeInt() envoie les 4 octets de l'entier et DataInputStream.readInt() les relit. La valeur et le signe sont donc conservés.

Capture : test avec un entier négatif (x = -250)

Résultat : -1250, correct.

img/8.png

Étape 5 : Saisies successives jusqu'à 0

img/9.png

Le client demande des entiers en boucle. Après chaque saisie, il envoie l'entier, reçoit x * 5 et l'affiche. Quand l'utilisateur saisit 0, le client envoie 0, le serveur quitte sa boucle, et les sockets sont fermés des deux côtés.

img/6.png
img/7.png
img/9.png

Conclusion

Le client envoie des entiers au serveur avec DataOutputStream, le serveur les multiplie par 5 et répond avec DataInputStream / DataOutputStream. La communication s'arrête proprement quand l'utilisateur saisit 0.