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
    void print() {
        System.out.println(song1);
        System.out.println(song5);
    }

    @Test
    void testToString() {
        assertEquals(song1.toString(), "Song{id=1, title='Willow', artist='Paul Schwartz & Mario Grigorov', " +
                "album='Aria 1', year='1997'}", "Incorrect string representation");
        assertEquals(song5.toString(), "Song{id=n/a, title='n/a', artist='n/a', album='n/a', year='n/a'}",
                "Incorrect string representation");
        assertEquals(song1.toString(), song2.toString(), "Incorrect string representation");
        assertNotEquals(song2.toString(), song3.toString(), "Incorrect string representation");
        assertNotEquals(song1.toString(), "Song{id=1, title='Willow', " +
                "artist='Paul Schwartz & Mario Grigorov ', album='Aria 1', year='1997'}",
                "Incorrect string representation");
    }

    @Test
    void testEquals() {
        assertEquals(song2, song1, song1 + " should be equal to " + song2);
        assertNotEquals(song1, song3, song3 + " should not be equal to " + song1);
        assertNotEquals(song4, song1, song3 + " should not be equal to " + song4);
    }

    @Test
    void testHashCode() {
        assertEquals(Objects.hash("1", "Willow", "Paul Schwartz & Mario Grigorov", "Aria 1", "1997"),
                song1.hashCode(), "Hash code is not correct");

        assertNotEquals(song1.hashCode(), song3.hashCode(),
                "Hash codes of " + song1 + " and " + song3 + " must be different");
        assertEquals(song1.hashCode(), song2.hashCode(),
                "Hash codes of  " + song1 + " and " + song3 + " must be the same");
    }

    @Test
    void getId() {
        assertEquals(song1.getId(), "1", "Incorrect id");
        assertEquals(song5.getId(), "n/a", "Incorrect id");
    }

    @Test
    void getTitle() {
        assertEquals(song1.getTitle(), "Willow", "Incorrect title");
        assertEquals(song5.getTitle(), "n/a", "Incorrect title");
    }

    @Test
    void getArtist() {
        assertEquals(song1.getArtist(), "Paul Schwartz & Mario Grigorov", "Incorrect artist");
        assertEquals(song5.getArtist(), "n/a", "Incorrect artist");
    }

    @Test
    void getAlbum() {
        assertEquals(song1.getAlbum(), "Aria 1", "Incorrect album");
        assertEquals(song5.getAlbum(), "n/a", "Incorrect album");
    }

    @Test
    void getYear() {
        assertEquals(song1.getYear(), "1997", "Incorrect year");
        assertEquals(song5.getYear(), "n/a", "Incorrect year");
    }
}