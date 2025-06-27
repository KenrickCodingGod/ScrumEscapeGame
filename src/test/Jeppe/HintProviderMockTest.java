/*package test.Jeppe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import game.hint.Hint;
import game.hint.HintProvider;
import org.junit.jupiter.api.Test;

public class HintProviderMockTest {

    // mock klasse om te controleren of geefHint() wordt aangeroepen
    static class MockHintProvider implements HintProvider {
        boolean geefHintCalled = false; // Houdt bij of methode is aangeroepen

        @Override
        public Hint geefHint() {
            geefHintCalled = true; // Zet true als deze methode wordt aangeroepen
            return () -> "Mock: hint"; // Geeft altijd dezelfde mock hint terug
        }
    }

    @Test
    void testGeefHintWordtAangeroepen() {
        MockHintProvider mock = new MockHintProvider();   //maak mock aan
        Hint hint = mock.geefHint(); // roep methode aan

        // contorleer of geefHint() is aangeroepen
        assertTrue("De methode geefHint() moet zijn aangeroepen", mock.geefHintCalled);
        //controleer of de hint de verwachte tekst geeft
        assertEquals("Mock: hint", hint.geefHint());
    }
}
*/