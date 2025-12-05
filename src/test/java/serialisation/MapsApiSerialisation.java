package serialisation;

import org.testng.annotations.Test;

import api.maps.pojo.AddPlace;
import api.maps.pojo.Location;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.ArrayList;
import java.util.List;

public class MapsApiSerialisation {

	@Test
	public void test1() {
		
		//AddPlace object is created for setting the values for the POJO classes
		//This is known as serialization
		//We will send this object as payLoad for the post method
		//For serialization, we use set methods to set the values
		
		AddPlace AP = new AddPlace();
		AP.setAccuracy(90);
		AP.setAddress("29, side layout, nagulapadu village 09");
		AP.setLanguage("English en");
		AP.setName("My House");
		AP.setPhone_number("7989102289");
		AP.setWebsite("www.google.com");
		
		List<String> types = new ArrayList<>();
		types.add("One");
		types.add("two");
		AP.setTypes(types);
		
		//This location object is created to set values for the sub json object in the main json object of the payload
		
		Location loc = new Location();
		loc.setLat(1234567);
		loc.setLng(2345678);
		AP.setLocation(loc);
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String res = given().log().all().queryParam("key", "qaclick123").body(AP).when().post("/maps/api/place/add/json")
				.then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

	}

}
