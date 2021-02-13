package model;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Song {
    private final String id;
    private final String title;
    private final String artist;
    private final String album;
    private final String year;

}
