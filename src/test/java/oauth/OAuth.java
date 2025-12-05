package oauth;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import pojo.GetCourse;
import pojo.WebAutomation;

import static io.restassured.RestAssured.*;

import java.util.List;

public class OAuth {

	@Test
	public void test1() {

		String response =

				given()

						.formParams("client_id",
								"692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

						.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

						.formParams("grant_type", "client_credentials")

						.formParams("scope", "trust")

						.when().log().all()

						.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

		System.out.println(response);

		JsonPath jsonPath = new JsonPath(response);

		String accessToken = jsonPath.getString("access_token");

		System.out.println(accessToken);

		String r2 = given()

				.queryParams("access_token", accessToken)

				.when()

				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")

				.asString();

		System.out.println(r2);

	}
	
	
	//This test gives brief understanding of 
	@Test
	public void test2() {
		String response =

				given()

						.formParams("client_id",
								"692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

						.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

						.formParams("grant_type", "client_credentials")

						.formParams("scope", "trust")

						.when().log().all()

						.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

		System.out.println(response);

		JsonPath jsonPath = new JsonPath(response);

		String accessToken = jsonPath.getString("access_token");

		System.out.println(accessToken);

		GetCourse r2 = given()

				.queryParams("access_token", accessToken)

				.when()

				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")

				.as(GetCourse.class);

		String s = r2.getLinkedIn();
		System.out.println(s);
		
		List<WebAutomation> WA = r2.getCourses().getWebAutomation();
		
		for(WebAutomation i: WA ) {
			System.out.println("------------Courses of WebAutomation--------------");
			System.out.println("Course title of :" + i.getCourseTitle());
			System.out.println("--------------------------");

		}
	
	}
}
