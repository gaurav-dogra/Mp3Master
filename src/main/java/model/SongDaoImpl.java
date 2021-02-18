package model;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SongDaoImpl implements Dao<Song> {

    public static final String JDBC_SQLITE = "jdbc:sqlite:";
    private final String TABLE_NAME = "songs";
    private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + " id INTEGER PRIMARY KEY,"
            + " title TEXT,"
            + " artist TEXT,"
            + " album TEXT,"
            + " year TEXT"
            + ")";
    private final String INSERT = "INSERT INTO " + TABLE_NAME + " (id, title, artist, album, year)" +
            "VALUES(?, ?, ?, ?, ?)";
    private final String GET_ALL_DATA = "SELECT * FROM " + TABLE_NAME;
    private final String DELETE_BY_ID = "DELETE FROM " + TABLE_NAME + " WHERE id=?";
    private Connection connection;

    @Override
    public boolean connect(String DB_NAME) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(JDBC_SQLITE + DB_NAME);
        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Optional<Song> get(int id) {
        List<Song> songs = this.getAll();
        return songs.stream().filter(s -> (id == s.getId())).findAny();
    }

    @Override
    public List<Song> getAll() {
        List<Song> songs = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(GET_ALL_DATA);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String album = rs.getString("album");
                String year = rs.getString("year");
                songs.add(new Song(id, title, artist, album, year));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    @Override
    public void save(Song song) {
        List<Song> songs = this.getAll();
        if (songs.contains(song)) {
            return;
        }
        try {
            PreparedStatement stmt = connection.prepareStatement(INSERT);
            stmt.setString(2, song.getTitle());
            stmt.setString(3, song.getArtist());
            stmt.setString(4, song.getAlbum());
            stmt.setString(5, song.getYear());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error! adding song to the database");
        }
    }

    @Override
    public void delete(Song song) {
        delete(song.getId());
    }

    private void delete(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement(DELETE_BY_ID);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
