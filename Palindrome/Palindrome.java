import java.util.Scanner;

class Palindrome {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Entrez un mot ou une phrase : ");
        String original = scanner.nextLine();
        String text = original.toLowerCase();
        String allowedList = "abcdefghijklmnopqrstuvwxyz";
        String temp = "";

        for(int i=0; i<text.length(); i++) {
            char c = text.charAt(i);
            if (allowedList.indexOf(c) != -1) {
                temp += c;
            }
        }
        text = temp;

        int leftPos = 0;
        int rightPos = text.length()-1;
        boolean palindrome = true;

        while ((leftPos < rightPos) && palindrome) {
            if (text.charAt(leftPos) != text.charAt(rightPos)) {
                palindrome = false;
            }
            leftPos++;
            rightPos--;
        }
        
        if (palindrome) {
            System.out.println("C'est un palindrôme !");
        } else {
            System.out.println("Non, ce n'est pas un palindrôme.");
        }
    }
}