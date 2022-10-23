package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import resources.APIResourcesEnumClasss;
import resources.TestDataBuild;
import resources.Utils;
import pojo.AddPlace;
import static org.junit.Assert.*;
public class StepDefination extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response resp;
	static String place_id;//if we haven't give as static then for other TC it will initialize with null values
	
	TestDataBuild data= new TestDataBuild();
	@Given("Add place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		resspec=new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		res= given().spec(requestSpecification())
				.body(data.addPlacePayload(name,language,address));
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpmethod) {
		//valueOf(string):when you hit this method constructor will executes of APIResourcesEnumClasss
		APIResourcesEnumClasss resourceAPI=APIResourcesEnumClasss.valueOf(resource);
		//to get resource value from APIResourcesEnumClasss
		System.out.println(resourceAPI.getResource());
		
		if(httpmethod.equalsIgnoreCase("post"))
		{
			resp= res.when().post(resourceAPI.getResource());
		}
		else if(httpmethod.equalsIgnoreCase("get"))
		{
			resp= res.when().get(resourceAPI.getResource());
		}
		else if(httpmethod.equalsIgnoreCase("delete"))
		{
			resp= res.when().delete(resourceAPI.getResource());
		}
		else
		{
			resp= res.when().put(resourceAPI.getResource());
		}
	    
	}
	@Then("the API call got sucess with status code {int}")
	public void the_api_call_got_sucess_with_status_code(Integer int1) {
		
		//import static org.junit.Assert.*;: assertEquals() is in static package we have to import manually
		assertEquals(resp.statusCode(),200);
	   
	}
	@Then("{string} in response body is equal to {string}")
	public void in_response_body_is_equal_to(String keyValue, String Expectedkeyvalue) 
	{
		
		assertEquals(getJsonPath(resp, keyValue), Expectedkeyvalue); 
	}
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectednamefromfeaturefile, String resource) throws IOException 
	{
		place_id=getJsonPath(resp,"place_id");
		//prepare request spec->get api call
		res= given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "get");
		String actualnamefromresponse=getJsonPath(resp,"name");
		assertEquals(actualnamefromresponse, expectednamefromfeaturefile); 
	}
	
	@Given("deletePlace payload")
	public void delete_place_payload() throws IOException 
	{
		res= given().spec(requestSpecification())
				.body(data.deletePlacePayload(place_id));
	}
	}


