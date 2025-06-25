package test.Anas;

import game.Monster;
import game.Speler;
import game.command.CommandUitvoerder;
import game.command.SetPositieCommand;
import game.command.VoegMonsterToeCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class StubCommandTest {

    static class StubSpeler extends Speler {
        private int positie;
        private List<Monster> monsters = new ArrayList<>();

        @Override
        public void setPositie(int positie) {
            this.positie = positie;
        }

        @Override
        public int getPositie() {
            return this.positie;
        }

        @Override
        public void voegMonsterToe(Monster monster) {
            monsters.add(monster);
        }

        @Override
        public List<Monster> getMonsters() {
            return monsters;
        }
    }

    @Test
    void testSetPositieCommandMetStub() {
        StubSpeler speler = new StubSpeler();
        CommandUitvoerder.voerUit(new SetPositieCommand(speler, 7));
        assertEquals(7, speler.getPositie());
    }

    @Test
    void testVoegMonsterToeCommandMetStub() {
        StubSpeler speler = new StubSpeler();
        Monster m = new Monster("Scopezilla", "Te veel werk zonder afstemming toegevoegd.");
        CommandUitvoerder.voerUit(new VoegMonsterToeCommand(speler, m));
        assertTrue(speler.getMonsters().contains(m));
    }
}