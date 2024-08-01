package practice;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;


public class ApiTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://reqres.in/";
		String response;
		JsonPath js;
		
		System.out.println("------------------List Users--------------------------");
		response=given().log().all().queryParam("page", "1")
		.when().get("/api/users").then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		js=ReusableMethods.rawToJson(response);
		int pageNo = js.getInt("page");
		System.out.println("Fetched data for page "+pageNo);
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println("------------------Single User--------------------------");
		
		
		String expectedEmailId="lindsay.ferguson@reqres.in";
		response=given().log().all()
		.when().get("/api/users/8").then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		js=ReusableMethods.rawToJson(response);
		String actualEmailId=js.getString("data.email");
		Assert.assertEquals(actualEmailId,expectedEmailId);
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println("------------------Register--------------------------");
		
		response=given().log().all().header("Content-type", "application/json")
		.body(Payload.Register())
		.when().post("/api/register").then().log().all().statusCode(200).extract().response().asString();
		
		js=ReusableMethods.rawToJson(response);
		int userId = js.getInt("id");
		System.out.println("User Registered Successfully, User id is: "+userId);
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println("------------------Login--------------------------");
		
		given().log().all().header("Content-type", "application/json")
		.body(Payload.Login())
		.when().post("/api/login").then().log().all().statusCode(200);
		
		

	}

}
