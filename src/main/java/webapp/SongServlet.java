package webapp;

import model.Song;
import model.SongDaoImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SongServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String message = new Scanner(Objects.requireNonNull(HomeServlet.class.getClassLoader()
                .getResourceAsStream("song_table.html")), StandardCharsets.UTF_8)
                .useDelimiter("\\Z")
                .next();

        SongDaoImpl songDao = new SongDaoImpl();
        songDao.connect("Mp3Master.db");
        List<Song> songs = songDao.getAll();

        StringBuilder builder = new StringBuilder();

        for (Song song : songs) {
            int id = song.getId();
            String title = song.getTitle();
            String artist = song.getArtist();
            String album = song.getAlbum();
            String year = song.getYear();
            builder.append("<tr>")
                    .append("<td>")
                    .append(id).append("</td><td>")
                    .append(title).append("</td><td>")
                    .append(artist).append("</td><td>")
                    .append(album).append("</td><td>")
                    .append(year).append("</td>")
                    .append("</tr>");
        }

        String[] segments = message.split("<!--Data rows-->");
        response.getWriter().println(segments[0] + builder.toString() + segments[1]);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
