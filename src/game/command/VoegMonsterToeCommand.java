package game.command;

import game.Speler;
import game.Monster;

public class VoegMonsterToeCommand implements SpelerCommand {
    private final Speler speler;
    private final Monster monster;

    public VoegMonsterToeCommand(Speler speler, Monster monster) {
        this.speler = speler;
        this.monster = monster;
    }

    @Override
    public void execute() {
        speler.voegMonsterToe(monster);
    }
}
