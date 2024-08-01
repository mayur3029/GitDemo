package practice;

public class Payload {

	public static String Register() {
		return """
				{
					"email": "eve.holt@reqres.in",
					"password": "pistol"
				}
				""";
	}
	
	public static String Login() {
		return """
				{
					"email": "eve.holt@reqres.in",
					"password": "cityslicka"
				}
				""";
	}
}
