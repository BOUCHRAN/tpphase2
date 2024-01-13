
public class Module {
    private String intitule;
    private Filiere filiere;
    private Enseignant professeur;

    public Module(String intitule, Filiere filiere, Enseignant professeur) {
        this.intitule = intitule;
        this.filiere = filiere;
        this.professeur = professeur;
    }

    public Module() {

    }

    // Getters et setters

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Enseignant getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Enseignant professeur) {
        this.professeur = professeur;
    }

}