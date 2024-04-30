import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class PetTests {

        @Test
        public void IDOfOurPet() {
            String s = "{\"id\":10, \"name\": \"Natasha\"}";

            given()
                    .contentType("application/json")
                    .body(s)
                    .when()
                    .post("https://petstore.swagger.io/v2/pet")
                    .then()
                    .statusCode(200)
                    .body("id", equalTo(10))
                    .body("name", equalTo("Natasha")); // Проверяем, что поле "name" равно "PetName"

        }

    @Test
    public void PutInfoAboutOurPet() {
        String s = "{\"id\":10}";
        given().
                contentType("application/json").
                body(s).
                when().
                put("https://petstore.swagger.io/v2/pet").
                then().
                statusCode(200).
                body("id", equalTo(10));
    }

        @Test
        public void GetOurPet() {
            RestAssured
                    .get("https://petstore.swagger.io/v2/pet/10")
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .and()
                    .body("id", equalTo(10))
                    .body("name", equalTo("Natasha"));
        }
        @Test
        public void WrongIDOfOurPet() {
            RestAssured
                    .get("https://petstore.swagger.io/v2/pet/9")
                    .then()
                    .assertThat()
                    .statusCode(200);
        }

        @Test
        public void ChangeInfoAboutOurPet() {

            String requestBody = "{\"id\": 100, \"name\": \"NeNatasha\"}";

            Response response = given()
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .when()
                    .put("https://petstore.swagger.io/v2/pet")
                    .then()
                    .statusCode(200)
                    .body("name", equalTo("NeNatasha"))
                    .extract()
                    .response();

        }

        @Test
        public void DeleteOurPetAndError() {

            Response response;
            response = given()
                    .when()
                    .delete("https://petstore.swagger.io/v2/pet/1")
                    .then()
                    .statusCode(404)
                    .extract()
                    .response();
        }
}


