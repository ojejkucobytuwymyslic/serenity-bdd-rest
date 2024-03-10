Feature: Latest exchange rates endpoint error responses

  @ErrorHandling
  Scenario: Verify if API returns error for unsupported currency code
    When user makes GET request to "notExistingCurrencyShouldReturnError"
    Then response code is 200
    And response contains error
    And error type is "unsupported-code"