package model;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Song {
    private final int id;
    private final String title;
    private final String artist;
    private final String album;
    private final String year;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (getTitle() != null ? !getTitle().equals(song.getTitle()) : song.getTitle() != null) return false;
        if (getArtist() != null ? !getArtist().equals(song.getArtist()) : song.getArtist() != null) return false;
        if (getAlbum() != null ? !getAlbum().equals(song.getAlbum()) : song.getAlbum() != null) return false;
        return getYear() != null ? getYear().equals(song.getYear()) : song.getYear() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getArtist() != null ? getArtist().hashCode() : 0);
        result = 31 * result + (getAlbum() != null ? getAlbum().hashCode() : 0);
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        return result;
    }
}
