package com.stepdefn;

import io.cucumber.gherkin.internal.com.eclipsesource.json.Json;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.JsonObject;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.json.simple.JSONObject;

import Configs.ConfigReader;

public class ReqresSteps extends RestBase{
	
	ConfigReader configs = ConfigReader.getInstance();
	int id = -1;
	@Given("the user sets the endpoint to {string}")
	public void the_user_sets_the_endpoint_to(String string) {
	    setBaseURI(configs.getBaseURI(string));
	    newRequest();
	}
	@Given("the user sets the endpoint to absolute {string}")
	public void the_user_sets_the_endpoint_to_absolute(String string) {
	    setBaseURI(string);
	    newRequest();
	}
	@When("the user wants to get page {int}")
	public void the_user_wants_to_get_page(Integer int1) {
		setParam("page",int1);
	}

	@When("the user makes a get request to {string}")
	public void the_user_makes_a_get_request_to(String path) {
		if(id==-1)
			sendGet(configs.getEndpoint(path));
		else
			sendGet(configs.getEndpoint(path),id);
	}
	@When("the user makes a get request to absolute {string}")
	public void the_user_makes_a_get_request_to_absolute(String path) {
		if(id==-1)
			sendGet(path);
		else
			sendGet(path,id);
	}
	@Then("the response should have status code {int}")
	public void the_response_should_have_status_code(Integer code) {
	    checkCode(code);
	}

	@Then("the response should contain {string} field as {string}")
	public void the_response_should_contain_field_as(String path, String value) {
		bodyContainsStrict(path,value);
	}

	@When("the user has an id of {int}")
	public void the_user_has_an_ID_of(Integer int1) {
		id = int1;
	}
	@When("the user has the following data:")
	public void the_user_has_the_following_data(Map<String,String> dataTable) {
		jsonBuilder(dataTable);
	}

	@When("the user makes a post request to {string}")
	public void the_user_makes_a_post_request_to(String path)
	{
	    sendPost(configs.getEndpoint(path));
	}
	@When("the user makes a post request to absolute {string}")
	public void the_user_makes_a_post_request_to_absolute(String path)
	{
	    sendPost(path);
	}
	@Then("show the response")
	public void show_the_response() {
	    System.out.println(bodyAsString());
	}
	@Then("the user stores the id")
	public void the_user_stores_the_id() {
	    id = getIntFromJson("id");
	}

	@Then("the user makes a delete request to {string}")
	public void the_user_makes_a_delete_request_to(String path) {
	    newRequest();
	    sendDelete(configs.getEndpoint(path),id);
	}

	@When("the user makes a put request to {string}")
	public void the_user_makes_a_put_request_to(String path)
	{
	    sendPut(configs.getEndpoint(path),id);
	}

	@When("the user makes a patch request to {string}")
	public void the_user_makes_a_patch_request_to(String path) {
	    sendPatch(configs.getEndpoint(path),id);
	}
	
	@Then("validate the response against the following data:")
	public void validate_the_response_against_the_following_data(Map<String,String> dataTable) {
	    validateBodyWithMap(dataTable);
	}

}
