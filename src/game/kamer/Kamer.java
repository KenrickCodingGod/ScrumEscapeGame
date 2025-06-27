package game.kamer;

import game.Monster;
import game.Speler;
import game.voorwerp.Readable;
import game.voorwerp.Weapon;
import game.vraag.Vraag;

public abstract class Kamer {
    private final int kamerNummer;
    private final String naam;
      protected final Vraag vraag;
    private final Weapon zwaard;
    private final Readable boek;
    private final String hint;
    private final Monster monster;



    public Kamer(int kamerNummer, String naam, Vraag vraag, Weapon zwaard, Readable boek, String hint, Monster monster) {
        this.kamerNummer = kamerNummer;
        this.naam = naam;

        this.vraag = vraag;
        this.zwaard = zwaard;
        this.boek = boek;
        this.hint = hint;
        this.monster = monster;
    }

    public int getKamerNummer() { return kamerNummer; }
      public String getNaam() { return naam; }
       public Weapon getZwaard() { return zwaard; }
    public Readable getBoek() { return boek; }

    public String getHint() { return hint; }
    public Monster getMonster() { return monster; }

    public abstract boolean voerUit(Speler speler);
}
