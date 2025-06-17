package game.joker;

public interface Joker {
    void gebruik();
    boolean magGebruikenInKamer(int kamerNummer);
    void gebruikInKamer(int kamerNummer);
    String getNaam();
}
