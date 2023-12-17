package stepDefinitions;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Constants.apiResource;
import Constants.testData;
import Utils.requestSpecification;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlaceRequest;
import pojo.locationInput;
import static org.junit.Assert.*;

public class stepDefinitions extends requestSpecification
{
	
	RequestSpecification placeRequest;
	Response response;
	testData payload =  new testData();
	public static String place_id;
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String address, String accuracy, String language) throws IOException 
	{		
		
		placeRequest = given().spec(requestSpec()).body(payload.AddPlacePayLoad(address,accuracy,language)); // 
		 
	}
	

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpsmethod)
	{
		apiResource resURL= apiResource.valueOf(resource);//invoke constructor method of enum class and set the corresponding method url to the string there
		//ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).build();
		System.out.println(resURL.getResource());
		
		if(httpsmethod.equalsIgnoreCase("POST"))
		response = placeRequest.when().post(resURL.getResource());//then().spec(res).extract().response();
		
		else if (httpsmethod.equalsIgnoreCase("GET"))
		response = placeRequest.when().get(resURL.getResource());
	}
	@Then("API returns statuscode as {int}")
	public void api_returns_statuscode_as(Integer int1) 
	{
		 assertEquals(response.getStatusCode(),200);

	}
	@And("{string} in the response is {string}")
	public void in_the_response_is(String keyValue, String ExpectedValue)
	{
		
		assertEquals(getJsonParse(response,keyValue),ExpectedValue);
		
	}
	@And("verify {string} entered for the new PlaceID same as received from {string}")
	public void verify_entered_for_the_new_place_id_same_as_received_from(String keyValue, String httpmethod) throws IOException
	{
		
		place_id = getJsonParse(response,"place_id");
		placeRequest = given().spec(requestSpec()).queryParam("place_id",getJsonParse(response,"place_id"));
		user_calls_with_http_request(httpmethod, "GET");
		String givenLanguage= getJsonParse(response,"language");
		assertEquals(keyValue,givenLanguage);

	}

	@Given("there is payload for Delete place API")
	public void there_is_payload_for_delete_place_api() throws IOException 
	{
		placeRequest = given().spec(requestSpec()).body(testData.deletePayLoad(place_id));
	}

}
