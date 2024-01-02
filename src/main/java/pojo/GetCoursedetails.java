package pojo;

import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
public class GetCoursedetails {
    public static void main(String[] args) {

      /* String resp= given().log().all().queryParam("access_token",oAuth.GenerateAccessToken())
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();

        System.out.println(resp);*/

        //desrialize using pojo

       CourseResponse gc= given().log().all().queryParam("access_token",oAuth.GenerateAccessToken())
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(CourseResponse.class);

        System.out.println(gc.getExpertise());
        System.out.println(gc.getCourses().getWebAutomation().get(1).getCourseTitle());

        for(int i=0;i<gc.getCourses().getWebAutomation().size();i++)
        {
            System.out.println(gc.getCourses().getWebAutomation().get(i).getCourseTitle());
        }


    }
}
