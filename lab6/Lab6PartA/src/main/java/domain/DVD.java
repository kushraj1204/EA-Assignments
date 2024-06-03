package domain;

import jakarta.persistence.Entity;

/**
 * @author kush
 */
@Entity
public class DVD extends Product {
    private String genre;

    public DVD(String genre) {
        this.genre = genre;
    }
    public DVD() {
    }

    public DVD(String name, String description, double price, String genre) {
        super(name, description, price);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
