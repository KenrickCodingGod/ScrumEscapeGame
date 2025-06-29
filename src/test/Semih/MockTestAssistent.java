package test.Semih;

import game.assistent.Assistent;
import game.assistent.AssistentComponent;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MockTestAssistent {

    static class MockComponent implements AssistentComponent {
        boolean activeerCalled = false;

        @Override
        public void activeer() {
            activeerCalled = true;
        }
    }

    @Test
    void testActiveerRoepAfhankelijkhedenAan() {
        MockComponent mockHint = new MockComponent();
        MockComponent mockHulpmiddel = new MockComponent();
        MockComponent mockMotivator = new MockComponent();

        Assistent assistent = new Assistent(List.of(mockHint, mockHulpmiddel, mockMotivator));


        assistent.activeer();


        assertTrue(mockHint.activeerCalled, "Hint-component moet geactiveerd worden");
        assertTrue(mockHulpmiddel.activeerCalled, "Hulpmiddel-component moet geactiveerd worden");
        assertTrue(mockMotivator.activeerCalled, "Motivator-component moet geactiveerd worden");
    }
}
