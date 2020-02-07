package resources;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import entities.Location;
import entities.Place;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestData {
	
	private HashMap<String, String> loginPayload = new HashMap<String, String>();
	public Place addPlace(String name, String language) throws IOException {
		FileInputStream workbook = new FileInputStream("Book1.xlsx");

		XSSFWorkbook objExcel = new XSSFWorkbook(workbook);
		
		objExcel.getNumberOfSheets();
		
		Place objPlace = new Place();
		objPlace.setAccuracy(50);
		objPlace.setName(name);
		objPlace.setAddress("madina streeet");
		objPlace.setPhone_number("962709207123");
		objPlace.setWebsite("https://ahmedwajieh.com");
		objPlace.setLanguage(language);

		Location objLocation = new Location();
		objLocation.setLat(35.123123);
		objLocation.setLng(30.324234);
		objPlace.setLocation(objLocation);

		List<String> typesList = new ArrayList<String>();
		typesList.add("type1");
		typesList.add("types2");
		
		objPlace.setTypes(typesList);	
		return objPlace;
	}
public String loginTC(String username,String password) {
		
		loginPayload.put("username", username);
		loginPayload.put("password",password);
		RestAssured.baseURI="http://167.172.113.193:8080/mwarrid-0.0.1/mwarrid";
		Response res = given().
		contentType(ContentType.JSON).
		body(loginPayload).
		when().
		post("/api/v1/auth/login").
		then().assertThat().statusCode(200).extract().response();
		JsonPath objResponse = new JsonPath(res.asString());
		return objResponse.get("data.token");		
	}

}
