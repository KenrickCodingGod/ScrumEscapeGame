package game;

public class DeurController implements SpelerObserver {
    @Override
    public void update(Speler speler) {
        System.out.println("🚪 [DeurController] Controleert toegang tot kamer: " + (speler.getPositie() + 1));
    }
}
