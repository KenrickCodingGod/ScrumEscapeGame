package game.observer;

public class GameStatusView {
    public void toonStatus(GameStatus status) {
        System.out.println("------------------------------");
        System.out.println("📍 Je bent nu in kamer: " + (status.kamerIndex + 1));
        System.out.println("🧟‍♂️ Monster: " + status.laatsteMonster);
        System.out.println("🚪 Er is een deur naar kamer: " + (status.kamerIndex + 2));
        System.out.println("------------------------------");
    }
}
