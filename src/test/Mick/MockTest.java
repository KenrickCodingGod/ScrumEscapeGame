package test.Mick;
package game.observer;

import game.Speler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MockTest {

    @Test
    void testSpelerWaarschuwtMockObserver() {
        // Arrange: maak een speler en een mock observer
        Speler speler = new Speler();
        MockObserver mockObserver = new MockObserver();
        speler.addObserver(mockObserver);

        // Act: roep notifyObservers aan
        speler.notifyObservers();

        // Assert: controleer of de mock observer is opgeroepen
        assertTrue(mockObserver.isOpgeroepen, "De observer moet zijn opgeroepen");
    }
}




   

