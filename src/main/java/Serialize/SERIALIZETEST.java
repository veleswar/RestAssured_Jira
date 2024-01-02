package Serialize;

import com.beust.ah.A;
import com.sun.jdi.PathSearchingVirtualMachine;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SERIALIZETEST {
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


        Response res = given().log().all().queryParam("key","qaclick123")
                .body(ap)
                .when().post("/maps/api/place/add/json").
                then().extract().response();

        System.out.println(res);

    }
}
