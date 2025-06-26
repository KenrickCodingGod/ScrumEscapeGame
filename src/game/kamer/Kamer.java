package game.kamer;

import game.Speler;
import game.vraag.Vraag;
import game.voorwerp.Voorwerp;

public class Kamer {
    private final String naam;
    private final Vraag vraag;
         private final Voorwerp voorwerp;

    public Kamer(String naam, Vraag vraag, Voorwerp voorwerp) {


        this.naam = naam;

        this.vraag = vraag;
        this.voorwerp = voorwerp;
    }

      public boolean voerUit(Speler speler) {
        System.out.println(getBeschrijving());
        return vraag.stelVraag(speler);
    }

    public String getBeschrijving() {
        return "Je bent in de kamer: " + naam;
    }
        public String getNaam() {
        return naam;
    }


    public Voorwerp getVoorwerp() {
        return voorwerp;
    }

    public Vraag getVraag() {
        return vraag;
    }




    @Override
    public String toString() {
        return "Kamer{" +
                "naam='" + naam + '\'' +
                ", voorwerp=" + (voorwerp != null ? voorwerp.getClass().getSimpleName() : "geen") +
                '}';
    }
}
