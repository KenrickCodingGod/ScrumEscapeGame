package game.voorwerp;

import game.Monster;

public class ScrumTimer implements Voorwerp {
    @Override
    public String getNaam() {
        return "ScrumTimer";
    }

    @Override
    public String gebruik(Monster monster) {
        return "⏱️ Je gebruikt de " + getNaam() + " tegen " + monster.getNaam() +
                "! De tijdsdruk dwingt het team tot korte en bondige updates.";
    }
}
