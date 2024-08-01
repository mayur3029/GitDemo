
import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void SumofCourses() {
		JsonPath js = new JsonPath(payload.CoursePrice());
		int count= js.getInt("courses.size()");
		int totalAmount=js.getInt("dashboard.purchaseAmount");
		double sum=0;
		
		for(int i=0;i<count;i++) {
			int price=js.get("courses["+i+"].price");
			int copies=js.get("courses["+i+"].copies");
			sum+=(price*copies);
		}
		
		System.out.println(sum);
		System.out.println(sum);
		Assert.assertEquals(totalAmount, sum);
		
	}
}
