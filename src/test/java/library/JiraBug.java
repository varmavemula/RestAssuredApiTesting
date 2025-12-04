package library;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraBug {

	// get an api key from attlasian and encode that api key as email:apikey
	// use that encoded token in header for authorization as basic token
	@Test
	public void createBug() {
		RestAssured.baseURI = "https://varmavemula1004.atlassian.net/";
		
		String response = 
				given().header("Authorization", "Basic dmFybWF2ZW11bGExMDA0QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjA1WmNTUDQyNVZ4ODF2bUdnRmF4ckUyS0R3ajNsczBJaWlfNXV6WnNqd25oUkVBVmhEY3ZzMnhESmxkZnBFSTk2UTNzUTYxYXE5djJjZTF4dVpwV21xam01bFZjQzJXZTVRUjMyZzR4N2hoRk9mVW0yN3dJanIxOTIxbWpYNzN6dER2SXdvZjJzUWh5ZFdFbEhfNW95TFBEOXVUNjhkUVduSy1MeXdlMnR6Snc9QkY4MkE2NDI=")
					.header("Content-Type", "application/json").body("{\r\n"
							+ "    \"fields\": {\r\n"
							+ "        \"project\": {\r\n"
							+ "            \"key\": \"SCRUM\"\r\n"
							+ "        },\r\n"
							+ "        \"summary\": \"Website items are not working- automation Rest Assured\",\r\n"
							+ "        \"issuetype\": {\r\n"
							+ "            \"name\": \"Bug\"\r\n"
							+ "        }\r\n"
							+ "    }\r\n"
							+ "}")
					.log().all()
				.post("rest/api/3/issue")
				.then()
					.log().all()
					.assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		
		
		System.out.println(js.getString("id"));
	
	}
	
	@Test(dataProvider="issueId")
	public void deleteIssue(int id) {
		RestAssured.baseURI = "https://varmavemula1004.atlassian.net/";
		
		String response = 
				given().header("Authorization", "Basic dmFybWF2ZW11bGExMDA0QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjA1WmNTUDQyNVZ4ODF2bUdnRmF4ckUyS0R3ajNsczBJaWlfNXV6WnNqd25oUkVBVmhEY3ZzMnhESmxkZnBFSTk2UTNzUTYxYXE5djJjZTF4dVpwV21xam01bFZjQzJXZTVRUjMyZzR4N2hoRk9mVW0yN3dJanIxOTIxbWpYNzN6dER2SXdvZjJzUWh5ZFdFbEhfNW95TFBEOXVUNjhkUVduSy1MeXdlMnR6Snc9QkY4MkE2NDI=")
					.header("Content-Type", "application/json").body("{\r\n"
							+ "    \"fields\": {\r\n"
							+ "        \"project\": {\r\n"
							+ "            \"key\": \"SCRUM\"\r\n"
							+ "        },\r\n"
							+ "        \"summary\": \"Website items are not working- automation Rest Assured\",\r\n"
							+ "        \"issuetype\": {\r\n"
							+ "            \"name\": \"Bug\"\r\n"
							+ "        }\r\n"
							+ "    }\r\n"
							+ "}")
					.log().all()
				.delete("rest/api/3/issue/"+id)
				.then()
					.log().all()
					.assertThat().statusCode(204).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		
		
		System.out.println(("id is deleted: "+ id));
	}
	
	@DataProvider(name="issueId")
	public Object[] issueId()
	{
		return new Object[] {10004, 10005, 10006};
		
	}
	
	@DataProvider(name="addScreenshotId")
	public Object[] getId()
	{
		return new Object[] {10007};
		
	}
	
	@Test(dataProvider="addScreenshotId")
	public void addScreenshotToIssue(int id) {
		RestAssured.baseURI = "https://varmavemula1004.atlassian.net/";
		
		String response = 
				given()
					.pathParam("id", id)
					.header("Authorization", "Basic dmFybWF2ZW11bGExMDA0QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjA1WmNTUDQyNVZ4ODF2bUdnRmF4ckUyS0R3ajNsczBJaWlfNXV6WnNqd25oUkVBVmhEY3ZzMnhESmxkZnBFSTk2UTNzUTYxYXE5djJjZTF4dVpwV21xam01bFZjQzJXZTVRUjMyZzR4N2hoRk9mVW0yN3dJanIxOTIxbWpYNzN6dER2SXdvZjJzUWh5ZFdFbEhfNW95TFBEOXVUNjhkUVduSy1MeXdlMnR6Snc9QkY4MkE2NDI=")
					.header("Content-Type", "multipart/form-data")
					.header("X-Atlassian-Token", "no-check")
					.multiPart("file", new File("C:/Users/VARMA/OneDrive/Pictures/Testing practice/29-08-2022 test questions/2022-08-29 (1).png"))
					.log().all()
				.post("rest/api/3/issue/{id}/attachments")
				.then()
					.log().all()
					.assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		
		
		System.out.println(js.getString("id"));
	}

}
