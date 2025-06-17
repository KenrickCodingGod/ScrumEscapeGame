package game.hint;

public class HelpHint implements Hint {
    private final String inhoud;

    public HelpHint(String inhoud) {
        this.inhoud = inhoud;
    }

    public String geefHint() {
        return inhoud;
    }
}
