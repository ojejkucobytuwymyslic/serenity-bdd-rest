Feature: USD exchange rates endpoint response time

  @USD @Performance
  Scenario: Verify API response time is within 3 seconds from current time
    When user makes GET request to "USD"
    Then response code is 200
    And response contains valid price
    And API response time is within 3 seconds from current time