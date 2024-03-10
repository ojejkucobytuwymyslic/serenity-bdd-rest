Feature: USD exchange rates endpoint

  @USD
  Scenario: Verify if API call is successful and returns valid price
    When user makes GET request to "USD"
    Then response code is 200
    And response contains valid price

  @USD @Smoke
  Scenario: Verify the status code and status of the API response
    When user makes GET request to "USD"
    Then response code is 200
    And response status is "success"

  @USD @Calculations
  Scenario: Verify USD to AED price falls within the range of 3.6 to 3.7
    When user makes GET request to "USD"
    Then response code is 200
    And response contains valid price
    And USD to AED price is within range of 3.6 to 3.7

  @USD
  Scenario: Verify that 162 currency pairs are returned by the API
    When user makes GET request to "USD"
    Then response code is 200
    And response contains 162 currency pairs