package model;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SongDaoImpl implements Dao<Song> {

    public static final String JDBC_SQLITE = "jdbc:sqlite:";
    public static final String DB_NAME = "Mp3Master.db";
    private final String TABLE_NAME = "songs";
    private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + " id TEXT,"
            + " title TEXT,"
            + " artist TEXT,"
            + " album TEXT,"
            + " year TEXT"
            + ")";
    private final String INSERT = "INSERT INTO " + TABLE_NAME + " (id, title, artist, album, year)" +
            "VALUES(?, ?, ?, ?, ?)";
    private final String FIND_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
    private final List<Song> songs = new ArrayList<>();

    @Override
    public boolean connect() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(JDBC_SQLITE + DB_NAME);
        try (final Connection connection = dataSource.getConnection();
             final Statement stmt = connection.createStatement()) {
            stmt.execute(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Optional<Song> get(String id) {
        Song song = songs.stream()
                .filter(s -> id.equals(s.getId()))
                .findAny()
                .orElse(null);
        return Optional.ofNullable(song);
    }

    @Override
    public List<Song> getAll() {
        return songs;
    }

    @Override
    public void save(Song song) {
        songs.add(song);
    }

    @Override
    public void delete(Song song) {
        songs.remove(song);
    }
}
