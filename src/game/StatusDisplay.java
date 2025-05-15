package game;

public class StatusDisplay implements SpelerObserver {
    @Override
    public void update(Speler speler) {
        System.out.println("🔄 [StatusDisplay] Positie: " + speler.getPositie());
        System.out.println("🔄 [StatusDisplay] Monsters: " + speler.getMonsterNamenAlsString());
    }
}
