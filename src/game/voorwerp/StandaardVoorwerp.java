package game.voorwerp;

import game.Monster;

public class StandaardVoorwerp implements Voorwerp {
    private final String naam;
    private final String effect;

    public StandaardVoorwerp(String naam, String effect) {
        this.naam = naam;
        this.effect = effect;
    }

    @Override
    public String gebruik(Monster monster) {
        return "🗡️ " + effect + " (tegen " + monster.getNaam() + ")";
    }



    @Override
    public String getNaam() {
        return naam;
    }
}
