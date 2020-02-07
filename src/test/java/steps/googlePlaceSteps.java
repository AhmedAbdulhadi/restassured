package steps;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.EEndpoints;
import resources.HttpMethod;
import resources.TestData;
import utils.Helper;
import utils.RequestBuilder;


public class googlePlaceSteps extends RequestBuilder{
	/*
	 * identify all global data members
	 */
	private RequestSpecification objRequest;
	private Response response;
	private TestData objData = new TestData();
	private JsonPath objResponse;
	private String strResponse;
	private Helper helper = new Helper();
	private HashMap<Object, Object> body = new HashMap<Object, Object>();
	private static String placeId;
	@Given("^Add place payload (.*) (.*)$")
    public void add_place_payload(String name, String langauge) throws Throwable {
				
		/**
		 * When we are extracting the spec builder it will return an object the request builder
		 * So now we have spec builder and body information.	
		 */
		
		//This will return an object of spec builder including the base url and params
		 objRequest = given().spec(setUpBuilder())
		//define the body
		.body(objData.addPlace(name,langauge));

    }

    @When("^User wants to call \"(.*)\" with http method \"(.*)\"$")
    public void user_wants_to_call_request_with_httpMethod(String endpoint, String method) throws Throwable {
    	//Preparing the endpoint
    	EEndpoints eResources = EEndpoints.valueOf(endpoint);
    	HttpMethod httpMethod = HttpMethod.valueOf(method);
    	
    	System.out.println(eResources.getResource());
    	
    	//objRequest contains baseUrl, params, body, headers
    	
    	switch (httpMethod) {
		case POST:
			System.out.println(httpMethod);
			response = objRequest.when().
        	post(eResources.getResource());
			break;

		case GET:
			System.out.println(httpMethod);

			response = objRequest.when().
        	get(eResources.getResource());
			break;
			
		case PUT:
			System.out.println(httpMethod);

			response = objRequest.when().
        	put(eResources.getResource());
			break;
		
		case DELETE:
			System.out.println(httpMethod);

			response = objRequest.when().
        	delete(eResources.getResource());
			break;
		default:
			break;
		}
    	
    }
     
    @Then("^Status code should be (.*)$")
    public void status_code_should_be_200(int code) throws Throwable {
    	
    	strResponse = response.then().assertThat().statusCode(code).
		extract().response().asString();
    	
    	//This will contains the response body as JsonPath
		objResponse = new JsonPath(strResponse);
		placeId = objResponse.getString("place_id");
    }

    @And("(.*) is response body should equal (.*)")
    public void something_is_response_body_should_equal_something(String key, String expectedValue) throws Throwable {
    
    	assertEquals(helper.getJsonPathValue(response,key), expectedValue);
    
    }
    
    @And("verify created name is (.*) using (.*)")
    public void verify_create_name_is(String expectedName, String endpoint) throws Throwable {
		 objRequest = given().spec(setUpBuilder().queryParam("place_id", helper.getJsonPathValue(response, "place_id")));
		 user_wants_to_call_request_with_httpMethod(endpoint,"GET");
		 assertEquals(expectedName, helper.getJsonPathValue(response, "name"));
    	
    }
    
    @Given("^Delete created place payload$")
    public void delete_created_place() throws IOException {
    	body.put("place_id", placeId);
		 objRequest = given().spec(setUpBuilder().body(body));
    	
    }
}
