package game.assistent;

public class StappenplanHulpmiddel implements AssistentComponent {
    @Override
    public void activeer() {
        System.out.println("📘 Stappenplan: 1) Lees de vraag goed. 2) Denk aan de rollen. 3) Kies het juiste antwoord.");
    }
}
