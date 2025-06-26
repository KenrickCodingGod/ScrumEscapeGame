package game.assistent;

import java.util.List;

public class Assistent {
    private final List<AssistentComponent> componenten;

    public Assistent(List<AssistentComponent> componenten) {
        this.componenten = componenten;
    }

    public void activeer() {
        System.out.println("🧑‍💻 Assistent geactiveerd!");
        for (AssistentComponent component : componenten) {
            component.activeer();
        }
    }
}
