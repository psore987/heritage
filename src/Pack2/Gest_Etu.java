package Pack2;

import java.util.Scanner;

public class Gest_Etu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("\nMenu de gestion des étudiants");
            System.out.println("1. Ajouter un étudiant");
            System.out.println("2. Mettre à jour un étudiant");
            System.out.println("3. Supprimer un étudiant");
            System.out.println("4. Afficher tous les étudiants");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    ajouterEtudiant(scanner);
                    break;
                case 2:
                    mettreAJourEtudiant(scanner);
                    break;
                case 3:
                    supprimerEtudiant(scanner);
                    break;
                case 4:
                    Etudiant.afficherTous();  // Appel direct de la méthode afficherTous() depuis la classe Etudiant
                    break;
                case 5:
                    continuer = false;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option non valide. Veuillez réessayer.");
            }
        }

        scanner.close();
    }

    // Méthode pour ajouter un étudiant
    private static void ajouterEtudiant(Scanner scanner) {
        System.out.print("Entrez le nom de l'étudiant : ");
        String nom = scanner.nextLine();
        System.out.print("Entrez le prénom de l'étudiant : ");
        String prenom = scanner.nextLine();
        System.out.print("Entrez la filière de l'étudiant : ");
        String filiere = scanner.nextLine();
        System.out.print("Entrez le niveau de l'étudiant : ");
        String niveau = scanner.nextLine();

        // Créer un nouvel étudiant et l'ajouter dans la base
        Etudiant etudiant = new Etudiant(nom, prenom, filiere, niveau);
        etudiant.ajouter();  // Appel direct de la méthode ajouter() depuis l'objet Etudiant
    }

    // Méthode pour mettre à jour un étudiant
    private static void mettreAJourEtudiant(Scanner scanner) {
        System.out.print("Entrez l'ID de l'étudiant à mettre à jour : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        System.out.print("Entrez le nouveau nom de l'étudiant : ");
        String nom = scanner.nextLine();
        System.out.print("Entrez le nouveau prénom de l'étudiant : ");
        String prenom = scanner.nextLine();
        System.out.print("Entrez la nouvelle filière de l'étudiant : ");
        String filiere = scanner.nextLine();
        System.out.print("Entrez le nouveau niveau de l'étudiant : ");
        String niveau = scanner.nextLine();

        // Mettre à jour l'étudiant dans la base
        Etudiant etudiant = new Etudiant(id, nom, prenom, filiere, niveau);
        etudiant.mettreAJour();  // Appel direct de la méthode mettreAJour() depuis l'objet Etudiant
    }

    // Méthode pour supprimer un étudiant
    private static void supprimerEtudiant(Scanner scanner) {
        System.out.print("Entrez l'ID de l'étudiant à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        // Supprimer l'étudiant
        Etudiant etudiant = new Etudiant(id);
        etudiant.supprimer();  // Appel direct de la méthode supprimer() depuis l'objet Etudiant
    }
}
