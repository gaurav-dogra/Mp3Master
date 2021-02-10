package model;

import java.util.Objects;

public class Song {
    private static final String NOT_AVAILABLE = "n/a";
    private final String id;
    private final String title;
    private final String artist;
    private final String album;
    private final String year;

    public Song(String id, String title, String artist, String album, String year) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", artist='" + getArtist() + '\'' +
                ", album='" + getAlbum() + '\'' +
                ", year='" + getYear() + '\'' +
                '}';
    }

    private String getStringOrNA(String str) {
        return str == null || str.isBlank() ? NOT_AVAILABLE : str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(id, song.id) &&
                Objects.equals(title, song.title) &&
                Objects.equals(artist, song.artist) &&
                Objects.equals(album, song.album) &&
                Objects.equals(year, song.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, artist, album, year);
    }

    public String getId() {
        return getStringOrNA(id);
    }

    public String getTitle() {
        return getStringOrNA(title);
    }

    public String getArtist() {
        return getStringOrNA(artist);
    }

    public String getAlbum() {
        return getStringOrNA(album);
    }

    public String getYear() {
        return getStringOrNA(year);
    }
}
