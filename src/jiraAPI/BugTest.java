package jiraAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class BugTest {

	public static void main(String [] args) {
		
		RestAssured.baseURI="https://mayurmore3095.atlassian.net";
		
		String createIssueResponse=given().header("Content-type", "application/json")
		.header("Authorization","Basic bWF5dXJtb3JlMzA5NUBnbWFpbC5jb206QVRBVFQzeEZmR0YwMnRuOHdpQVZKM1Jwclp5Zndkd0Z2bVd6cjRxakstajlSWExYN1NvdEZtMEcxcTBsM2hUd0FHNGZIYTBJVEp4M3lPNVFneHd0blZvTXNjekRYQTJlaUJVNnlqcGdKR2Z5OXFiZWNwVlQ0ZDJPRmxad3lWdTlEMDNlOHhnbjV4VG9ZSWNzWjkyUmJ3LXQ2UTZMVW5Ebko2YnJZX2Z3aFR3STVxSkJJRm1RUlVRPTdFMkNDMTU2")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Report page is broken\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}")
		.log().all()
		.post("/rest/api/3/issue").then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		JsonPath js= new JsonPath(createIssueResponse);
		String issueId=js.getString("id");
		System.out.println(issueId);
		
		
		given().pathParam("key", issueId)
		.header("Authorization","Basic bWF5dXJtb3JlMzA5NUBnbWFpbC5jb206QVRBVFQzeEZmR0YwMnRuOHdpQVZKM1Jwclp5Zndkd0Z2bVd6cjRxakstajlSWExYN1NvdEZtMEcxcTBsM2hUd0FHNGZIYTBJVEp4M3lPNVFneHd0blZvTXNjekRYQTJlaUJVNnlqcGdKR2Z5OXFiZWNwVlQ0ZDJPRmxad3lWdTlEMDNlOHhnbjV4VG9ZSWNzWjkyUmJ3LXQ2UTZMVW5Ebko2YnJZX2Z3aFR3STVxSkJJRm1RUlVRPTdFMkNDMTU2")
		.header("X-Atlassian-Token","no-check")
		.multiPart("file",new File("D:/Notes/AttachmentTest.txt")).log().all()
		.post("/rest/api/3/issue/{key}/attachments/").then().log().all().assertThat().statusCode(200);
	}
}
