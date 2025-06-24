package game.command;

public class CommandUitvoerder {
    public static void voerUit(SpelerCommand command) {
        command.execute();
    }
}