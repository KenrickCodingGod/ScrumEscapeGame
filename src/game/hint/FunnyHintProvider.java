
package game.hint;

public class FunnyHintProvider implements HintProvider {

    public Hint geefHint() {

        return new FunnyHint();
    }
}