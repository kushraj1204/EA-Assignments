package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Book {

	@Id
	@GeneratedValue
	private long id;

	private String title;

	private String author;

	private String locationCode;

	private int publicationYear;

	public Book() {
	}

	public Book(String title, String author, String locationCode, int publicationYear) {
		this.title = title;
		this.author = author;
		this.locationCode = locationCode;
		this.publicationYear = publicationYear;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", title='" + title + '\'' +
				", author='" + author + '\'' +
				", locationCode='" + locationCode + '\'' +
				", publicationYear='" + publicationYear + '\'' +
				'}';
	}

}
