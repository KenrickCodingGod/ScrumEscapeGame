package game.hint;

import java.util.Random;
import java.util.List;

public class HintFactory {
    public HintProvider getRandomProvider(String inhoudelijkeHint) {
        List<HintProvider> providers = List.of(
                new HelpHintProvider(inhoudelijkeHint),
                new FunnyHintProvider()
        );
        Random random = new Random();
        return providers.get(random.nextInt(providers.size()));
    }
}
