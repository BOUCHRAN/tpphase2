class Enseignant {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String grade;
    private Departement departement;

    // Constructors, getters, setters...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }



    public void affecterDepartement(Departement departement) {
        this.departement = departement;

        //DatabaseManager.updateEnseignantDepartementAssociation(this.id, departement.getId());
    }
}

