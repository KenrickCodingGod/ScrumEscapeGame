package game;

public class MonsterLogger implements SpelerObserver {
    @Override
    public void update(Speler speler) {
        if (!speler.getMonsters().isEmpty()) {
            System.out.println("🧌 [MonsterLogger] Laatste monster: " +
                speler.getMonsters().get(speler.getMonsters().size() - 1).getNaam());
        }
    }
}
