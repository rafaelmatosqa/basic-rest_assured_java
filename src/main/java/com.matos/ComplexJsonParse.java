package com.matos;

import files.Payload;
import io.restassured.path.json.JsonPath;

import java.util.List;


public class ComplexJsonParse {


    public static void main(String[] args) {
        JsonPath js = new JsonPath(Payload.CoursePrice());

        int count = js.getInt("courses.size()");
        System.out.println(count);

        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        String title = js.getString("courses[0].title");
        System.out.println(title);

        List lista = js.getList("courses.title");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }

        for(int i=0; i< count; i++){
            String coursesTiles = js.get("courses["+i+"].title");
            if(coursesTiles.equalsIgnoreCase("RPA")){
                //copies sold
                int copies = js.get("courses["+i+"].copies");
                System.out.println(copies);
                break;

            }
        }


    }
}
