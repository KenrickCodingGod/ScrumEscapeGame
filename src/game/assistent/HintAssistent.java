package game.assistent;

import game.Speler;

public class HintAssistent implements AssistentComponent {
    private final Speler speler;

    public HintAssistent(Speler speler) {
        this.speler = speler;
    }

    @Override
    public void activeer() {
        int kamerPositie = speler.getHuidigeKamer().getKamerNummer();

        switch (kamerPositie) {
            case 1 -> System.out.println("💡 Hint: Denk aan de drie Scrumrollen en hun verantwoordelijkheden.");
            case 3 -> System.out.println("💡 Hint: Denk aan hoe het team zich aanpast aan veranderingen tijdens de sprint.");
            default -> System.out.println("💡 Hint: Algemeen Scrumprincipe.");
        }
    }
}
