import java.util.Scanner;

class Permute {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Valeur de x : ");
        int x = scanner.nextInt();
        System.out.print("Valeur de y : ");
        int y = scanner.nextInt();

        System.out.println("Avant permutation : ");
        System.out.println("x = "+x+"\n"+"y = "+y);

        int temp = x;
        x = y;
        y = temp;

        System.out.println("Après permutation : ");
        System.out.println("x = "+x+"\n"+"y = "+y);
    }
}
