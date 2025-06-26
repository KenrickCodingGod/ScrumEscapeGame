package game.joker;

public interface Joker {
    String getNaam();
    boolean magGebruikenInKamer(int kamerNummer);
    void gebruikInKamer(int kamerNummer);
}
