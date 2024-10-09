package Pack2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Etudiant extends Personne {
    private String filiere;
    private String niveau;

    // Constructeur de l'étudiant
    public Etudiant(String nom, String prenom, String filiere, String niveau) {
        super(nom, prenom);  // Appelle le constructeur de la classe Personne
        this.filiere = filiere;
        this.niveau = niveau;
    }

    // Constructeur avec l'ID pour les opérations de mise à jour ou de suppression
    public Etudiant(int id, String nom, String prenom, String filiere, String niveau) {
        super(id);  // Appelle le constructeur de la classe Personne avec l'ID
        this.setNom(nom);
        this.setPrenom(prenom);
        this.filiere = filiere;
        this.niveau = niveau;
    }

    // Constructeur pour créer un étudiant avec seulement l'ID (pour suppression)
    public Etudiant(int id) {
        super(id);  // Appel au constructeur de la classe Personne qui prend un ID
    }
    
    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    // Méthode pour ajouter un étudiant
    @Override
    public int ajouter() { // Changer void en int
        Connection connexion = BddUtils.connecterBdd();
        int idPersonne = 0; // Initialiser l'ID à 0
        try {
            // Ajouter dans la table Personne et récupérer l'ID
            idPersonne = super.ajouter(); // Appel à la méthode ajouter de Personne

            // Ajouter dans la table Etudiant
            String sqlEtudiant = "INSERT INTO Etudiant (Id_P, Filiere, Niveau) VALUES (?, ?, ?)";
            PreparedStatement stmt = connexion.prepareStatement(sqlEtudiant);
            stmt.setInt(1, idPersonne); // Utiliser l'ID récupéré
            stmt.setString(2, this.filiere);
            stmt.setString(3, this.niveau);
            stmt.executeUpdate();
            System.out.println("Étudiant ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BddUtils.fermerBdd(connexion);
        }
        return idPersonne; // Retourner l'ID de la personne ajoutée
    }

    // Méthode pour mettre à jour un étudiant
    public void mettreAJour() {
        Connection connexion = BddUtils.connecterBdd();
        try {
            // Mettre à jour la table Personne
            super.mettreAJour();

            // Mettre à jour la table Etudiant
            String sql = "UPDATE Etudiant SET Filiere = ?, Niveau = ? WHERE Id_P = ?";
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, this.filiere);
            stmt.setString(2, this.niveau);
            stmt.setInt(3, this.getId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Étudiant mis à jour avec succès.");
            } else {
                System.out.println("Aucun étudiant trouvé avec l'ID : " + this.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BddUtils.fermerBdd(connexion);
        }
    }

    // Méthode pour supprimer un étudiant
    public void supprimer() {
        Connection connexion = BddUtils.connecterBdd();
        try {
            // Supprimer de la table Etudiant
            String sqlEtudiant = "DELETE FROM Etudiant WHERE Id_P = ?";
            PreparedStatement stmtEtudiant = connexion.prepareStatement(sqlEtudiant);
            stmtEtudiant.setInt(1, this.getId());
            int rowsDeleted = stmtEtudiant.executeUpdate();

            if (rowsDeleted > 0) {
                // Supprimer de la table Personne
                super.supprimer();
                System.out.println("Étudiant supprimé avec succès.");
            } else {
                System.out.println("Aucun étudiant trouvé avec l'ID : " + this.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BddUtils.fermerBdd(connexion);
        }
    }

    // Méthode pour afficher tous les étudiants
    public static void afficherTous() {
        Connection connexion = BddUtils.connecterBdd();
        try {
            String query = "SELECT P.Id_P, P.Nom_P, P.Prenom_P, E.Filiere, E.Niveau " +
                           "FROM Personne P JOIN Etudiant E ON P.Id_P = E.Id_P";
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("Id_P");
                String nom = resultSet.getString("Nom_P");
                String prenom = resultSet.getString("Prenom_P");
                String filiere = resultSet.getString("Filiere");
                String niveau = resultSet.getString("Niveau");
                System.out.println("Id: " + id + ", Nom: " + nom + ", Prénom: " + prenom + 
                                   ", Filière: " + filiere + ", Niveau: " + niveau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BddUtils.fermerBdd(connexion);
        }
    }
}
