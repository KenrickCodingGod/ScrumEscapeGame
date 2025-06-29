package game.observer;

public class GameStatus {
    public final String kamerNaam;
    public final int kamerIndex;
    public final String laatsteMonster;

    public GameStatus(String kamerNaam, int kamerIndex, String laatsteMonster) {
        this.kamerNaam = kamerNaam;
        this.kamerIndex = kamerIndex;
        this.laatsteMonster = laatsteMonster;
    }
}
