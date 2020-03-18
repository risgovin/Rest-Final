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

public class RestBase {
	
	protected RequestSpecification request;
	protected Response response;
	protected void setBaseURI(String uri)
	{
		baseURI=uri;
	}
	protected void newRequest() {
		request = given();
	}
	protected void setParam(String name,Object value)
	{
		request = given().param(name,value);
	}
	protected void sendGet(String path)
	{
		response = request.when().get(path);
	}
	protected void sendGet(String path,int variable)
	{
		response = request.when().get(path,variable);
	}
	protected void checkCode(int code)
	{
		response.then().statusCode(code);
	}
	protected void bodyContainsStrict(String path, String value)
	{
		if(!path.equalsIgnoreCase("")&&!value.equalsIgnoreCase(""))
		{
			response.then().body(path,equalTo(parse(value)));
		}
	}
	private Object parse(String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return value;
		}
	}
	protected void jsonBuilder(Map<String,String> dataTable)
	{
		JSONObject json = new JSONObject();
	    for(Map.Entry<String, String> entry:dataTable.entrySet())
	    {
	    	json.put(entry.getKey(),parse(entry.getValue()));
	    }
	    request.header("Content-Type","application/json");
	    request.body(json.toString());
	}
	protected void sendPost(String path)
	{
		response = request.post(path);
	}
	protected String bodyAsString()
	{
		return response.body().asString();
	}
	protected int getIntFromJson(String path)
	{
		return response.then().extract().jsonPath().getInt(path);
	}
	protected void sendPut(String path,int value)
	{
		response = request.when().put(path,value);
	}
	protected void sendPatch(String path,int value)
	{
		response = request.when().patch(path,value);
	}
	protected void sendDelete(String path, int value)
	{
	    response =request.when().delete(path,value);
	}
	protected void validateBodyWithMap(Map<String,String> dataTable)
	{
		for(Map.Entry<String,String> entry: dataTable.entrySet())
	    {
	    	response.then().body(entry.getKey(),equalTo(parse(entry.getValue())));
	    }
	}
}
