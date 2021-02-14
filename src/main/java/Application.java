import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Application {

    private final List<File> mp3Files = new ArrayList<>();

    public List<File> start(String[] args) {
        if (args == null || args.length == 0) {
            log.error("No file name passed");
            return null;
        }

        File file = Path.of(args[0]).toFile();
        readFolder(file);
        System.out.println(mp3Files.size());
        return mp3Files;
    }

    private void readFolder(File file) {
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                return;
            }
            for (File currentFile : files) {
                if (currentFile.isDirectory()) {
                    readFolder(currentFile);
                } else {
                    String name = currentFile.getName();
                    String extension = name.substring(name.lastIndexOf(".") + 1);
                    if (extension.equalsIgnoreCase("mp3")) {
                        mp3Files.add(currentFile);
                    }
                }
            }
        }
    }
}
