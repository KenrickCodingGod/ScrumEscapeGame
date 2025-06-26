package game.joker;

public class HintJoker extends AbstractJoker {
    private final HintProvider hintProvider;

    public HintJoker(HintProvider hintProvider) {
        this.hintProvider = hintProvider;
    }

    @Override
    public void gebruikInKamer(int kamerNummer) {
        if (!isGebruikt()) {
            String hint = hintProvider.getHintVoorKamer(kamerNummer);
            System.out.println("💡 Joker Hint - " + hint);
            markeerAlsGebruikt();
        } else {
            System.out.println("⚠️ Je hebt deze joker al gebruikt.");
        }
    }

    @Override
    public String getNaam() {
        return "HintJoker";
    }
}
