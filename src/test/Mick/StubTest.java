package test.Mick;
package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StubTest {

    @Test
    void testGetNaam() {
        Monster monster = new Monster("Draak", "Een vuurspuwend monster");
        assertEquals("Draak", monster.getNaam());
    }

    @Test
    void testToString() {
        Monster monster = new Monster("Trol", "Een groot en sterk monster");
        assertEquals("Trol", monster.toString());
    }

    // Optioneel: geen assert, maar laat zien hoe toonMonster werkt
    @Test
    void testToonMonster() {
        Monster monster = new Monster("Zombie", "Langzaam maar eng");
        monster.toonMonster();  // Dit print alleen naar de console
    }
}


