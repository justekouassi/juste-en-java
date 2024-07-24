class Triplet<T> {
    T premier;
    T second;
    T troisieme;

    Triplet(T premier, T second, T troisieme) {
        this.premier = premier;
        this.second = second;
        this.troisieme = troisieme;
    }

    T getPremier() {
        return premier;
    }

    T getSecond() {
        return second;
    }

    T getTroisieme() {
        return troisieme;
    }

    public static void main(String[] args) {
        Triplet<String> triplet = new Triplet<>("Kouassi", "Kan", "Juste");
        System.out.println(triplet.getPremier().toUpperCase() + " " + triplet.getSecond() + " " + triplet.getTroisieme());
    }
}