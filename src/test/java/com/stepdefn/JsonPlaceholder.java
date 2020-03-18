package com.stepdefn;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class JsonPlaceholder {

	Response response;
	
	@Given("I set the API Endpoint")
	public void user_sets_API_Endpoint() {
		baseURI = "https://reqres.in/api";
	}

	@When("I make a get request to {string}")
	public void i_make_a_get_request_to(String string) {
		response = when().get("/"+string);
	}

	@Then("I receive a valid response code {int}")
	public void i_receive_a_valid_response_code(int int1) {
	    response.then().assertThat().statusCode(int1);
	}
}
