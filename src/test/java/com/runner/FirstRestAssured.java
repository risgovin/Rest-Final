package com.runner;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.response.Response;
public class FirstRestAssured {
	
	@Test
	public void testFirstRest()
	{
		baseURI = "https://jsonplaceholder.typicode.com";
		Response res = when().get("/posts/{id}",3).
						then().assertThat().statusCode(200).extract().response();
		System.out.println(res.asString());
	}
}
