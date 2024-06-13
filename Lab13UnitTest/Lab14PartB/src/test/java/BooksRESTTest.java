import books.domain.Book;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

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
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        // test getting the book
        given()
                .when()
                .get("books/878")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("isbn",equalTo("878"))
                .body("title",equalTo("Book 123"))
                .body("price",equalTo(18.95f))
                .body("author",equalTo("Joe Smith"));
        //cleanup
        given()
                .when()
                .delete("books/878");
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book("112-221-221", "Spiderman", 18.95, "Kush Raj");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        given()
                .when()
                .delete("books/112-221-221")
                .then()
                .statusCode(204);
        given()
                .when()
                .get("books/112-221-221")
                .then()
                .statusCode(404);
    }

    @Test
    public void testAddBook() {
        Book book = new Book("112-221-221", "Batman", 77.27, "DC Stan Lee");
        given()
                .contentType("application/json")
                .body(book)
                .when()
                .post("/books")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .and()
                .body("isbn", equalTo("112-221-221"))
                .body("title", equalTo("Batman"))
                .body("price", equalTo(77.27F))
                .body("author", equalTo("DC Stan Lee"));
        given()
                .when()
                .delete("books/112-221-221");
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book("112-221-221", "Batman", 22.00, "Jack Kirby");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        Book updatedBook = new Book("112-221-221", "Batman that laughs", 25.00, "Stan Lee");
        given()
                .contentType("application/json")
                .body(updatedBook)
                .when()
                .put("/books/112-221-221")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .and()
                .body("isbn", equalTo("112-221-221"))
                .body("title", equalTo("Batman that laughs"))
                .body("price", equalTo(25.00F))
                .body("author", equalTo("Stan Lee"));
        given()
                .when()
                .delete("books/112-221-221");
    }

    @Test
    public void testSearchBooks() {
        Book book1 = new Book("112-221-221", "Batman", 10.00, "Stan Lee");
        Book book2 = new Book("112-221-222", "Ironman", 15.00, "Stan Lee");
        Book book3 = new Book("112-221-223", "Spiderman", 20.00, "Jack Kirby");
        given().contentType("application/json").body(book1).when().post("/books").then().statusCode(200);
        given().contentType("application/json").body(book2).when().post("/books").then().statusCode(200);
        given().contentType("application/json").body(book3).when().post("/books").then().statusCode(200);

        given()
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .and()
                .body("books", hasSize(3));

        given()
                .when()
                .get("/books?author=\"Stan Lee\"")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .and()
                .body("books", hasSize(2));

        given().when().delete("books/112-221-221");
        given().when().delete("books/112-221-222");
        given().when().delete("books/112-221-223");
    }


}
