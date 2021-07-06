package com.instawork.hooks;

import com.aventstack.extentreports.GherkinKeyword;
import com.instatwork.base.BaseUtil;
import com.instawork.pages.Flights;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Homepage extends BaseUtil {

	private BaseUtil base;

	public Homepage(BaseUtil base) {
		this.base = base;
	}

	@Given("I navigate to the Home page")
	public void i_navigate_to_the_home_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("navigate to the Home page");
		base.Driver.navigate().to("https://www.orbitz.com/");
		Thread.sleep(2000l);

	}

	@And("I click on Flight page")
	public void i_click_on_flight_page() throws Throwable {
		base.scenarioDef.createNode(new GherkinKeyword("And"), "I click on Flight page");
		Flights page = new Flights(base.Driver);
		page.ClickFlight();
		Thread.sleep(2000l);

	}

	@And("I enter the  Leaving from  And going to")
	public void i_enter_the_leaving_from_and_going_to(io.cucumber.datatable.DataTable dataTable) throws Throwable {

		Flights page = new Flights(base.Driver);
		page.enterFromCity("SFO");
		page.enterToCity("JFK");

		Thread.sleep(1000l);
		System.out.println("inside I enter the  Leaving from  And going to ");

	}

	@And("I Enter Departing and Returndate")
	public void i_enter_departing_and_returndate() throws Throwable {
		Flights page = new Flights(base.Driver);
		page.enterDepartDate(base.Driver);
		page.enterReturnDate(base.Driver);
		// Write code here that turns the phrase above into concrete actions
		System.out.println("inside I Enter Departing and Returndate ");

	}

	@And("I click the search button")
	public void i_click_the_search_button() throws Throwable {
		Flights page = new Flights(base.Driver);

		page.clickSearch();
		// Write code here that turns the phrase above into concrete actions
		System.out.println("I click the search button");
	}

	@Then("I should see the search results")
	public void i_should_see_the_search_results() throws Throwable {
		Flights page = new Flights(base.Driver);
		page.verifyResults(base.Driver);
		System.out.println("I should see the search results");
	}

	@And("I click the Non Stop Flight")
	public void i_click_the_Non_Stop_Flight() throws Throwable {

		Flights page = new Flights(base.Driver);
		page.clickNonStopFlights(base.Driver);
		System.out.println("I click the Non Stop Flight");
	}

	@And("I sort the price from high to low")
	public void i_sort_the_price_from_high_to_low() throws Throwable {

		Flights page = new Flights(base.Driver);
		page.sortFromHighToLow(base.Driver);
		System.out.println("I sort the price from high to low");

	}

	@And("I select the first flight and click continue")
	public void i_select_the_first_flight_and_click_continue() throws Throwable {

		Flights page = new Flights(base.Driver);
		page.selectFlight(base.Driver);
		System.out.println("I select the first flight and click continue");

	}

	@Then("I should see the proper flights in review page")
	public void i_should_see_the_proper_flights_in_review_page() throws Throwable {

		Flights page = new Flights(base.Driver);
		page.reviewResults(base.Driver);
		System.out.println("I should see the proper flights in review page");

	}

}
