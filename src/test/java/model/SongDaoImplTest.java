package model;

import org.junit.jupiter.api.*;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SongDaoImplTest {

    private SongDaoImpl dao;
    private final Song song1 = new Song(1, "temp1", "temp1", "temp1", "temp1");
    private final Song song2 = new Song(2, "temp2", "temp2", "temp2", "temp2");

    @BeforeEach
    void init() {
        dao = new SongDaoImpl();
        dao.connect("TEMP_TEST_DB.db");
        dao.save(song1);
        dao.save(song2);
    }

    @Test
    @Order(1)
    void connect() {
        assertTrue(dao.connect("TEMP_TEST_DB.db"));
    }

    @Test
    @Order(2)
    void get() {
        Optional<Song> optional = dao.get(1);
        if (optional.isPresent()) {
            assertEquals(song1, optional.get());
        } else {
            fail("Not able to find song with id = 1 in the database");
        }
    }

    @Test
    @Order(3)
    void getAll() {
        List<Song> songsFromDB = dao.getAll();
        List<Song> songs = List.of(song1, song2);
        assertTrue(songsFromDB.containsAll(songs) && songs.containsAll(songsFromDB));
    }

    @Test
    @Order(4)
    void save() {
        Song song3 = new Song(3, "temp3", "temp3", "temp3", "temp3");
        dao.save(song3);
        List<Song> songsFromDB = dao.getAll();
        List<Song> songs = List.of(song1, song2, song3);
        assertTrue(songsFromDB.containsAll(songs) && songs.containsAll(songsFromDB));
        dao.delete(song3);
    }

    @Test
    @Order(5)
    void delete() {
        dao.delete(song1);
        List<Song> songsFromDB = dao.getAll();
        List<Song> songs = List.of(song2);
        assertTrue(songsFromDB.containsAll(songs) && songs.containsAll(songsFromDB));
    }

    @AfterAll
    static void cleanup() {
        File db_file = new File("TEMP_TEST_DB.db");
        db_file.deleteOnExit();
    }
}