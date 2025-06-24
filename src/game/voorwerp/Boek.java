package game.voorwerp;



public class Boek implements Readable {
    private final String naam;
    private final String boodschap;

    public Boek(String naam, String boodschap) {
        this.naam = naam;
        this.boodschap = boodschap;
    }

    @Override
    public String showMessage() {
        return boodschap;
    }

    public String getNaam() {
        return naam;
    }
}
