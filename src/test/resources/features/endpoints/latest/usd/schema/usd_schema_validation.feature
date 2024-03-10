Feature: USD exchange rates endpoint response matches JSON schema

  @SchemaValidation @USD
  Scenario: Verify API response matches JSON schema
    When user makes GET request to "USD"
    Then response code is 200
    And response matches JSON schema "schemas/latest_usd_exchange_rate_schema.json"
