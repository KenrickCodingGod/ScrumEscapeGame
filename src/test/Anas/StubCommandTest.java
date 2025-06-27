/*package test.Anas;

import game.Monster;
import game.Speler;
import game.command.CommandUitvoerder;
import game.command.SetPositieCommand;
import game.command.VoegMonsterToeCommand;
import game.kamer.Kamer;
import game.kamer.NormaleKamer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class StubCommandTest {

    static class StubSpeler extends Speler {
        private Kamer huidigeKamer;
        private List<Monster> monsters = new ArrayList<>();

        @Override
        public void setHuidigeKamer(Kamer kamer){
            this.huidigeKamer = kamer;
        }

        @Override
        public Kamer getHuidigeKamer() {
            return huidigeKamer;
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
        Kamer kamer = new NormaleKamer(7, "Test Kamer", null, null, null, null, null);
        CommandUitvoerder.voerUit(new SetPositieCommand(speler, kamer));
        assertEquals(kamer, speler.getHuidigeKamer());
    }

    @Test
    void testVoegMonsterToeCommandMetStub() {
        StubSpeler speler = new StubSpeler();
        Monster m = new Monster("Scopezilla", "Te veel werk zonder afstemming toegevoegd.");
        CommandUitvoerder.voerUit(new VoegMonsterToeCommand(speler, m));
        assertTrue(speler.getMonsters().contains(m));
    }
}*/