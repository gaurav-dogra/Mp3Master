package model;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SongTest {
    Song song1 = new Song("1", "Willow", "Paul Schwartz & Mario Grigorov", "Aria 1", "1997");
    Song song2 = new Song("1", "Willow", "Paul Schwartz & Mario Grigorov", "Aria 1", "1997");
    Song song3 = new Song("2", "Un Bel Di", "Paul Schwartz & Mario Grigorov", "Aria 1", "1997");
    Song song4 = null;
    Song song5 = new Song(null, null, null, null, null);

    @Test
    void testToString() {
        assertEquals(song1.toString(), "Song(id=1, title=Willow, artist=Paul Schwartz & Mario Grigorov, " +
                "album=Aria 1, year=1997)", "Incorrect string representation");
        assertEquals(song5.toString(), "Song(id=null, title=null, artist=null, album=null, year=null)",
                "Incorrect string representation");
        assertEquals(song1.toString(), song2.toString(), "Incorrect string representation");
        assertNotEquals(song2.toString(), song3.toString(), "Incorrect string representation");
        // one extra blank space Mario and Grigorov
        assertNotEquals(song1.toString(), "Song(id=1, title=Willow, " +
                "artist=Paul Schwartz & Mario  Grigorov, album=Aria 1, year=1997)",
                "Incorrect string representation");
    }

    @Test
    void testEquals() {
        assertEquals(song2, song1, song1 + " should be equal to " + song2);
        assertNotEquals(song1, song3, song3 + " should not be equal to " + song1);
        assertNotEquals(song4, song1, song3 + " should not be equal to " + song4);
    }

    @Test
    void getId() {
        assertEquals(song1.getId(), "1", "Incorrect id");
        assertNull(song5.getId(), "Incorrect id");
    }

    @Test
    void getTitle() {
        assertEquals(song1.getTitle(), "Willow", "Incorrect title");
        assertNull(song5.getTitle(), "Incorrect title");
    }

    @Test
    void getArtist() {
        assertEquals(song1.getArtist(), "Paul Schwartz & Mario Grigorov", "Incorrect artist");
        assertNull(song5.getArtist(), "Incorrect artist");
    }

    @Test
    void getAlbum() {
        assertEquals(song1.getAlbum(), "Aria 1", "Incorrect album");
        assertNull(song5.getAlbum(), "Incorrect album");
    }

    @Test
    void getYear() {
        assertEquals(song1.getYear(), "1997", "Incorrect year");
        assertNull(song5.getYear(), "Incorrect year");
    }
}