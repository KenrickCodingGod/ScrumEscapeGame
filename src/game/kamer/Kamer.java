package game.kamer;

import game.Speler;
import game.voorwerp.Readable;
import game.vraag.Vraag;
import game.voorwerp.*;



public class Kamer {
    private final int Kamernummer;
    private final String naam;
    protected final Vraag vraag;
    private final Weapon zwaard;
    private final Readable boek;

    public Kamer(int kamerNummer, String naam, Vraag vraag, Weapon zwaard, Readable boek) {
        this.Kamernummer = kamerNummer;
        this.naam = naam;
        this.vraag = vraag;
        this.zwaard = zwaard;
        this.boek = boek;
    }

    public int getKamerNummer() {
        return Kamernummer;
    }

    public String getNaam() {
        return naam;
    }

    public Weapon getZwaard() {
        return zwaard;
    }

    public Readable getBoek() {
        return boek;
    }

    public boolean voerUit(Speler speler) {
        System.out.println("Je bent in kamer " + Kamernummer + ": " + naam);
        return vraag.stelVraag(speler, this);
    }
}