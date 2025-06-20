package test.Mick;
package game.observer;

import game.Monster;
import game.Speler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class MockTest {

    private GameStatusObserver observer;
    private Speler speler;

    @BeforeEach
    void setUp() {
        observer = new GameStatusObserver();
        speler = mock(Speler.class);
    }

    @Test
    void testUpdateWithMonster() {
        // Arrange
        when(speler.getPositie()).thenReturn(2);
        Monster monster = new Monster("Draak", "Vuurspuwend");
        when(speler.getMonsters()).thenReturn(List.of(monster));

        // Act
        observer.update(speler);

        // Assert
        verify(speler).getPositie();
        verify(speler).getMonsters();
    }

    @Test
    void testUpdateWithoutMonster() {
        // Arrange
        when(speler.getPositie()).thenReturn(5);
        when(speler.getMonsters()).thenReturn(Collections.emptyList());

        // Act
        observer.update(speler);

        // Assert
        verify(speler).getPositie();
        verify(speler).getMonsters();
    }
}

public class MockTest {
}
