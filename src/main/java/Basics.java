import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class Basics {
    public static void main(String[] args) {
        //validate if Add place API is working or not
        //given-all input details
        // when-  submit input details
        //then - Validate input details

        //add place-> update it with new address-> Get place to validate if new place is presentin response

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(Payload.AddPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();

        JsonPath js = new JsonPath(response);
        String placeId = js.getString("place_id");
        System.out.println(placeId);

    }
}
