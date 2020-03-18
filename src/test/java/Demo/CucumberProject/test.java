package Demo.CucumberProject;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//{
//    "email": "eve.holt@reqres.in",
//    "password": "pistol"
//}

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

public class test {

	@Test
	public void testRun()
	{
		baseURI = "https://reqres.in/api";
		RequestSpecification request = given().header("Content-Type","application/json");
		JSONObject json = new JSONObject();
		json.put("email","test@test123");
		json.put("password", "piston");
		Response res = request.body(json.toJSONString()).when().
		post("/register").
		then().assertThat().statusCode(400).extract().response();
		System.out.println(res.body().asString());
		
	}
}
