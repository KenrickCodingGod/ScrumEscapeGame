package test.Mick;
package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StubTest {

    @Test
void testUpdateMetStubSpeler() {
    // Gebruik de StubSpeler: altijd kamer 0, geen monsters
    observer.update(stubSpeler);

    // Controleer dat de stub inderdaad zijn vaste waarden heeft
    assertEquals(0, stubSpeler.getPositie());
    assertTrue(stubSpeler.getMonsters().isEmpty());

