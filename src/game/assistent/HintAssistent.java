package game.assistent;



import java.util.List;
import java.util.Random;

public class HintAssistent implements AssistentComponent {
    private static final List<String> hints = List.of(
            "💡 Hint: Denk aan de drie Scrumrollen en hun verantwoordelijkheden.",
            "💡 Hint: Denk aan hoe het team zich aanpast aan veranderingen tijdens de sprint."
    );



    @Override
    public void activeer() {
        String randomHint = hints.get(new Random().nextInt(hints.size()));
        System.out.println(randomHint);
    }
}