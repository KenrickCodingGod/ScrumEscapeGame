package test.Jeppe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import game.hint.Hint;
import game.hint.HintProvider;
import org.junit.jupiter.api.Test;

public class HintProviderMockTest {


    static class MockHintProvider implements HintProvider {
        boolean geefHintCalled = false;
        @Override
        public Hint geefHint() {
            geefHintCalled = true;
            return () -> "Mock: hint";
        }
    }

    @Test
    void testGeefHintWordtAangeroepen() {
        MockHintProvider mock = new MockHintProvider();   //maak mock aan
        Hint hint = mock.geefHint(); // roep methode aan


        assertTrue("De methode geefHint() moet zijn aangeroepen", mock.geefHintCalled);

        assertEquals("Mock: hint", hint.geefHint());
    }
}
