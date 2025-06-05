package game.voorwerp;

public class Zwaard implements Voorwerp {
    private final String doel;

    public Zwaard(String doel) {
        this.doel = doel;
    }

    @Override
    public void gebruik() {
        System.out.println("🗡️ Je gebruikt het zwaard om " + doel + " te verslaan!");
    }
}
