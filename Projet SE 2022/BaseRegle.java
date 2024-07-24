import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Scanner;

class BaseRegle {

    String nom; // nom de la base de regles
    static String charge = "";
    static BaseRegle baseCharge;
    ArrayList<Regle> regles = new ArrayList<>(); // liste des règles de la base de règle
    static ArrayList<String> dejaEssaye = new ArrayList<>();
    static ArrayList<Regle> cheminRaisonnement = new ArrayList<>();
    static ArrayList<String> memoire_travail = new ArrayList<>();
    static ArrayList<Regle> ensemble_conflits = new ArrayList<>();

    /**
     * Constructeur pour créer une base de règles
     * @param nom
     */
    BaseRegle(String nom) {
        this.nom = nom;
    }

    /**
     * Ajout d'une règle dans la base des règles en dernière position
     * @param regle
     */
    public void addRegle(Regle regle) {
        this.regles.add(regle);
    }

    /**
     * Ajout d'une règle dans la base des règles en positon index
     * @param index
     * @param regle
     */
    public void addRegle(int index, Regle regle) {
        this.regles.add(index, regle);
    }

    /**
     * Vérifie si une base de règles est chargée
     * @return true
     */
    public static boolean estCharge() {
        return !BaseRegle.charge.equals("");
    }

    /**
     * Vérifie si une base de règles est chargée 
     * @return true
     */
    public static boolean estCharge(String nom) {
        return BaseRegle.charge == nom;
    }

    /**
     * Vérifie si un fichier existe
     * @param nom
     * @return true
     */
    public static boolean existe(String nom) {
        File f = new File(nom.concat(".txt"));
        if (f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }

    /**
     * Affiche toutes les bases de règles créées
     */
    public static void afficheBase() {
        File f = new File(".");
        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        };

        File[] files = f.listFiles(textFilter);
        if (files.length == 0) {
            System.out.println("\t\t==> Aucune base de regles");
        } else {
            int index = 0;
            for (File file : files) {
                System.out.println("\t\t--> " + ++index + " - " + file.getName().replace(".txt", ""));
            }
        }
    }

    /**
     * Affiche les règles d'une base de règle
     * @param br
     */
    public static void afficherRegle(BaseRegle br) {
        for (Regle regle : br.regles) {
            System.out.print(regle.nom + "\t\t : SI ");
            for (int i = 0; i < regle.premisses.size() - 1; i++) {
                System.out.print(regle.premisses.get(i) + " ET ");
            }
            System.out.print(regle.premisses.get(regle.premisses.size() - 1));
            System.out.println(" ALORS " + regle.conclusion);
        }
    }

