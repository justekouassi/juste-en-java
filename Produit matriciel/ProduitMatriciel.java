import java.util.Scanner;

public class ProduitMatriciel {
    private static Scanner scanner = new Scanner(System.in);

    static int lireEntier(String message) {
        int n;
        do {
            System.out.print(message);
            n = scanner.nextInt();
        } while (n < 1);
        return n;
    }

    static double[][] lireMatrice() {
        int ligne = lireEntier("Nombre de lignes : ");
        int colonne = lireEntier("Nombre de colonnes : ");

        double[][] mat = new double[ligne][colonne];
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                System.out.print("M[" + (i + 1) + "," + (j + 1) + "] = ");
                mat[i][j] = scanner.nextDouble();
            }
        }
        return mat;
    }

    static double[][] multiplierMatrice(double[][] mat1, double[][] mat2) {
        double[][] prod = new double[mat1.length][mat2[0].length];
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                prod[i][j] = 0.0;
                for (int k = 0; k < mat2.length; k++) {
                    prod[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }
        return prod;
    }

    static void afficherMatrice(double[][] prod) {
        for (int i = 0; i < prod.length; i++) {
            for (int j = 0; j < prod[i].length; j++) {
                System.out.print(prod[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        System.out.println("\nSaisie de la 1ere matrice : ");
        double[][] mat1 = lireMatrice();
        System.out.println("\nSaisie de la 2e matrice : ");
        double[][] mat2 = lireMatrice();

        if (mat1[0].length != mat2.length) {
            System.out.println("Produit matriciel impossible");
        } else {
            double[][] prod = multiplierMatrice(mat1, mat2);
            System.out.println("\nRésultat :");
            afficherMatrice(prod);
        }
    }
}