import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.mpatric.mp3agic.ID3v1;
import lombok.extern.slf4j.Slf4j;
import model.Song;
import model.SongDaoImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Application {

    private List<File> mp3Files = new ArrayList<>();
    public static final String DB_NAME = "Mp3Master.db";

    public void start(String[] args) {
        if (!isArgsPresent(args)) {
            return;
        }
        mp3Files = FolderScanner.scanMp3(args[0]);
        saveMP3FilesMetadata();
    }

    private void saveMP3FilesMetadata() {
        SongDaoImpl dao = new SongDaoImpl();
        if (dao.connect(DB_NAME)) {
            System.out.println("connected to the database");
        } else {
            System.out.println("Error connecting to the database");
        }

        for (File aMp3 : mp3Files) {

            Mp3File mp3file;
            try {
                mp3file = new Mp3File(aMp3);
            } catch (IOException | UnsupportedTagException | InvalidDataException e) {
                System.out.println("Error reading tags of " + aMp3.getName());
                continue;
            }

            if (!mp3file.hasId3v1Tag()) {
                continue;
            }
            ID3v1 id3v1Tag = mp3file.getId3v1Tag();
            String title = id3v1Tag.getTitle();
            String artist = id3v1Tag.getArtist();
            String album = id3v1Tag.getAlbum();
            String year = id3v1Tag.getYear();
            dao.save(new Song(-1, title, artist, album, year));
        }
    }

    private boolean isArgsPresent(String[] args) {
        if (args == null || args.length == 0) {
            log.error("No file name passed");
            return false;
        }
        return true;
    }

}
