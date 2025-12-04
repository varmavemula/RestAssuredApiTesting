package library;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payloads.Payload;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


//Sending the data using data provider from testng
public class DynamicJSON {
	@Test(dataProvider = "BooksData")
	public void AddBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = 
				given().log().all().header("Content-Type", "application/json").
					body(Payload.addBook(isbn, aisle)).
				when().
					post("/Library/Addbook.php").
				then().log().all().
					assertThat().statusCode(200).extract().response().asString();

		JsonPath js = new JsonPath(response);

		String id = js.getString("ID");

		System.out.println("response: "+response);
		System.out.println("id of new book is: "+ id);

	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData(){
		return new Object[][] {
			{"000123ab", "varma"},
			{"000123ac","varun"},
			{"000123ad","vani"}
		};
	}
	
	
	// Now we have json file which have the body which need to be sent to post method.
	//To send a json file to body() function in when(), we have to convert that json format to string
	//body() function will accept String data type as input.
	//content of json file --> String --> How to convert?
	//There is a way in java
	// first the content of file is converted to Bytes
	//when we are ready with byte data, then we can convert byte --> String
	
	
		@Test
		public void AddBookByStaticJson() throws IOException {
			RestAssured.baseURI = "http://216.10.245.166";
			String response = 
					given().log().all().header("Content-Type", "application/json").
						body(new String(Files.readAllBytes(Paths.get("C:\\Users\\VARMA\\OneDrive\\Desktop\\Testing\\addBook.json")))).
					when().
						post("/Library/Addbook.php").
					then().log().all().
						assertThat().statusCode(200).extract().response().asString();

			JsonPath js = new JsonPath(response);

			String id = js.getString("ID");

			System.out.println("response: "+response);
			System.out.println("id of new book is: "+ id);
		}
		

}
