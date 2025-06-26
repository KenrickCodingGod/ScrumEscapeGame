package game.observer;

public class GameStatusView {
    public void toonStatus(GameStatus status) {
        System.out.println("------------------------------");
        System.out.println("📍 Je bent nu in kamer: " + (status.kamerNummer-1));
        System.out.println("🧟‍♂️ Monster: " + status.laatsteMonster);
        System.out.println("🚪 Je hebt toegang tot kamer: " + (status.kamerNummer));
        System.out.println("------------------------------");
    }
}
