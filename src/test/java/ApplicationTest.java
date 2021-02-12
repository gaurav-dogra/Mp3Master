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

@SuppressWarnings("CheckReturnValue")
class ApplicationTest {
    private static List<File> mp3Files;
    private static final String[] paths = {"sample", "sample/my1.mp3", "sample/my2.mp3",
            "sample/my3.mp3", "sample/my4.mp3", "sample/my5.mp3",
            "sample/myF1", "sample/myF2", "sample/myF2/my6.mp3",
            "sample/myF1/noMP3.jpg", "sample/myF2/my7.mp3", "sample/myF1/my8.mp3"};
    private static final List<File> expectedFiles = new ArrayList<>();
    private static final Application app = new Application();

    @BeforeAll
    static void init() {
        Arrays.stream(paths).map(Path::of).forEach((s) -> { if (s.toString().contains(".")) {
            try {
                s.toFile().createNewFile();
                expectedFiles.add(s.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            s.toFile().mkdir();
        }
        });

        mp3Files = app.start(paths);
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
        assertNull(app.start(null), "passing null breaks the app");
        assertNull(app.start(new String[0]), "passing empty array breaks the app");
    }
}