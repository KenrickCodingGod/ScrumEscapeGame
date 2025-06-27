package game.vraag;

import game.Speler;
import game.assistent.*;
import game.joker.HintJoker;
import game.joker.Joker;
import game.joker.KeyJoker;
import game.kamer.Kamer;
import game.voorwerp.Readable;

import java.util.List;

public class VraagInteractieHandler {

    public boolean verwerkInput(String input, Speler speler, Kamer kamer) {
        switch (input.toLowerCase()) {
            case "gebruik assistent" -> {
                verwerkAssistent(kamer);
                return true;
            }
            case "hintjoker" -> {
                verwerkJokerVanType(speler, kamer, HintJoker.class, "❌ Je hebt geen HintJoker gekozen.");
                return true;
            }
            case "keyjoker" -> {
                verwerkJokerVanType(speler, kamer, KeyJoker.class, "❌ Je hebt geen KeyJoker gekozen.");
                return true;
            }


            case "gebruik boek" -> {
                verwerkBoek(kamer);
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    private void verwerkAssistent(Kamer kamer) {
        if (kamer.isAssistentToegestaan()) {
            Assistent assistent = new Assistent(List.of(
                    new HintAssistent(),
                    new StappenplanHulpmiddel(),
                    new Motivator()
            ));
            assistent.activeer();
        } else {
            System.out.println("❌ Assistent is niet beschikbaar in deze kamer.");
        }
    }

    private void verwerkJokerVanType(Speler speler, Kamer kamer, Class<? extends Joker> verwachtType, String foutmelding) {
        if (!speler.heeftJoker()) {
            System.out.println("❌ Je hebt geen joker gekozen.");
            return;
        }

        Joker gekozen = speler.getGekozenJoker();

        if (!verwachtType.isInstance(gekozen)) {
            System.out.println(foutmelding);
            return;
        }

        if (speler.jokerAlGebruikt()) {
            System.out.println("❌ Je hebt je joker al gebruikt.");
            return;
        }

        boolean success = speler.gebruikJoker(kamer);
        if (!success) {
            System.out.println("❌ De " + gekozen.getNaam() + " kon niet gebruikt worden.");
        }
    }


    private void verwerkBoek(Kamer kamer) {
        Readable boek = kamer.getBoek();
        if (boek != null) {
            System.out.println("📖 " + boek.showMessage());
        } else {
            System.out.println("❌ Er is geen boek in deze kamer.");
        }
    }
}
