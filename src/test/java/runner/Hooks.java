package runner;

import cucumber.api.java.Before;
import steps.googlePlaceSteps;

public class Hooks {
	private googlePlaceSteps addPlace = new googlePlaceSteps();

	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
		System.out.println("ENTER ADD PLACE BEOFRE ");
		addPlace.add_place_payload("Ahmed", "test");
		addPlace.user_wants_to_call_request_with_httpMethod("AddPlaceAPI", "POST");
		addPlace.status_code_should_be_200(200);
		
	}
}
