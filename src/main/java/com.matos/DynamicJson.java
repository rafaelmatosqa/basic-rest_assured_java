package com.matos;


import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn,String aisle) {


        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all().header("Content-Type", "application/json").
                body(Payload.addBook(isbn,aisle)).
                when()
                .post("/Library/Addbook.php")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = ReUsableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);

        //Delete Book

    }

    /**
     * Using the dataProvider is a simple way to make json payload data more dynamic by passing the values by parameters, but
     * if there is any validation of repeated data this dynamic would depend on an implementation of the Delete method after it is performed in order to eliminate what was inserted into the base.
     * I point out that it is not a good practice to create dependent test cases because they can cause false failures.
     */

    @DataProvider(name="BooksData")
    public Object[][] getData(){

        //Quando usamos dois colchetes esse objeto será uma matriz multidimensional
        // array = collections of arrays
        return new Object[][] {{"hdgdfs","8547"}, {"bdytar","9865"}, {"wradse","65874"}};
    }
}
