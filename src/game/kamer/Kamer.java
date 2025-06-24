package game.kamer;

import game.Speler;
import game.voorwerp.Readable;
import game.vraag.Vraag;
import game.voorwerp.*;

import java.util.Scanner;

public class Kamer {
    private final int Kamernummer;
    private final String naam;
    protected final Vraag vraag;
    private final Object voorwerp;

    public Kamer(int Kamernummer, String naam, Vraag vraag, Object voorwerp) {
        this.Kamernummer = Kamernummer;
        this.naam = naam;
        this.vraag = vraag;
        this.voorwerp = voorwerp;
    }

    public int getKamerNummer() {
        return Kamernummer;
    }

    public String getNaam() {
        return naam;
    }

    public Object getVoorwerp() {
        return voorwerp;
    }

    public boolean voerUit(Speler speler) {
        System.out.println("Je bent in kamer " + Kamernummer + ": " + naam);

        if (voorwerp instanceof Readable readable) {
            System.out.println("Je hebt een boek gevonden in deze kamer. Wil je hem lezen? (ja/nee)");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().equalsIgnoreCase("ja")) {
                System.out.println("📖 " + readable.showMessage());
            }
        }

        return vraag.stelVraag(speler);
    }
}
