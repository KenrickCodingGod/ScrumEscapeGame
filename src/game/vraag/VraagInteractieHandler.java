package game.vraag;

import game.Speler;
import game.assistent.*;
import game.command.CommandUitvoerder;
import game.command.GebruikJokerCommand;
import game.joker.KeyJoker;
import game.kamer.Kamer;
import game.voorwerp.Readable;

import java.util.List;

public class VraagInteractieHandler {
    public boolean verwerkInput(String input, Speler speler, Kamer kamer) {
        int kamerNummer = kamer.getKamerNummer();

        switch (input) {
            case "gebruik assistent" -> {
                verwerkAssistent(speler, kamerNummer);
                return true;
            }
            case "keyjoker" -> {
                if (verwerkKeyJoker(speler, kamerNummer));
                return true;
            }
            case "hintjoker" -> {
                verwerkHintJoker(speler, kamerNummer);
                return true;
            }
            case "gebruik boek" -> {
                verwerkBoek(kamer);
                return true;
            }
        }
        return false;
    }

    private void verwerkAssistent(Speler speler, int kamerNummer) {
        if (kamerNummer == 1 || kamerNummer == 3) {
            Assistent assistent = new Assistent(List.of(
                    new HintAssistent(speler),
                    new StappenplanHulpmiddel(),
                    new Motivator()
            ));

            assistent.activeer();

        } else {
            System.out.println("❌ Assistent is niet beschikbaar in deze kamer.");
        }
    }

    private boolean verwerkKeyJoker(Speler speler, int kamerNummer) {
        if (!speler.heeftJoker() || !(speler.getGekozenJoker() instanceof KeyJoker keyJoker)) {
            System.out.println("❌ Je hebt niet de KeyJoker gekozen.");
            return true; // opnieuw proberen
        }

        if (keyJoker.magGebruikenInKamer(kamerNummer)) {
            keyJoker.gebruikInKamer(kamerNummer);
            speler.setKamerOvergeslagen(true); // ✅ Markeer dat de kamer wordt overgeslagen
            System.out.println("🔑 Je hebt de KeyJoker gebruikt! De kamer wordt overgeslagen.");
            return false; // vraag stoppen
        } else {
            System.out.println("❌ De KeyJoker mag alleen in kamer 2 of 4 worden gebruikt en slechts één keer.");
            return true;
        }
    }


    private void verwerkHintJoker(Speler speler, int kamerNummer) {
        if (speler.heeftJoker()) {
            CommandUitvoerder.voerUit(new GebruikJokerCommand(speler, kamerNummer));
        } else {
            System.out.println("❌ Je hebt geen hintjoker of je hebt hem al gebruikt.");
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
