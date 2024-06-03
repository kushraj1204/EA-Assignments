package domain;

import jakarta.persistence.Entity;

/**
 * @author kush
 */
@Entity
public class Book extends Product {
    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Book() {
    }

    public Book(String isbn) {
        this.isbn = isbn;
    }

    public Book(String name, String description, double price, String isbn) {
        super(name, description, price);
        this.isbn = isbn;
    }
}
