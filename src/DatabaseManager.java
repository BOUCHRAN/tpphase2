
import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/tpfx";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Enseignant createEnseignant(Enseignant enseignant,Departement departement) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
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

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "UPDATE Enseignant SET departement_id = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, departement.getId());
                preparedStatement.setInt(2, enseignant.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enseignant;
    }

    public static Departement createDepartement(Departement departement, Enseignant enseignant) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
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
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "INSERT INTO Departement (nom, enseignant_id) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, departement.getNom());
                preparedStatement.setInt(2, enseignant.getId());

                preparedStatement.executeUpdate();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int departementId = resultSet.getInt(1);

                    departement.setId(departementId);
                    enseignant.setDepartement(departement);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
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


    public static Etudiant createEtudiant(Etudiant etudiant, Filiere filiere) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "INSERT INTO Etudiant (nom, prenom, filiere_id) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, etudiant.getNom());
                preparedStatement.setString(2, etudiant.getPrenom());
                preparedStatement.setInt(3, filiere.getId());

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
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
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

    public static void afficherEnseignants() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM enseignant";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    System.out.println("Liste des enseignants : ");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nom = resultSet.getString("nom");
                        String prenom = resultSet.getString("prenom");
                        String email = resultSet.getString("email");
                        String grade = resultSet.getString("grade");
                        System.out.println("ID: " + id + ", Nom: " + nom+", Prenom: "+prenom+", Email: "+email+", Grade: "+grade);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Departement getDepartementById(int departementId) {
        Departement departement = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM Departement WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, departementId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nom = resultSet.getString("nom");
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

    public static void enregistrerFiliere(Filiere filiere,Departement departement,Enseignant enseignant) {
        try (Connection connexion = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String requete = "INSERT INTO filiere (intitule, responsable_id, departement_id) VALUES (?, ?, ?)";

            try (PreparedStatement statement = connexion.prepareStatement(requete)) {
                statement.setString(1, filiere.getIntitule());
                statement.setInt(2, enseignant.getId() );
                statement.setInt(3,departement.getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void afficherFilieres() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM filiere";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    System.out.println("Liste des filieres : ");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        int idDep = resultSet.getInt("departement_id");
                        String nom = resultSet.getString("intitule");

                        Departement dep = getDepartementById(idDep);

                        System.out.println("ID: " + id + ", Nom: " + nom+" Departement : "+ dep.getNom());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void afficherModules() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM modules";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    System.out.println("Liste des module : ");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nom = resultSet.getString("intitule");


                        System.out.println("ID: " + id + ", Nom: " + nom);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void afficherEtudiant() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM etudiant";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    System.out.println("Liste des etudiants : ");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        int idDep = resultSet.getInt("filiere_id");
                        String nom = resultSet.getString("nom");
                        String prenom = resultSet.getString("prenom");

                        Filiere filiere = getFiliereById(idDep);

                        System.out.println("ID: " + id + ", Nom: " + nom+ ", Prenom: " + prenom+" Filiere : "+ filiere.getIntitule());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Filiere getFiliereById(int filiereId) {
        Filiere filiere = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM Filiere WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, filiereId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nom = resultSet.getString("intitule");
                        filiere = new Filiere();
                        filiere.setId(id);
                        filiere.setIntitule(nom);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filiere;
    }

    public static Enseignant getEnseignatById(int ensId) {
        Enseignant enseignant = new Enseignant();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM enseignant WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, ensId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nom = resultSet.getString("nom");
                        String prenom = resultSet.getString("prenom");

                        enseignant.setId(id);
                        enseignant.setPrenom(prenom);
                        enseignant.setNom(nom);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enseignant;
    }


    public static void enregistrerModule(Module module,Filiere filiere,Enseignant enseignant) {
        try (Connection connexion = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String requete = "INSERT INTO modules (intitule, filiere_id, professeur_id) VALUES (?, ?, ?)";

            try (PreparedStatement statement = connexion.prepareStatement(requete)) {
                statement.setString(1, module.getIntitule());
                statement.setInt(2, filiere.getId() );
                statement.setInt(3,enseignant.getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
