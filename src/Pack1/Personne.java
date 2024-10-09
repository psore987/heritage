package Pack1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Personne {
    private int id;
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Personne(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    // Méthode pour ajouter une personne
    public void ajouter() {
        Connection connexion = BddUtils.connecterBdd();
        try {
            String sql = "INSERT INTO Personne (Nom_P, Prenom_P) VALUES (?, ?)";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, this.nom);
            statement.setString(2, this.prenom);
            statement.executeUpdate();
            System.out.println("Personne ajoutée : " + this.nom + " " + this.prenom);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BddUtils.fermerBdd(connexion);
        }
    }

    // Méthode pour supprimer une personne
    public void supprimer() {
        Connection connexion = BddUtils.connecterBdd();
        try {
            String sql = "DELETE FROM Personne WHERE Id_P = ?";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setInt(1, this.id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Personne supprimée avec succès.");
            } else {
                System.out.println("Aucune personne trouvée avec l'ID : " + this.id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BddUtils.fermerBdd(connexion);
        }
    }

    // Méthode pour afficher toutes les personnes
    public static void afficherTous() {
        Connection connexion = BddUtils.connecterBdd();
        String sql = "SELECT * FROM Personne";
        try (Statement statement = connexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("Id_P");
                String nom = resultSet.getString("Nom_P");
                String prenom = resultSet.getString("Prenom_P");
                System.out.println("Id: " + id + ", Nom: " + nom + ", Prénom: " + prenom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BddUtils.fermerBdd(connexion);
        }
    }

    // Nouvelle méthode pour mettre à jour une personne
    public void mettreAJour() {
        Connection connexion = BddUtils.connecterBdd();
        try {
            String sql = "UPDATE Personne SET Nom_P = ?, Prenom_P = ? WHERE Id_P = ?";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, this.nom);
            statement.setString(2, this.prenom);
            statement.setInt(3, this.id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Personne mise à jour avec succès.");
            } else {
                System.out.println("Aucune personne trouvée avec l'ID : " + this.id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BddUtils.fermerBdd(connexion);
        }
    }

    @Override
    public String toString() {
        return "Personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
    }
}
