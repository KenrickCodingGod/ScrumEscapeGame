package test.Mick;

import game.GameStatus;
import game.Monster;
import game.Speler;
import game.observer.GameStatusObserver;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StubTestGameStatusObserver {

    @Test
    void testObserverGeeftJuisteStatusTerugMetMonster() {
        // Arrange (maak een stub Speler)
        Speler stubSpeler = mock(Speler.class);
        when(stubSpeler.getPositie()).thenReturn(2);
        when(stubSpeler.getMonsters()).thenReturn(List.of(new Monster("Zombie")));

        GameStatusObserver observer = new GameStatusObserver();

        // Act
        observer.update(stubSpeler);
        GameStatus status = observer.getStatus();

        // Assert
        assertEquals(2, status.positie);
        assertEquals("Zombie", status.laatsteMonster);
    }

    @Test
    void testObserverGeeftStatusZonderMonster() {
        Speler stubSpeler = mock(Speler.class);
        when(stubSpeler.getPositie()).thenReturn(5);
        when(stubSpeler.getMonsters()).thenReturn(List.of());

        GameStatusObserver observer = new GameStatusObserver();

        observer.update(stubSpeler);
        GameStatus status = observer.getStatus();

        assertEquals(5, status.positie);
        assertEquals("Geen actieve monsters.", status.laatsteMonster);
    }
}
