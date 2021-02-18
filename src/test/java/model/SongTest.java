package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SongTest {
    Song song1 = new Song(1, "Willow", "Paul Schwartz & Mario Grigorov", "Aria 1", "1997");
    Song song2 = new Song(2, "Willow", "Paul Schwartz & Mario Grigorov", "Aria 1", "1997");
    Song song3 = new Song(2, "Un Bel Di", "Paul Schwartz & Mario Grigorov", "Aria 1", "1997");
    Song song4 = null;

    @Test
    void testEquals() {
        assertEquals(song2, song1, song1 + " should be equal to " + song2);
        assertNotEquals(song1, song3, song3 + " should not be equal to " + song1);
        assertNotEquals(song4, song1, song3 + " should not be equal to " + song4);
    }
}