package game.voorwerp;

public class KamerInfoBoek implements Voorwerp {
    private final String info;

    public KamerInfoBoek(String info) {
        this.info = info;
    }

    @Override
    public void gebruik() {
        System.out.println("📘 Kamerinformatie: " + info);
    }
}
