package model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SongDaoImpl implements Dao<Song> {

    private final String TABLE_NAME = "songs";
    private final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + " id TEXT,"
            + " title TEXT,"
            + " artist TEXT,"
            + " album TEXT,"
            + " year TEXT"
            + ")";
    private final String INSERT = "INSERT INTO " + TABLE_NAME + " (id, title, artist, album, year)" +
            "VALUES(?, ?, ?, ?, ?)";
    private final String FIND_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
    private Connection connection;
    private final List<Song> songs = new ArrayList<>();

    @Override
    public boolean connect() {
        try {
            connection = ConnectionFactory.getConnection();
        } catch (Exception e) {
            System.out.println("Failed to connect to database");
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
