package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;

/**
 * @author kush
 */
@Entity
@NamedQuery(name = "CD.findByArtist",query = "select cd from CD cd where cd.artist=:artist")
public class CD extends Product{
    private String artist;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public CD(String artist) {
        this.artist = artist;
    }
    public CD() {
    }

    public CD(String name, String description, double price, String artist) {
        super(name, description, price);
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "CD{" +
                "super="+super.toString()+
                "artist='" + artist + '\'' +
                '}';
    }
}
