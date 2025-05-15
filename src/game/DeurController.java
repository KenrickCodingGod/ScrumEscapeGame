package game;

public class DeurController implements SpelerObserver {
    @Override
    public void update(Speler speler) {
        System.out.println("🚪 [DeurController] Controleert toegang voor kamer: " + speler.getPositie());
    }
}
