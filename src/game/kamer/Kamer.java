package game.kamer;

public abstract class Kamer {
    public String naam;

    public Kamer(String naam) {
        this.naam = naam;
    }

    public abstract boolean voerUit();
}
