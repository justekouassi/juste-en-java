import java.util.ArrayList;

class Regle {
    public String nom; // nom d'une regle
    ArrayList<String> premisses = new ArrayList<>();
    String conclusion;
    public Boolean active = true;
    public ArrayList<String> action = new ArrayList<String>();

    /**
     * Constructeur permettant de créer une règle en écrivant entièrement la règle
     * @param regle
     */
    Regle(String regle) {
        String nom; // nom de la règle
        String conclusion; // conclusion de la règle
        String[] premisses; // tableau contenant les prémisses de la règle
        String[] regles; // tableau contenant le nom, la conclusion et les prémisses d'une règle
        regles = regle.split("=", 2); // détacher le nom d'une règle de la règle elle-même
        nom = regles[0];
        nom = nom.trim();
        regles = regles[1].split(":");
        conclusion = regles[0];
        conclusion = conclusion.trim();
        premisses = regles[1].split(",", 0);
        this.nom = nom;
        this.conclusion = conclusion;
        for (String premisse : premisses) {
            premisse = premisse.trim();
            this.addPremisses(premisse);
        }

    }

    /**
     * Constructeur permettant de créer une règle à partir de son nom et de sa conclusion
     * @param nom
     * @param conclusion
     */
    Regle(String nom, String conclusion) {
        nom = nom.trim();
        conclusion = conclusion.trim();
        this.nom = nom;
        this.conclusion = conclusion;
    }

    /**
     * Constructeur permettant de créer une règle à partir de son nom, de ses prémisses et de sa conclusion
     * @param nom
     * @param premisses
     * @param conclusion
     */
    Regle(String nom, ArrayList<String> premisses, String conclusion) {
        this.nom = nom;
        this.premisses = premisses;
        this.conclusion = conclusion;
    }

    String getName(String nom) {
        return this.nom;
    }

    /**
     * Vérifie si une règle est bien écrite conformément à notre schéma
     * @param regle
     * @return true
     */
    public static boolean isWellDefine(String regle) {
        String regex = "^ *\\w+ *= *(\\w+ *, *)*\\w+ *: *(\\w+ *, *)*\\w+ *$";
        // String regex = "^ *\\w+ *= *\\w+ *: *(\\w+ *, *)*\\w+ *$";
        return regle.matches(regex);
    }

    /**
     * Ajoute une prémisse dans la liste des prémisses
     * @param premisse
     */
    public void addPremisses(String premisse) {
        this.premisses.add(premisse);
    }

    public void activate(){
        this.active= true;
    }

    public void deactivate(){
        this.active= false;
    }

    public String toString() {
        return this.nom + " --> SI " + this.premisses.toString() + " Alors "+ this.conclusion.toString();
    }

}