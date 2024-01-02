import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import jdk.security.jarsigner.JarSigner;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UseFilterSession {
    public static void main(String[] args) {
        RestAssured.baseURI= "http://localhost:8080/";
        SessionFilter session = new SessionFilter();
        String resp =given().log().all().
                header("Content-Type","application/json").
                body("{\"username\":\"viswanadhs\",\"password\":\"Sailu143$\"}").
                filter(session).when().
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
        Header header3 = new Header("X-Atlassian-Token","no-check");

        String resp1 =given().log().all().
                header(header1).
                body(payLoad.createIssue()).
                filter(session).when().
                post("/rest/api/2/issue").then()
                .extract().response().asString() ;
        System.out.println(resp1);

        //Add comment

        given().pathParam("key", "10006").log().all().
                header(header1).body("{\n" +
                        "    \"body\": \"Testing the comment\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}").
                filter(session).when().post("/rest/api/2/issue/{key}/comment")
                .then().log().all().assertThat().statusCode(201);

        //Add attachment
        given().header(header3).header("Content-Type","multipart/form-data").log().all().filter(session)
                .pathParam("key","10006").
                multiPart("file",new File("src/main/java/jira.txt")).when().
                post("/rest/api/2/issue/{key}/attachments").then().log().all().
                statusCode(200);

        //Get issue

        String attach=given().log().all().filter(session).pathParam("key","10006").
                queryParam("fields","comment").
                when().get("/rest/api/2/issue/{key}").then().extract().response().asString();
        JsonPath clue = new JsonPath(attach);
        System.out.println(clue.getInt("fields.comment.comments.size()"));
    }
}
