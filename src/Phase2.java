import java.sql.*;
public class Phase2 {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/projetphase2";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void createEnseignant(Enseignant enseignant) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projetphase2", "root", "w@2915djkq#")) {
            String query = "INSERT INTO Enseignant (nom, prenom, email, grade) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, enseignant.getNom());
                preparedStatement.setString(2, enseignant.getPrenom());
                preparedStatement.setString(3, enseignant.getEmail());
                preparedStatement.setString(4, enseignant.getGrade());

                preparedStatement.executeUpdate();

                var resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int enseignantId = resultSet.getInt(1);

                    enseignant.setId(enseignantId);
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projetphase2", "root", "w@2915djkq#")) {
            String query = "UPDATE Enseignant SET departement_id = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, enseignant.getDepartement().getId());
                preparedStatement.setInt(2, enseignant.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a method to create a Departement record
    public static Departement createDepartement(Departement departement, Enseignant enseignant) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projetphase2", "root", "w@2915djkq#")){
            String query = "INSERT INTO Enseignant (nom, prenom, email, grade) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, enseignant.getNom());
                preparedStatement.setString(2, enseignant.getPrenom());
                preparedStatement.setString(3, enseignant.getEmail());
                preparedStatement.setString(4, enseignant.getGrade());

                preparedStatement.executeUpdate();

                // Retrieve the generated Enseignant ID
                var resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int enseignantId = resultSet.getInt(1);

                    // Set the Enseignant ID for future reference
                    enseignant.setId(enseignantId);
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projetphase2", "root", "w@2915djkq#")) {
            String query = "INSERT INTO Departement (nom, enseignant_id) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, departement.getNom());
                preparedStatement.setInt(2, enseignant.getId());

                preparedStatement.executeUpdate();

                // Retrieve the generated Departement ID
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int departementId = resultSet.getInt(1);

                    // Set the ID for the departement object
                    departement.setId(departementId);
                    enseignant.setDepartement(departement);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projetphase2", "root", "w@2915djkq#")) {
            String query = "UPDATE Enseignant SET departement_id = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, departement.getId());
                preparedStatement.setInt(2, enseignant.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departement;
    }

    public static void updateEnseignantDepartementAssociation(int enseignantId, int departementId) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projetphase2", "root", "w@2915djkq#")) {
            String query = "UPDATE Enseignant SET departement_id = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, departementId);
                preparedStatement.setInt(2, enseignantId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Etudiant createEtudiant(Etudiant etudiant, Filiere filiere) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projetphase2", "root", "w@2915djkq#")) {
            String query = "INSERT INTO Etudiant (nom, prenom, filiere_id) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, etudiant.getNom());
                preparedStatement.setString(2, etudiant.getPrenom());
                preparedStatement.setInt(3, filiere.getId()); // Assuming Filiere has an ID

                preparedStatement.executeUpdate();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int etudiantId = resultSet.getInt(1);

                    etudiant.setId(etudiantId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return etudiant;
    }

    public static void afficherDepartements() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projetphase2", "root", "w@2915djkq#")) {
            String query = "SELECT * FROM Departement";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    System.out.println("Liste des départements : ");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nom = resultSet.getString("nom");
                        System.out.println("ID: " + id + ", Nom: " + nom);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Departement getDepartementById(int departementId) {
        Departement departement = null;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projetphase2", "root", "w@2915djkq#")) {
            String query = "SELECT * FROM Departement WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, departementId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nom = resultSet.getString("nom");
                        // You may need to retrieve other information depending on your Departement class
                        departement = new Departement();
                        departement.setId(id);
                        departement.setNom(nom);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departement;
    }

    public static void createFiliere(Filiere filiere, Departement departement, Enseignant enseignant) {
        try (Connection connection = DriverManager.getConnection(URL, "root", "w@2915djkq#")) {


            // Insert Filiere record
            String filiereQuery = "INSERT INTO Filiere (intitule, responsable_id, departement_id) VALUES (?, ?, ?)";
            try (PreparedStatement filiereStatement = connection.prepareStatement(filiereQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                filiereStatement.setString(1, filiere.getIntitule());
                filiereStatement.setInt(2, enseignant.getId());
                filiereStatement.setInt(3, departement.getId());

                filiereStatement.executeUpdate();

                ResultSet filiereResultSet = filiereStatement.getGeneratedKeys();
                if (filiereResultSet.next()) {
                    int filiereId = filiereResultSet.getInt(1);

                    filiere.setId(filiereId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
