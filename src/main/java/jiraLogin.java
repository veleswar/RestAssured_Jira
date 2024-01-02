import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import  static io.restassured.RestAssured.*;
public class jiraLogin {
    public static void main(String[] args) {
        RestAssured.baseURI= "http://localhost:8080/";
        String resp =given().log().all().
                header("Content-Type","application/json").
                body("{\"username\":\"viswanadhs\",\"password\":\"Sailu143$\"}").when().
                post("/rest/auth/1/session").then()
                .extract().response().asString() ;
        JsonPath js = new JsonPath(resp);
        System.out.println(resp);
        System.out.println(js.getString("session.name"));
        System.out.println(js.getString("session.value"));
        String cookie = js.getString("session.name")+"="+js.getString("session.value");
        System.out.println(cookie);
        // create Issue

        Header header1 = new Header("Content-Type","application/json");
        Header header2 = new Header("Cookie",cookie);

        String resp1 =given().log().all().
                header(header1).
                header(header2).
                body(payLoad.createIssue()).when().
                post("/rest/api/2/issue").then()
                .extract().response().asString() ;
        System.out.println(resp1);

        //Add comment

    }
}
