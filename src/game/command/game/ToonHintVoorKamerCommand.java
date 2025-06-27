package game.command.game;

import game.command.Command;
import game.GameUI;
import game.hint.Hint;
import game.hint.HintFactory;
import game.kamer.Kamer;

public class ToonHintVoorKamerCommand implements Command {
    private final Kamer kamer;
    private final GameUI ui;

    public ToonHintVoorKamerCommand(Kamer kamer, GameUI ui) {
        this.kamer = kamer;
        this.ui = ui;
    }

    @Override
    public void voerUit() {
        HintFactory factory = new HintFactory();
        Hint hint = factory.getRandomProvider(kamer.getHint()).geefHint();
        ui.toon(hint.geefHint());
    }
}
