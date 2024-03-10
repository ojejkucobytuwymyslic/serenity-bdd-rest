Feature: General Error Responses

  @ErrorHandling
  Scenario: Verify if API returns 404 for incorrect URL
    When user makes GET request to "incorrectUrl/shouldReturn404"
    Then response code is 404

  #Other error statuses mentioned on provider's page are for paid API and not for this endpoint:
  #"malformed-request" when some part of your request doesn't follow the structure shown above.
  #"invalid-key" when your API key is not valid.
  #"inactive-account" if your email address wasn't confirmed.
  #"quota-reached" when your account has reached the the number of requests allowed by your plan.