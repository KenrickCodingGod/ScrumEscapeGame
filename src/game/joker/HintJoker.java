package game.joker;

public class HintJoker implements Joker {
    private boolean gebruikt = false;

    @Override
    public void gebruik() {
        if (!gebruikt) {
            System.out.println("Hint: Denk aan de Scrum Guide en de rollen!");
            gebruikt = true;
        }
    }

    @Override
    public boolean magGebruikenInKamer(int kamerNummer) {
        return !gebruikt;
    }

    @Override
    public String getNaam() {
        return "HintJoker";
    }
}
