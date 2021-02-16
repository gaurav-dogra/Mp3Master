import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderScanner {
    public static List<File> scanMp3(String folderName) {
        List<File> mp3Files = new ArrayList<>();
        scanMp3(new File(folderName), mp3Files);
        return mp3Files;
    }

    private static void scanMp3(File folder, List<File> mp3Files) {
        File[] files = folder.listFiles();

        if (files == null) { return; }

        for (File currentFile : files) {
            if (currentFile.isDirectory()) {
                scanMp3(currentFile, mp3Files);
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
