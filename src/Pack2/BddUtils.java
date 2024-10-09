package Pack2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BddUtils {

    // Détails de la base de données MariaDB
    private static final String URL = "jdbc:mariadb://localhost:3306/Heritage";
    private static final String UTILISATEUR = "rootheritage";
    private static final String MOT_DE_PASSE = "passwordheritage";

    public static Connection connecterBdd() {
        Connection connexion = null;
        try {
            // Chargement du pilote JDBC
            Class.forName("org.mariadb.jdbc.Driver");

            // Établir la connexion
            connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
            System.out.println("Connexion à la base de données réussie !");
        } catch (ClassNotFoundException e) {
            System.err.println("Le driver JDBC n'a pas été trouvé.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
        return connexion;
    }

    public static void fermerBdd(Connection connexion) {
        if (connexion != null) {
            try {
                connexion.close();
                System.out.println("Connexion à la base de données fermée.");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}
