package game;

public class Monster {
    private final String naam;
    private final String uitleg;

    public Monster(String naam, String uitleg) {
        this.naam = naam;
        this.uitleg = uitleg;
    }

    public String getNaam() {
        return naam;
    }


    public void toonMonster() {
        System.out.println("------------------------------\n🧟‍♂️ Monster opgeroepen: " + naam);
        System.out.println("💬 Uitleg: " + uitleg);
    }

    @Override
    public String toString() {
        return naam;
    }
}
