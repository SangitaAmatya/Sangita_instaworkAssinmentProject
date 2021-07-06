Feature: FlightFeature

	Scenario: Search Flight between cities
    Given I navigate to the Home page
    And I click on Flight page
    And I enter the  Leaving from  And going to
      | Leavig_from | Going_to      |
      | San Francisco | New York |    
    And I Enter Departing and Returndate
    And I click the search button
    Then I should see the search results
	
	
    Scenario: Book Flight between cities
    Given I navigate to the Home page
    And I click on Flight page
    And I enter the  Leaving from  And going to
      | Leavig_from | Going_to      |
      | SFO | JFK |    
    And I Enter Departing and Returndate
    And I click the search button
    And I click the Non Stop Flight
    And I sort the price from high to low 
    And I select the first flight and click continue
    And I click the Non Stop Flight
    And I sort the price from high to low 
    And I select the first flight and click continue
    Then I should see the proper flights in review page
    
    
    
  