class Writting {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void Entete() {
        System.out.println("");
        System.out.println(ANSI_GREEN + "\t\t\t********************************************" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "\t\t\t*                                          *" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "\t\t\t*     BIENVENUE SUR SYSTEME EXPERT PRO     *" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "\t\t\t*                                          *" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "\t\t\t********************************************\n\n" + ANSI_RESET);
        System.out.print("\t\tBase de regle actuelle : ");
        if (BaseRegle.charge.equals("")) {
            System.out.println("- Aucune base charge");
        } else {
            System.out.println("-" + BaseRegle.charge);
        }
    }
}
