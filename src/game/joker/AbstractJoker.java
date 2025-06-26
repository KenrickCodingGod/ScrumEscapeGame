package game.joker;

public abstract class AbstractJoker implements Joker {
    private boolean gebruikt = false;

    protected boolean isGebruikt() {
        return gebruikt;
    }

    protected void markeerAlsGebruikt() {
        this.gebruikt = true;
    }

    @Override
    public boolean magGebruikenInKamer(int kamerNummer) {
        return !gebruikt;
    }
}
