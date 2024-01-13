import java.util.Scanner;

class phase3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans le système de gestion éducative.");

        while (true) {
            System.out.println("\nMenu :");
            System.out.println("1. Enregistrer un département");
            System.out.println("2. Enregistrer un enseignant");
            System.out.println("3. Afficher les departements");
            System.out.println("4. Afficher les enseignants");
            System.out.println("5. Enregistrer une filière");
            System.out.println("6. Afficher les filières");
            System.out.println("7. Enregistrer un etudiant");
            System.out.println("8. Afficher les etudiants");
            System.out.println("9. Enregistrer un module");
            System.out.println("10. Afficher les modules");

            System.out.println("11. Quitter");
            System.out.print("Choisissez une option (1-4) : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    enregistrerDepartement();
                    break;
                case 2:
                    enregistrerEnseignant();
                    break;
                case 3:
                    DatabaseManager.afficherDepartements();
                    break;

                case 4:
                    DatabaseManager.afficherEnseignants();
                    break;

                case 5:
                    enregistrerFiliere();
                    break;

                case 6:
                    DatabaseManager.afficherFilieres();
                    break;
                case 7:
                    enregistrerEtudiant();
                    break;

                case 8:
                    DatabaseManager.afficherEtudiant();
                    break;
                case 9:
                    enregistrerModule();
                    break;

                case 10:
                    DatabaseManager.afficherModules();
                    break;
                case 11:
                    System.out.println("Au revoir !");
                    System.exit(0);
                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
            }
        }
    }

    private static void enregistrerDepartement() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnregistrement d'un département : ");
        System.out.println("Nom du département : ");
        String nomDepartement = scanner.nextLine();

        Departement departement = new Departement();
        departement.setNom(nomDepartement);

        System.out.println("Nom du responsable : ");
        String nomEnseignant = scanner.nextLine();

        System.out.println("Prénom du responsable : ");
        String prenomEnseignant = scanner.nextLine();

        System.out.println("Email du responsable : ");
        String emailEnseignant = scanner.nextLine();

        System.out.println("Grade du responsable : ");
        String gradeEnseignant = scanner.nextLine();

        Enseignant enseignant =new Enseignant();
        enseignant.setNom(nomEnseignant);
        enseignant.setPrenom(prenomEnseignant);
        enseignant.setEmail(emailEnseignant);
        enseignant.setGrade(gradeEnseignant);

        DatabaseManager.createDepartement(departement,enseignant);
        System.out.println("Département enregistré avec succès !");
    }

    private static void enregistrerEnseignant() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnregistrement d'un enseignant : ");
        System.out.println("Nom de l'enseignant : ");
        String nomEnseignant = scanner.nextLine();

        System.out.println("Prénom de l'enseignant : ");
        String prenomEnseignant = scanner.nextLine();

        System.out.println("Email de l'enseignant : ");
        String emailEnseignant = scanner.nextLine();

        System.out.println("Grade de l'enseignant : ");
        String gradeEnseignant = scanner.nextLine();

        DatabaseManager.afficherDepartements();

        System.out.println("Choisissez le numéro du département pour l'enseignant : ");
        int choixDepartement = scanner.nextInt();
        scanner.nextLine();

        Departement departement = DatabaseManager.getDepartementById(choixDepartement);

        Enseignant enseignant = new Enseignant();
        enseignant.setNom(nomEnseignant);
        enseignant.setPrenom(prenomEnseignant);
        enseignant.setEmail(emailEnseignant);
        enseignant.setGrade(gradeEnseignant);
        enseignant.affecterDepartement(departement);

        DatabaseManager.createEnseignant(enseignant,departement);
        System.out.println("Enseignant enregistré avec succès !");
    }

    private static void enregistrerFiliere(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Intitule filière : ");
        String intitule = scanner.nextLine();

        System.out.println("Nom de l'enseignant : ");
        String nomEnseignant = scanner.nextLine();

        System.out.println("Prénom de l'enseignant : ");
        String prenomEnseignant = scanner.nextLine();

        System.out.println("Email de l'enseignant : ");
        String emailEnseignant = scanner.nextLine();

        System.out.println("Grade de l'enseignant : ");
        String gradeEnseignant = scanner.nextLine();

        DatabaseManager.afficherDepartements();

        System.out.println("Choisissez le numéro du département pour la filière : ");
        int choixDepartement = scanner.nextInt();
        scanner.nextLine();

        Departement departement = DatabaseManager.getDepartementById(choixDepartement);

        Enseignant enseignant = new Enseignant();
        enseignant.setNom(nomEnseignant);
        enseignant.setPrenom(prenomEnseignant);
        enseignant.setEmail(emailEnseignant);
        enseignant.setGrade(gradeEnseignant);
        enseignant.affecterDepartement(departement);

        Enseignant ens = DatabaseManager.createEnseignant(enseignant,departement);

        Filiere filiere = new Filiere();
        filiere.setIntitule(intitule);

        DatabaseManager.enregistrerFiliere(filiere,departement,ens);
    }
    private static void enregistrerModule(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Intitule module : ");
        String intitule = scanner.nextLine();

        DatabaseManager.afficherFilieres();

        System.out.println("Choisissez le numéro du filiere : ");
        int choixDepartement = scanner.nextInt();
        scanner.nextLine();

        Filiere departement = DatabaseManager.getFiliereById(choixDepartement);


        DatabaseManager.afficherEnseignants();

        System.out.println("Choisissez le numéro du enseignant : ");
        int choixEns = scanner.nextInt();
        scanner.nextLine();

        Enseignant enseignant = DatabaseManager.getEnseignatById(choixEns);



        Module module = new Module();
        module.setIntitule(intitule);

        DatabaseManager.enregistrerModule(module,departement,enseignant);
    }

    private static void enregistrerEtudiant(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nom de l'etudiant : ");
        String nometudiant = scanner.nextLine();

        System.out.println("Prénom de l'etudiant : ");
        String prenometudiant = scanner.nextLine();


        DatabaseManager.afficherFilieres();

        System.out.println("Choisissez le numéro de la filière : ");
        int choixFiliere = scanner.nextInt();
        scanner.nextLine();

        Filiere filiere = DatabaseManager.getFiliereById(choixFiliere);

        Etudiant etudiant = new Etudiant();
        etudiant.setNom(nometudiant);
        etudiant.setPrenom(prenometudiant);

        DatabaseManager.createEtudiant(etudiant,filiere);


    }


}
