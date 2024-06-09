import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import mvc.Book;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class BooksRESTTest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testGetOneBook() {
        // add the book to be fetched
        Book book = new Book("123-123-123", "Rudyard Kipling", "The Jungle Book", 160.90);
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        // test getting the book
        given()
                .when()
                .get("books/123-123-123")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("isbn",equalTo("123-123-123"))
                .body("author",equalTo("Rudyard Kipling"))
                .body("title",equalTo("The Jungle Book"))
                .body("price",equalTo(160.90));
        //cleanup
        given()
                .when()
                .delete("books/Mary");
    }

    @Test
    public void testDeleteBook() {
        // add the book to be deleted book
        Book book = new Book("123-123-123", "Rudyard Kipling", "The Jungle Book", 160.90);
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);

        given()
                .when()
                .delete("books/123-123-123");

        given()
                .when()
                .get("books/123-123-123")
                .then()
                .statusCode(404)
                .and()
                .body("errorMessage",equalTo("Book with isbn= Bob 123-123-123 not available"));
    }

    @Test
    public void testAddBook() {
        // add the book
        Book book = new Book("123-123-123", "Rudyard Kipling", "The Jungle Book", 160.90);
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        // get the book and verify
        given()
                .when()
                .get("books/123-123-123")
                .then()
                .statusCode(200)
                .and()
                .body("isbn",equalTo("123-123-123"))
                .body("author",equalTo("Rudyard Kipling"))
                .body("title",equalTo("The Jungle Book"))
                .body("price",equalTo(160.90));
        //cleanup
        given()
                .when()
                .delete("books/Bob");
    }

    @Test
    public void testUpdateBook() {
        // add the book
        Book book = new Book("123-123-123", "Rudyard Kipling", "The Jungle Book", 160.90);
        Book updateBook = new Book("123-123-123", "Rudyard Kipling", "The Jungle Book", 180.90);
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        //update book
        given()
                .contentType("application/json")
                .body(updateBook)
                .when().put("/books/"+ updateBook.getIsbn()).then()
                .statusCode(200);
        // get the book and verify
        given()
                .when()
                .get("books/Bob")
                .then()
                .statusCode(200)
                .and()
                .body("isbn",equalTo("123-123-123"))
                .body("author",equalTo("Rudyard Kipling"))
                .body("title",equalTo("The Jungle Book"))
                .body("price",equalTo(180.90));
        //cleanup
        given()
                .when()
                .delete("books/123-123-123");
    }

    @Test
    public void testGetAllBooks() {
        // add the books
        Book book = new Book("123-123-123", "Rudyard Kipling", "The Jungle Book", 160.90);
        Book book2 = new Book("123-123-124", "Paulo Coelho", "The alchemist", 100.90);
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        given()
                .contentType("application/json")
                .body(book2)
                .when().post("/books").then()
                .statusCode(200);

        // get all books and verify
        given()
                .when()
                .get("books")
                .then()
                .statusCode(200)
                .and()
                .body("books.isbn", hasItems("123-123-123", "123-123-124"))
                .body("books.author",hasItems("Rudyard Kipling", "Paulo Coelho"))
                .body("books.title",hasItems("The Jungle Book", "The alchemist"))
                .body("books.price",hasItems(160.90, 100.90));
        //cleanup
        given()
                .when()
                .delete("books/123-123-123");
        given()
                .when()
                .delete("books/123-123-124");
    }

}
