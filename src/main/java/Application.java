import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.mpatric.mp3agic.ID3v1;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Application {

    private List<File> mp3Files = new ArrayList<>();

    public void start(String[] args) {
        if(!isArgsPresent(args)) { return; }
        mp3Files = FolderScanner.scanMp3(args[0]);
        saveMP3FilesMetadata();
    }

    private void saveMP3FilesMetadata() {
        for (File aMp3 : mp3Files) {
            Mp3File mp3file = null;
            try {
                mp3file = new Mp3File(aMp3);
            } catch (IOException | UnsupportedTagException | InvalidDataException e) {
                System.out.println(aMp3.getName() + " does not have a tag");
            }
            assert mp3file != null;
            if (mp3file.hasId3v1Tag()) {
                ID3v1 id3v1Tag = mp3file.getId3v1Tag();
                System.out.println("Artist: " + id3v1Tag.getArtist());
                System.out.println("Title: " + id3v1Tag.getTitle());
                System.out.println("Album: " + id3v1Tag.getAlbum());
                System.out.println("Year: " + id3v1Tag.getYear());
            } else {
                System.out.println(aMp3.getName() + " does not have a tag");
            }
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
