package game.voorwerp;

import game.Monster;

public class KamerInfoBoek implements Voorwerp {
    @Override
    public String getNaam() {
        return "KamerInfoBoek";
    }

    @Override
    public String gebruik(Monster monster) {
        return "📘 Je leest de kameruitleg nog een keer. Misschien helpt dat.";
    }
}
