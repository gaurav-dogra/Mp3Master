import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FolderScannerTest {

    private static List<File> mp3Files;
    private static final String[] tempTestData = {"sample", "sample/my1.mp3", "sample/my2.mp3",
            "sample/my3.mp3", "sample/my4.mp3", "sample/my5.mp3",
            "sample/myF1", "sample/myF2", "sample/myF2/my6.mp3",
            "sample/myF1/noMP3.jpg", "sample/myF2/my7.mp3", "sample/myF1/my8.mp3"};
    private static final List<File> expectedFiles = new ArrayList<>();

    @BeforeAll
    static void init() {
        Arrays.stream(tempTestData).map(Path::of).forEach((s) -> {
            if (s.toString().contains(".")) {
                try {
                    s.toFile().createNewFile();
                    if (s.toString().endsWith(".mp3")) {
                        expectedFiles.add(s.toFile());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                s.toFile().mkdir();
            }
        });

        mp3Files = FolderScanner.scanMp3(tempTestData[0]);
    }

    @AfterAll
    static void cleanUp() {
        new File("sample/myF1/my8.mp3").delete();
        new File("sample/myF1/noMP3.jpg").delete();
        new File("sample/myF1").delete();
        new File("sample/myF2/my6.mp3").delete();
        new File("sample/myF2/my7.mp3").delete();
        new File("sample/myF2").delete();
        new File("sample/my1.mp3").delete();
        new File("sample/my2.mp3").delete();
        new File("sample/my3.mp3").delete();
        new File("sample/my4.mp3").delete();
        new File("sample/my5.mp3").delete();
        new File("sample").delete();
    }

    @Test
    void start() {
        assertEquals(8, mp3Files.size(), "Expected: 8, Actual: " + mp3Files.size());
        assertTrue(expectedFiles.containsAll(mp3Files), "Files are different");
        assertEquals(0, FolderScanner.scanMp3("").size(), "empty Str should have empty list");
    }
}