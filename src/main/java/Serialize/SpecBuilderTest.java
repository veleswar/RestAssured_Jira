package Serialize;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {
    public static void main(String[] args) {

        AddPlace ap = new AddPlace();
        ap.setAccuracy(50);
        ap.setAddress("219 willis street,te aro,wellington");
        ap.setLanguage("English");
        ap.setPhone_number("+91234567755");
        ap.setWebsite("testing.com");
        ap.setName("myname");
        List<String> typeList = new ArrayList<String>();
        typeList.add("shoe");
        typeList.add("shop");
        ap.setType(typeList);
        Location loc = new Location();
        loc.setLat(-38.3846);
        loc.setLng(-40.4536);
        ap.setLocation(loc);

      RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").
                setContentType(ContentType.JSON).build();


        ResponseSpecification rb = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        RequestSpecification res = given().spec(req).log().all()
                .body(ap) ;

        Response r1= res.when().post("/maps/api/place/add/json").then().spec(rb).extract().response();
        String responseString = r1.asString();

        System.out.println(responseString);

    }
}
