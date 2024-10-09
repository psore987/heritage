package Pack1;

import java.util.Scanner;

public class Gest_Personne {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Ajouter une personne");
            System.out.println("2. Supprimer une personne");
            System.out.println("3. Afficher toutes les personnes");
            System.out.println("4. Mettre à jour une personne");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();
            scanner.nextLine();  // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    // Ajouter une personne
                    System.out.print("Entrez le nom de la personne : ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez le prénom de la personne : ");
                    String prenom = scanner.nextLine();
                    Personne nouvellePersonne = new Personne(nom, prenom);
                    nouvellePersonne.ajouter();
                    break;

                case 2:
                    // Supprimer une personne
                    System.out.print("Entrez l'ID de la personne à supprimer : ");
                    int idSuppression = scanner.nextInt();
                    Personne personneASupprimer = new Personne(idSuppression);
                    personneASupprimer.supprimer();
                    break;

                case 3:
                    // Afficher toutes les personnes
                    Personne.afficherTous();
                    break;

                case 4:
                    // Mettre à jour une personne
                    System.out.print("Entrez l'ID de la personne à mettre à jour : ");
                    int idMiseAJour = scanner.nextInt();
                    scanner.nextLine();  // Consommer la nouvelle ligne
                    System.out.print("Entrez le nouveau nom : ");
                    String nouveauNom = scanner.nextLine();
                    System.out.print("Entrez le nouveau prénom : ");
                    String nouveauPrenom = scanner.nextLine();
                    Personne personneAMettreAJour = new Personne(idMiseAJour);
                    personneAMettreAJour.setNom(nouveauNom);
                    personneAMettreAJour.setPrenom(nouveauPrenom);
                    personneAMettreAJour.mettreAJour();
                    break;

                case 5:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Option non valide. Veuillez réessayer.");
            }
        } while (choix != 5);

        scanner.close();
    }
}
