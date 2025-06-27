package game.command.speler;

import game.Speler;
import game.Monster;
import game.command.Command;

public class VoegMonsterToeCommand implements Command {
    private final Speler speler;
    private final Monster monster;

    public VoegMonsterToeCommand(Speler speler, Monster monster) {
        this.speler = speler;
        this.monster = monster;
    }

    @Override
    public void voerUit() {
        speler.voegMonsterToe(monster);
    }
}
