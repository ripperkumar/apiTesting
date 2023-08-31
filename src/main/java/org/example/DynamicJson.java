package org.example;

import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;

public class DynamicJson {

    @Test (dataProvider="BooksData")
    public void addBook(String isbn,String aisle){
        RestAssured.baseURI= "http://216.10.245.166";
        String response = given().log().all().header("Content-Type","application/json").
                body(Payload.AddBooks(isbn,aisle)).
                when().post("/Library/Addbook.php").
                then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js = ReUsableMethods.rawToJson(response);
        String id = js.getString("ID");
        System.out.println(id);
    }


    @DataProvider(name="BooksData")

    public Object[][]  getData()

    {
        return new Object[][] {{"ojfwty","9363"},{"cwetee","4253"}, {"okmfet","533"} };

    }








}
