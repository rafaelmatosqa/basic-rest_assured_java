package com.matos;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {


    public void sumCourses(){

        int sum = 0;
        JsonPath js = new JsonPath(Payload.CoursePrice());
        int count = js.getInt("courses.size()");
        for(int i = 0; i < count; i++){
            int price = js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");
            int amount = copies * price;
            System.out.println(amount);
            sum = sum + amount;
            System.out.println("Total amount: " +sum);
        }


    }
}
