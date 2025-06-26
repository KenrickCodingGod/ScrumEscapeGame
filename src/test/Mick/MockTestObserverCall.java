/*package test.Mick;

import game.Speler;
import game.observer.SpelerObserver;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MockTestObserverCall {

    @Test
    void testUpdateWordtAangeroepenOpObserver() {

        Speler speler = new Speler();
        SpelerObserver observerMock = mock(SpelerObserver.class);

        speler.voegObserverToe(observerMock);


        speler.veranderPositie(3);


        verify(observerMock, times(1)).update(speler);
    }
} */