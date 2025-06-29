package test.Mick;
import game.Speler;
import game.observer.SpelerObserver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MockTestObserverCall {


    static class ObserverMock implements SpelerObserver {
        boolean updateAangeroepen = false;

        @Override
        public void update(Speler speler) {
            updateAangeroepen = true;
        }
    }

    @Test
    void testUpdateWordtAangeroepenOpObserver() {
        Speler speler = new Speler();
        ObserverMock observerMock = new ObserverMock();

        speler.attach(observerMock);


        speler.setHuidigeKamer(null);

        assertTrue(observerMock.updateAangeroepen, "Observer update werd niet aangeroepen");
    }
}
