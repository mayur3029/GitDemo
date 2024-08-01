import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js = new JsonPath(payload.CoursePrice());
		
		int count= js.getInt("courses.size()");
		//System.out.println(count);
		
		int totalAmount=js.getInt("dashboard.purchaseAmount");
		//System.out.println(totalAmount);
		
//		String firstTitle=js.get("courses[0].title");
		//System.out.println(firstTitle);
		
		for(int i=0;i<count;i++) {
			String courseTitles=js.get("courses["+i+"].title");
			System.out.println(js.get("courses["+i+"].price").toString());
			System.out.println(courseTitles);
		}
		
		System.out.println("Print copies sold by RPA course");
		
		for(int i=0;i<count;i++) {
			String courseTitles=js.get("courses["+i+"].title");
			if(courseTitles.equalsIgnoreCase("RPA")) {
				int copies=js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
		
		System.out.println("Verify sum of all courses price match with purchase amount");
		double sum=0;
		
		for(int i=0;i<count;i++) {
			int price=js.get("courses["+i+"].price");
			int copies=js.get("courses["+i+"].copies");
			sum+=(price*copies);
		}
		
		System.out.println(sum);
		
		Assert.assertEquals(totalAmount, sum);
	}

}
