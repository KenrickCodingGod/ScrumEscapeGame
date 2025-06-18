package test.Semih;

import game.assistent.Assistent;
import game.assistent.EducatiefHulpmiddel;
import game.assistent.HintAssistent;
import game.assistent.Motivator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MockTestAssistent {

    static class MockHintAssistent extends HintAssistent {
        boolean voerUitCalled = false;

        MockHintAssistent() {
            super(0); // kamerPositie irrelevant hier
        }

        @Override
        public void voerUit() {
            voerUitCalled = true;
        }
    }

    static class MockEducatiefHulpmiddel implements EducatiefHulpmiddel {
        boolean toonCalled = false;

        @Override
        public void toon() {
            toonCalled = true;
        }
    }

    static class MockMotivator extends Motivator {
        boolean toonMeldingCalled = false;

        @Override
        public void toonMelding() {
            toonMeldingCalled = true;
        }
    }

    @Test
    void testActiveerRoepAfhankelijkhedenAan() {
        //maakt mocks aan
        MockHintAssistent mockHint = new MockHintAssistent();
        MockEducatiefHulpmiddel mockHulpmiddel = new MockEducatiefHulpmiddel();
        MockMotivator mockMotivator = new MockMotivator();

        Assistent assistent = new Assistent(mockHint, mockHulpmiddel, mockMotivator);

        assistent.activeer();

        assertTrue(mockHint.voerUitCalled, "HintAssistent.voerUit() moet aangeroepen worden");
        assertTrue(mockHulpmiddel.toonCalled, "EducatiefHulpmiddel.toon() moet aangeroepen worden");
        assertTrue(mockMotivator.toonMeldingCalled, "Motivator.toonMelding() moet aangeroepen worden");
    }
}
