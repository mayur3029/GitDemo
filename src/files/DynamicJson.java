package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class DynamicJson {

	@Test(dataProvider = "BooksData")
	public void addBook(String isbn,String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		
		String resp=given().log().all().header("Content-type", "application/json")
		.body(payload.AddBook(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js=ReusableMethods.rawToJson(resp);
		String id=js.get("ID");
		System.out.println(id);
	}
	
	@DataProvider(name="BooksData")
	public Object [][] getData(){
		return new Object [][] { {"fhfh","3322"},{"fhgj","1029"},{"nmbl","9822"} };
	}
}
