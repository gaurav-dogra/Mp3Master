package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SongDaoImplTest {

    private SongDaoImpl dao;
    private final Song song1 = new Song(1, "temp1", "temp1", "temp1", "temp1");
    private final Song song2 = new Song(2, "temp2", "temp2", "temp2", "temp2");

    @BeforeEach
    void init() {
        dao = new SongDaoImpl();
        dao.connect("TEMP_TEST_DB");
        dao.save(song1);
        dao.save(song2);
    }

    @Test
    void connect() {
        assertTrue(dao.connect("TEMP_TEST_DB"));
    }

    @Test
    void get() {
        Optional<Song> optional = dao.get(1);
        if (optional.isPresent()) {
            assertEquals(song1, optional.get());
        } else {
            fail("Not able to find song with id = 1 in the database");
        }
    }

    @Test
    void getAll() {
        List<Song> songsFromDB = dao.getAll();
        List<Song> songs = List.of(song1, song2);
        assertTrue(songsFromDB.containsAll(songs) && songs.containsAll(songsFromDB));
    }

    @Test
    void save() {
        Song song3 = new Song(3, "temp3", "temp3", "temp3", "temp3");
        dao.save(song3);
        List<Song> songsFromDB = dao.getAll();
        List<Song> songs = List.of(song1, song2, song3);
        assertTrue(songsFromDB.containsAll(songs) && songs.containsAll(songsFromDB));
    }

    @Test
    void delete() {
        dao.delete(song1);
        List<Song> songsFromDB = dao.getAll();
        List<Song> songs = List.of(song2);
        assertTrue(songsFromDB.containsAll(songs) && songs.containsAll(songsFromDB));
    }
}