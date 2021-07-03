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

  