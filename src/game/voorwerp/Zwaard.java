package game.voorwerp;

import game.Monster;

public class Zwaard implements Weapon {
    private final String naam;
    private final String effect;

    public Zwaard(String naam, String effect) {
        this.naam = naam;
        this.effect = effect;
    }

    @Override
    public String attack (Monster monster) {
        return effect + " tegen " + monster.getNaam();
    }
    public String getNaam() {
        return naam;
    }
}
