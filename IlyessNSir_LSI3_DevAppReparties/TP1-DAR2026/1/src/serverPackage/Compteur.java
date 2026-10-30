package serverPackage;
 
// Compteur global partagé entre tous les threads ClientProcess (ressource critique)
public class Compteur {
 
    private int valeur = 0;
 
    // synchronized : un seul thread à la fois peut exécuter cette méthode,
    // donc pas de conflit d'accès, et l'affichage correspond bien à la valeur incrémentée
    public synchronized int incrementerEtAfficher(int numeroClient, String operation) {
        valeur++;
        System.out.println("[Compteur global] = " + valeur
                + "  (opération \"" + operation + "\" du client n°" + numeroClient + ")");
        return valeur;
    }
 
    public synchronized int getValeur() {
        return valeur;
    }
}
 