package IBM.Rest_1;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class api_project {

		@Test(enabled = true)
		public void createUser(ITestContext val)
		{
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			
			JSONObject obj = new JSONObject();
			
			obj.put("username", "Klaus_07");
			obj.put("firstName", "Klaus");
			obj.put("lastName", "Mikaelson");			
			obj.put("email", "klaus@gmail.com");			
			obj.put("password", "wolf");			
			obj.put("phone", "123456789");
			
			String u_name="Klaus_07";
			
			given()
			.contentType(ContentType.JSON)
			.body(obj.toJSONString()).
		when()
			.post("/user").
		then()
			.statusCode(200)
			.log()
			.all();
			
			val.setAttribute("username", u_name);
		}
		
		@Test(enabled = true, dependsOnMethods="createUser")
		public void login()
		{
			
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			
			given().
			when()
				.queryParam("username","Klaus_07")
				.queryParam("password","wolf")
				.get("/user/login").
			then()
				.statusCode(200)
				.log()
				.all();
		}
		
		@Test(enabled = true, dependsOnMethods= "login")
		public void edit(ITestContext val)
		{
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			JSONObject obj = new JSONObject();
			obj.put("username", "obito");
			obj.put("firstName", "obito");
			obj.put("lastName", "uchiha");
			obj.put("email", "obi@ichi.com");
			obj.put("password", "obi1234");
			obj.put("phone", "9876543210");
			
			String u_name="obito";
			
			given()
			.contentType(ContentType.JSON)
			.body(obj.toJSONString()).
		when()
			.put("/user/"+val.getAttribute("username")).
		then()
			.statusCode(200)
			.log()
			.all();

			val.setAttribute("username", u_name);
			
		}
		
		@Test(enabled = true, dependsOnMethods= "edit")
		public void logout()
		{
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			given().
			when()
				.get("/user/logout").
			then()
				.statusCode(200)
				.log()
				.all();
		}
		
		@Test(enabled = true, dependsOnMethods="logout")
		public void delete(ITestContext val)
		{
			RestAssured.baseURI="https://petstore.swagger.io/v2";
			
			given().
			when()
			.delete("/user/"+val.getAttribute("username").toString()).
		then()
			.statusCode(200)
			.log()
			.all();
			

		}
		
}