package game.hint;

public class HelpHintProvider implements HintProvider {
    private final String tip;

    public HelpHintProvider(String tip) {
        this.tip = tip;
    }

    public Hint geefHint() {
        return new HelpHint(tip);
    }
}
