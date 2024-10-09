package Pack2;

import java.util.Scanner;

public class Gest_Enseignant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("=== Gestion des Enseignants ===");
            System.out.println("1. Ajouter un Enseignant");
            System.out.println("2. Mettre à jour un Enseignant");
            System.out.println("3. Supprimer un Enseignant");
            System.out.println("4. Afficher tous les Enseignants");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consomme la ligne restante

            switch (choix) {
                case 1:
                    // Ajouter un Enseignant
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Spécialité : ");
                    String specialite = scanner.nextLine();
                    System.out.print("Salaire : ");
                    double salaire = scanner.nextDouble();
                    scanner.nextLine(); // Consomme la ligne restante

                    Enseignant enseignant = new Enseignant(nom, prenom, specialite, salaire);
                    enseignant.ajouter();
                    break;

                case 2:
                    // Mettre à jour un Enseignant
                    System.out.print("ID de l'Enseignant à mettre à jour : ");
                    int idUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consomme la ligne restante
                    System.out.print("Nouveau Nom : ");
                    String newNom = scanner.nextLine();
                    System.out.print("Nouveau Prénom : ");
                    String newPrenom = scanner.nextLine();
                    System.out.print("Nouvelle Spécialité : ");
                    String newSpecialite = scanner.nextLine();
                    System.out.print("Nouveau Salaire : ");
                    double newSalaire = scanner.nextDouble();
                    scanner.nextLine(); // Consomme la ligne restante

                    Enseignant enseignantUpdate = new Enseignant(idUpdate, newNom, newPrenom, newSpecialite, newSalaire);
                    enseignantUpdate.mettreAJour();
                    break;

                case 3:
                    // Supprimer un Enseignant
                    System.out.print("ID de l'Enseignant à supprimer : ");
                    int idDelete = scanner.nextInt();
                    scanner.nextLine(); // Consomme la ligne restante
                    Enseignant enseignantDelete = new Enseignant(idDelete);
                    enseignantDelete.supprimer();
                    break;

                case 4:
                    // Afficher tous les Enseignants
                    Enseignant.afficherTous();
                    break;

                case 5:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    break;
            }
        } while (choix != 5);

        scanner.close();
    }
}
