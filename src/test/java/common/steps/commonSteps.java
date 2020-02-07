package common.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;


public class commonSteps {

    @Then("^This is from common$")
    public void i_should_be_able_checkin_my_address() throws Throwable {
    	System.out.println("Smoke test3");
    }
    
    


}