    public static void afficherRegle(String nom_base_de_regle) {
        // affiche les regles d'une base de regle
        File f = new File(nom_base_de_regle.concat(".txt"));
        try {
            Scanner sc = new Scanner(f);
            String regle;
            while (sc.hasNextLine()) {
                // System.out.println(sc.nextLine());
                regle = sc.nextLine();
                // System.out.print(regle.nom + " : ");
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("\t\tFichier non trouve");
        }
    }

    public static BaseRegle charger(String nom) {
        if (existe(nom)) {
            // si la base de regle exite
            if (!estCharge(nom)) {
                File f = new File(nom.concat(".txt"));
                Regle regle;
                BaseRegle br = new BaseRegle(nom);
                if (f.length() == 0) {
                    // si il b'existe aucune regle dans la base
                    charge = nom;
                    baseCharge = br;
                    return br;
                } else {
                    try {
                        Scanner sc = new Scanner(f);
                        String regleparse;
                        while (sc.hasNextLine()) {
                            regleparse = sc.nextLine();
                            regle = new Regle(regleparse);
                            br.addRegle(regle);
                        }
                        charge = nom;
                        baseCharge = br;
                        sc.close();
                        return br;
                    } catch (FileNotFoundException e) {
                        System.out.println("Fichier non trouve");
                    }
                }
                System.out.println("\t\tBase charge avec succes");
            } else {
                System.out.println("\t\tLa base est dejà charge");
                return baseCharge;
            }
        } else {
            System.out.println("\t\tOups la base entree n'existe pas");
        }
        return null;
    }

    public ArrayList<String> countRegleApplicable(ArrayList<String> bf){
        ArrayList<String> ec = new ArrayList<>();
        for (Regle regle : this.regles) {
            if(bf.containsAll(regle.premisses) && regle.active){
                ec.add(regle.nom);
            }
        }
        return ec;
    }

    public Regle getRegleApplicable(ArrayList<String> bf) throws Exception{
        Regle rApp = null;
        for (Regle regle : this.regles) {
            if(bf.containsAll(regle.premisses) && regle.active){
                rApp = regle;
                break;
            }
        }
        if(rApp==null){
            throw new Exception("error no base"); 
        }
        return  rApp;

    }

    /**
     * Algorithme du chainage avant
     * @param h : hypothèse à prouver
     * @param bf : base des faits initiale
     * @param br : base des règles
     * @return : true
     * @throws Exception
     */
    public static boolean chainageAvant(String h, ArrayList<String> bf, BaseRegle br) throws Exception {
        ArrayList<String> listeF = bf;
        ArrayList<Regle> treeRegle = new ArrayList<Regle>();
        int ncycle = 1;
        System.out.println("\nHYPOTHESE A VERIFIER : " + h);

        while(!listeF.contains(h) && br.countRegleApplicable(listeF).size() > 0) {
            System.out.print("\nCycle " + ncycle + " : ");
            System.out.print("MT = {");
            for (String s : listeF) {
                System.out.print(s + " , ");
            }
            System.out.print("}\n");
            
            Regle regAppl = br.getRegleApplicable(listeF);

            System.out.println("EC = " + br.countRegleApplicable(listeF).toString());
            regAppl.deactivate();
            // le chemin des règles
            treeRegle.add(regAppl);

            System.out.println("RE = " + regAppl.nom);

            // listeF.addAll(regAppl.action);
            listeF.add(regAppl.conclusion);
            System.out.println("MT = " + listeF.toString());
            ncycle++;
            if (!listeF.contains(h)) {
                System.out.println(h + " est non déduit.");
            }
        }
        if (listeF.contains(h)) {
            System.out.println(h + " est déduit.");
            System.out.print("\nChemin = ");
            String chemin = "";
            for (Regle r : treeRegle) {
                chemin += r.nom + " -> ";
            }
            System.out.print(chemin);
            return true;

        } else {
            System.out.println(h + " n'est pas déduit.");
            return false;
        }
    }

    // public static void chainageAvantRaisonnement(String h, ArrayList<String> bf, ArrayList<Regle> br, int ncycle,
    //         ArrayList<String> bfbis) {
    //     ArrayList<String> listeF = bf;
    //     System.out.println("HYPOTHESE A VERIFIER : " + h);
    //     System.out.print("Cycle " + ncycle + " : ");
    //     System.out.print("MT = {");
    //     for (String s : listeF) {
    //         System.out.print(s + " , ");
    //     }
    //     System.out.print("}\n");
    // }

    public static void raisonnement(String h, ArrayList<String> bf, BaseRegle br) throws Exception {
        System.out.println("-----Explication du raisonnement precedent-----");
        System.out.println("CONTENU DE LA BASE DE REGLES\n\n");
        BaseRegle.afficherRegle(br);
        System.out.print("LISTE DES FAITS INITIAUX : ");
        for (String fait : bf) {
            System.out.print(fait + " ");
        }
        System.out.println("\nHYPOTHESE A PROUVER : " + h);
        ArrayList<String> bfbis = new ArrayList<>(bf);
        bfbis.add("Non " + h);
        // chainageAvantRaisonnement(h, bf, br.regles, 1, bfbis);
        chainageAvant(h, bf, br);
    }
}
