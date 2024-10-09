package Pack2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Enseignant extends Personne {
    private String specialite;
    private double salaire;

    // Constructeur de l'enseignant
    public Enseignant(String nom, String prenom, String specialite, double salaire) {
        super(nom, prenom);  // Appelle le constructeur de la classe Personne
        this.specialite = specialite;
        this.salaire = salaire;
    }

    // Constructeur avec l'ID pour les opérations de mise à jour ou de suppression
    public Enseignant(int id, String nom, String prenom, String specialite, double salaire) {
        super(id);  // Appelle le constructeur de la classe Personne avec l'ID
        this.setNom(nom);
        this.setPrenom(prenom);
        this.specialite = specialite;
        this.salaire = salaire;
    }

    // Constructeur pour créer un enseignant avec seulement l'ID (pour suppression)
    public Enseignant(int id) {
        super(id);  // Appel au constructeur de la classe Personne qui prend un ID
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    // Méthode pour ajouter un enseignant
    public int ajouter() {
        Connection connexion = BddUtils.connecterBdd();
        int idPersonne = 0;
        try {
            // Démarrer la transaction
            connexion.setAutoCommit(false);

            // Ajouter dans la table Personne
            idPersonne = super.ajouter(); // Appel à la méthode ajouter de Personne

            // Ajouter dans la table Enseignant
            String sqlEnseignant = "INSERT INTO Enseignant (Id_P, Specialite, Salaire) VALUES (?, ?, ?)";
            PreparedStatement stmt = connexion.prepareStatement(sqlEnseignant);
            stmt.setInt(1, idPersonne);
            stmt.setString(2, this.specialite);
            stmt.setDouble(3, this.salaire);
            stmt.executeUpdate();

            // Valider la transaction
            connexion.commit();
            System.out.println("Enseignant ajouté avec succès.");
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();  // Annuler en cas d'erreur
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            BddUtils.fermerBdd(connexion);
        }
        return idPersonne;  // Retourner l'ID de l'enseignant ajouté
    }

    // Méthode pour mettre à jour un enseignant
    public void mettreAJour() {
        Connection connexion = BddUtils.connecterBdd();
        try {
            // Mettre à jour la table Personne
            super.mettreAJour();

            // Mettre à jour la table Enseignant
            String sql = "UPDATE Enseignant SET Specialite = ?, Salaire = ? WHERE Id_P = ?";
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, this.specialite);
            stmt.setDouble(2, this.salaire);
            stmt.setInt(3, this.getId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Enseignant mis à jour avec succès.");
            } else {
                System.out.println("Aucun enseignant trouvé avec l'ID : " + this.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BddUtils.fermerBdd(connexion);
        }
    }

    // Méthode pour supprimer un enseignant
    public void supprimer() {
        Connection connexion = BddUtils.connecterBdd();
        try {
            // Supprimer de la table Enseignant
            String sqlEnseignant = "DELETE FROM Enseignant WHERE Id_P = ?";
            PreparedStatement stmtEnseignant = connexion.prepareStatement(sqlEnseignant);
            stmtEnseignant.setInt(1, this.getId());
            int rowsDeleted = stmtEnseignant.executeUpdate();

            if (rowsDeleted > 0) {
                // Supprimer de la table Personne
                super.supprimer();
                System.out.println("Enseignant supprimé avec succès.");
            } else {
                System.out.println("Aucun enseignant trouvé avec l'ID : " + this.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BddUtils.fermerBdd(connexion);
        }
    }

    // Méthode pour afficher tous les enseignants
    public static void afficherTous() {
        Connection connexion = BddUtils.connecterBdd();
        try {
            String query = "SELECT P.Id_P, P.Nom_P, P.Prenom_P, E.Specialite, E.Salaire " +
                           "FROM Personne P JOIN Enseignant E ON P.Id_P = E.Id_P";
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("Id_P");
                String nom = resultSet.getString("Nom_P");
                String prenom = resultSet.getString("Prenom_P");
                String specialite = resultSet.getString("Specialite");
                double salaire = resultSet.getDouble("Salaire");
                System.out.println("Id: " + id + ", Nom: " + nom + ", Prénom: " + prenom + 
                                   ", Spécialité: " + specialite + ", Salaire: " + salaire);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BddUtils.fermerBdd(connexion);
        }
    }
}
