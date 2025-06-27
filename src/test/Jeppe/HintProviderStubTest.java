package test.Jeppe;

import static org.junit.Assert.assertEquals;

import game.hint.Hint;
import game.hint.HintProvider;
import org.junit.jupiter.api.Test;

public class HintProviderStubTest {

    // stub class die altijd dezelfde hint terug geeft
    static class StubHintProvider implements HintProvider {
        @Override
        public Hint geefHint() {
            return () -> "Stub: vaste hint"; // vaste hint
        }
    }

    @Test
    void testStubGeeftVasteHint() {
        StubHintProvider stub = new StubHintProvider(); //  maak stub aan
        Hint hint = stub.geefHint();//vraag de hint op

        //check of de hint precies deze tekst terug geeft
        assertEquals("Stub: vaste hint", hint.geefHint());
    }
}
