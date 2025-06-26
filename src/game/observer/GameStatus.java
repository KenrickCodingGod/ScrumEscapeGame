package game.observer;

public class GameStatus {
    public final String kamerNaam;
    public final int kamerNummer;
    public final String laatsteMonster;

    public GameStatus(String kamerNaam, int kamerNummer, String laatsteMonster) {
        this.kamerNaam = kamerNaam;
        this.kamerNummer = kamerNummer;
        this.laatsteMonster = laatsteMonster;
    }
}
