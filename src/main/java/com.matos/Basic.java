package com.matos;


import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basic {

public void flowE2E() throws IOException {

        /**
         * Validate if Add Place API is working as expected
         */

        //given - all input details
        //when - Submit the API
        //then - validate the response

        RestAssured.baseURI = "https://rahulshettyacademy.com";


        /**
         * Add Place
         */
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(Payload.AddPlace()).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))

                //validate the header server to see if it is being directed to the correct server
                .header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response); //for parsing Json
        String placeId = js.getString("place_id");



        /**
         * Add Place using an external json file in the payload and converting from bytes to String
         */
        String resp = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")

                //Converting body external file payload from bytes to String
                .body(new String (Files.readAllBytes(Paths.get("src/main/java/files/addPlace.json")))).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))

                //Validate the header server to see if it is being directed to the correct server
                .header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

        System.out.println(response);
        JsonPath js_0 = new JsonPath(response); //for parsing Json


        //Update Place
        String newAddress = "Summer Walk, Africa";

        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\r\n" +
                        "\"place_id\":\"" + placeId + "\",\r\n" +
                        "\"address\":\"" + newAddress + "\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}").
                when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //Get Place

        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();
        JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress, "Summer Walk, Africa");

    }
}
