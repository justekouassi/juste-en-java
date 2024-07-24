import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author ANGORA Kouamé Israël
 * @author DIABAGATE Issa
 * @author KONE Aboubakar
 * @author KOUASSI Kan Juste
 * @author N'GUESSAN Aya Elielle
 * @author N'GUESSAN Yoboua Justin
 */
public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int choix; // choix dans le menu
        String nom_base_de_regle;
        String nom_regle = "";
        String base_regle_charge; // base des règles
        File file;
        FileWriter writer;
        BaseRegle br;
        do {
            /**
             * Menu
             */
            Writting.clearScreen(); // nettoyer le terminal
            Writting.Entete(); // afficher l'entête
            System.out.println("\n\t\tMenu de selection\n\n");
            System.out.println("\t\t1 - BASE DE REGLES");
            System.out.println("\t\t2 - PROUVER UNE HYPOTHESE");
            System.out.println("\t\t0 - QUITTER");

            System.out.print("\n\t\tCHOIX : ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                /**
                 * 1 base de règles
                 * 2 prouver une hypothèse
                 * 0 quitter
                 */
                case 1:
                    int choix12;
                    do {
                        Writting.clearScreen();
                        Writting.Entete();
                        System.out.println("\n\t\tMenu de selection\n\n");
                        System.out.println("\t\t1 - NOUVELLE BASE DE REGLES");
                        System.out.println("\t\t2 - AJOUTER DES REGLES A UNE BASE DE REGLES");
                        System.out.println("\t\t3 - AFFICHER LES REGLES D'UNE BASE DE REGLES");
                        System.out.println("\t\t4 - CHARGER UNE BASE DE REGLES");
                        System.out.println("\t\t5 - SUPPRIMER UNE BASE DE REGLES");
                        System.out.println("\t\t6 - AFFICHER LA LISTE DES BASES DE REGLES");
                        System.out.println("\t\t0 - RETOUR");
                        System.out.print("\n\t\tCHOIX : ");
                        choix12 = sc.nextInt();
                        sc.nextLine();
                        switch (choix12) {
                            // Nouvelle base de regles
                            case 1:
                                System.out.print("\t\tENTRER LE NOM DE LA BASE DE REGLES : ");
                                // lire le nom de la base de règles saisie
                                nom_base_de_regle = sc.nextLine();
                                // création d'un objet base de règles
                                br = new BaseRegle(nom_base_de_regle);
                                // création du fichier texte de la base de règles
                                file = new File(nom_base_de_regle + ".txt");
                                if (file.createNewFile()) {
                                    // ouvrir le fichier en écriture
                                    writer = new FileWriter(file);
                                    do {
                                        System.out.println(
                                                "\t\tENTRER LES REGLES DE LA BR (Syntaxe de prolog exemple :  R1 = C : F1 , F2) TAPER 0 POUR QUITTER");
                                        nom_regle = sc.nextLine();
                                        nom_regle = nom_regle.toUpperCase();
                                        if (!nom_regle.equals("0")) {
                                            if (Regle.isWellDefine(nom_regle)) {
                                                Regle regle;

                                                // conversion en clause de horn
                                                String[] nomregle2 = nom_regle.split("=", 2);
                                                String rule = nomregle2[0];
                                                nomregle2 = nomregle2[1].split(":");
                                                if (nomregle2[0].split(",").length > 1) {
                                                    for (String c : nomregle2[0].split(",")) {
                                                        nom_regle = rule + " = " + c + " : " + nomregle2[1];
                                                        regle = new Regle(nom_regle);
                                                        br.addRegle(regle);
                                                        nom_regle = nom_regle.concat("\n");
                                                        writer.write(nom_regle);
                                                    }
                                                } else {
                                                    regle = new Regle(nom_regle);
                                                    br.addRegle(regle);
                                                    nom_regle = nom_regle.concat("\n");
                                                    writer.write(nom_regle);
                                                }

                                            } else {
                                                System.out.println(
                                                        "\t\tOUPS ! La saisie de votre regle semble incorrecte veuillez reessayer.");
                                            }
                                        }
                                    } while (!nom_regle.equals("0"));
                                    writer.close();
                                } else {
                                    System.out.println("\t\tOups! Le nom de la base existe deja");
                                }
                                break;
                            case 2:
                                // ajouter des regles à la base
                                Writting.clearScreen();
                                Writting.Entete();
                                System.out.println("\n\t\t Ajouter des regles a une BR \n\n");
                                if (BaseRegle.charge.equals("")) {
                                    System.out.println("\t\tLes bases de regles disponibles sont : ");
                                    BaseRegle.afficheBase();
                                    System.out.print("\t\tEntrez le nom de la base de regles: ");
                                    nom_base_de_regle = sc.nextLine();
                                } else {
                                    System.out.println(BaseRegle.charge);
                                    nom_base_de_regle = BaseRegle.charge;
                                }

                                br = BaseRegle.charger(nom_base_de_regle);
                                if (BaseRegle.estCharge(nom_base_de_regle)) {
                                    writer = new FileWriter(nom_base_de_regle + ".txt", true);
                                    do {
                                        System.out.println(
                                                "\t\tENTRER LES REGLES DE LA BR (Syntaxe de prolog exemple :  R1 = C : F1 , F2) TAPER 0 POUR QUITTER");
                                        nom_regle = sc.nextLine();
                                        nom_regle = nom_regle.toUpperCase();
                                        if (!nom_regle.equals("0")) {
                                            Regle regle;

                                            // conversion en clause de horn
                                            String[] nomregle2 = nom_regle.split("=", 2);
                                            String rule = nomregle2[0];
                                            nomregle2 = nomregle2[1].split(":");
                                            if (nomregle2[0].split(",").length > 1) {
                                                for (String c : nomregle2[0].split(",")) {
                                                    nom_regle = rule + " = " + c + " : " + nomregle2[1];
                                                    regle = new Regle(nom_regle);
                                                    br.addRegle(regle);
                                                    nom_regle = nom_regle.concat("\n");
                                                    writer.write(nom_regle);
                                                }
                                            } else {
                                                regle = new Regle(nom_regle);
                                                br.addRegle(regle);
                                                nom_regle = nom_regle.concat("\n");
                                                writer.write(nom_regle);
                                            }

                                        }
                                    } while (!nom_regle.equals("0"));
                                    writer.close();
                                }
                                break;
                            case 3:
                                // Afficher les regles d'une BR
                                int choix2;
                                do {
                                    Writting.clearScreen();
                                    Writting.Entete();
                                    System.out.println("\n\t\t Afficher les regles d'une BR \n\n");
                                    if (BaseRegle.estCharge()) {
                                        br = BaseRegle.baseCharge;
                                        BaseRegle.afficherRegle(br);
                                    } else {
                                        System.out.println("\t\tLes bases de regles disponibles sont : ");
                                        BaseRegle.afficheBase();
                                        System.out.print("\nEntrez le nom de la base de regles : ");
                                        nom_base_de_regle = sc.nextLine();
                                        if (BaseRegle.existe(nom_base_de_regle)) {
                                            br = BaseRegle.charger(nom_base_de_regle);
                                            System.out.println(
                                                    "\n\t\tLes regles de la base " + nom_base_de_regle + " sont : \n");
                                            BaseRegle.afficherRegle(br);
                                        } else {
                                            System.out.println("\t\tOups la base de regles entre n'existe pas");
                                        }
                                    }
                                    System.out.println("\n\t\tTaper 0 Pour Quitter");
                                    System.out.print("\t\tCHOIX : ");
                                    choix2 = sc.nextInt();
                                    sc.nextLine();
                                } while (choix2 != 0);
                                break;
                            case 4:
                                // Charger une base de règle
                                int choix3;
                                do {
                                    Writting.clearScreen();
                                    Writting.Entete();
                                    System.out.println("\n\t\t Charger une base de regle \n\n");
                                    System.out.println("\t\tListe des bases de regles existantes : ");
                                    BaseRegle.afficheBase();
                                    System.out.print("\t\tEntrez le nom de la base de regles a charger : ");
                                    base_regle_charge = sc.nextLine();
                                    if (BaseRegle.existe(base_regle_charge)) {
                                        br = BaseRegle.charger(base_regle_charge);

                                    } else {
                                        System.out.println("\t\tLa base de regle n'existe pas");
                                    }
                                    System.out.println("\n\t\tTaper 0 Pour Quitter");
                                    System.out.print("\t\tCHOIX : ");
                                    choix3 = sc.nextInt();
                                    sc.nextLine();
                                } while (choix3 != 0);
                                break;
                            case 5:
                                // Supprimer une base de regles
                                int choix5;
                                do {
                                    Writting.clearScreen();
                                    Writting.Entete();
                                    System.out.println("\n\t\t Supprimer une base de regle \n\n");
                                    System.out.println("Liste des bases de regles existantes : ");
                                    BaseRegle.afficheBase();
                                    System.out.print("Entrez le nom de la base de regles a supprimer : ");
                                    nom_base_de_regle = sc.nextLine();
                                    file = new File(nom_base_de_regle.concat(".txt"));
                                    if (file.delete()) {
                                        if (nom_base_de_regle.equals(BaseRegle.charge)) {
                                            BaseRegle.charge = null;
                                            BaseRegle.baseCharge = null;
                                        }
                                        System.out.println("Base supprime avec succes");
                                    } else {
                                        System.out.println("Echec de suppression de la base de regles");
                                    }
                                    System.out.println("\n\t\tTaper 0 Pour Quitter");
                                    System.out.print("\t\tCHOIX : ");
                                    choix5 = sc.nextInt();
                                    sc.nextLine();
                                } while (choix5 != 0);
                                break;
                            case 6:
                                // aficher la liste des bases de regles
                                int choix6;
                                do {
                                    Writting.clearScreen();
                                    Writting.Entete();
                                    System.out.println("\n\t\t Afficher la liste des bases de regles \n\n");
                                    System.out.println("\t\tListe des bases de regles existantes : ");
                                    BaseRegle.afficheBase();
                                    System.out.println("\n\t\tTaper 0 Pour Quitter");
                                    System.out.print("\t\tCHOIX : ");
                                    choix6 = sc.nextInt();
                                    sc.nextLine();
                                } while (choix6 != 0);
                                break;
                        }
                    } while (choix12 != 0);
                    break;
                case 2:
                    // prouver une hyphothèse
                    int choix22;
                    do {
                        Writting.clearScreen();
                        Writting.Entete();
                        System.out.println("\n\t\tProuver une hypothese \n\n");
                        if (BaseRegle.estCharge()) {
                            br = BaseRegle.baseCharge;
                        } else {
                            System.out.println("\t\tLes bases de regles disponibles sont : ");
                            BaseRegle.afficheBase();
                            System.out.print("\t\tEntrez le nom de la base de regles: ");
                            nom_base_de_regle = sc.nextLine();
                            br = BaseRegle.charger(nom_base_de_regle);
                        }

                        if (br != null) {
                            ArrayList<String> bf = new ArrayList<>();
                            System.out.print("\t\tEntrer les faits initiaux (ex :F1 F2 F3) : ");
                            String fait = sc.nextLine().toUpperCase();
                            System.out.print("\t\tEntrer l'hypothèse à prouver : ");
                            String h = sc.nextLine().toUpperCase().trim();
                            String[] bf2 = fait.split(" ");
                            for (String f : bf2) {
                                bf.add(f.trim());
                            }
                            BaseRegle.dejaEssaye.clear();
                            BaseRegle.cheminRaisonnement.clear();
                            if (BaseRegle.chainageAvant(h, bf, br)) {
                                System.out.println(
                                        Writting.ANSI_GREEN + "\n\t\t" + h + " est prouve");
                                System.out.print("\t\tChemin du raisonnement : ");
                                Collections.reverse(BaseRegle.cheminRaisonnement);
                                for (Regle r : BaseRegle.cheminRaisonnement) {
                                    System.out.print(r.nom + " => ");
                                }
                                System.out.println("");
                                System.out.println("\t\t1 - Explication du raisonnement TAPER 0 POUR QUITTER");
                                System.out.print("\t\tCHOIX : ");
                                choix = sc.nextInt();
                                sc.nextLine();
                                if (choix == 1) {
                                    BaseRegle.dejaEssaye.clear();
                                    BaseRegle.cheminRaisonnement.clear();
                                    BaseRegle.raisonnement(h, bf, br);
                                }
                            } else {
                                System.out.println("\t\t" + h + " ne peut etre prouve");
                            }
                        }
                        System.out.println("\nTaper 0 Pour Quitter");
                        System.out.print("\t\tCHOIX : ");
                        choix22 = sc.nextInt();
                        sc.nextLine();
                    } while (choix22 != 0);
                    break;

                case 4:
                    // afficher les regles d"une base
                    if (BaseRegle.estCharge()) {
                        BaseRegle.afficherRegle(BaseRegle.baseCharge);
                    } else {
                        System.out.print("\t\tEntrez le nom de la base de regles a afficher : ");
                        nom_base_de_regle = sc.nextLine();
                        // br = BaseRegle.charger(nom_base_de_regle);
                        if (BaseRegle.existe(nom_base_de_regle)) {
                            BaseRegle.afficherRegle(nom_base_de_regle);
                        } else {
                            System.out.println("\t\tErreur de chargement de la base");
                        }
                    }
                    break;
            }
        } while (choix != 0);

        sc.close();
    }
}
