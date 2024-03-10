package endpoints.latest;

import endpoints.latest.model.Currency;
import endpoints.latest.model.ExchangeRateErrorResponse;
import endpoints.latest.model.ExchangeRateResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.junit.Assert.assertTrue;

public class ExchangeRateStepDefinitions {

    final String BASE_URL = "https://open.er-api.com/v6/latest/";

    @Steps
    ExchangeRateSteps exchangeRateSteps;

    @When("user makes GET request to {string}")
    public void userMakesGETRequestTo(String endpoint) {
        exchangeRateSteps.whenUserMakesGETRequest(BASE_URL + endpoint);
    }

    @Then("response code is {int}")
    public void responseCodeIs(int expectedStatusCode) {
        restAssuredThat(response -> response.statusCode(expectedStatusCode));
    }

    @Then("response contains valid price")
    public void responseContainsValidPrice() {
        ExchangeRateResponse exchangeRateResponse = exchangeRateSteps.response.as(ExchangeRateResponse.class);
        final Double usdToEurExchangeRate = exchangeRateResponse.getRates().get(Currency.EUR);
        Assert.assertTrue("EUR price is not greater than 0", usdToEurExchangeRate > 0);
    }

    @And("response matches JSON schema {string}")
    public void responseMatchesJSONSchema(String schemaPath) {
        restAssuredThat(response -> response.body(matchesJsonSchemaInClasspath(schemaPath)));
    }

    @Then("error type is {string}")
    public void errorTypeIs(String expectedErrorType) {
        ExchangeRateErrorResponse exchangeRateErrorResponse = exchangeRateSteps.response.as(ExchangeRateErrorResponse.class);
        Assert.assertEquals("Response error type is not as expected", expectedErrorType, exchangeRateErrorResponse.getErrorType());
    }

    @Then("response status is {string}")
    public void responseStatusIs(String expectedStatus) {
        ExchangeRateResponse exchangeRateResponse = exchangeRateSteps.response.as(ExchangeRateResponse.class);
        Assert.assertEquals("Response status is not as expected", expectedStatus, exchangeRateResponse.getResult());
    }

    @Then("response contains error")
    public void responseContainsError() {
        ExchangeRateErrorResponse exchangeRateErrorResponse = exchangeRateSteps.response.as(ExchangeRateErrorResponse.class);
        Assert.assertEquals("Response result is not 'error'", "error", exchangeRateErrorResponse.getResult());
    }

    @Then("USD to AED price is within range of {double} to {double}")
    public void usdToAEDPriceIsWithinRange(double lowerBound, double upperBound) {
        ExchangeRateResponse exchangeRateResponse = exchangeRateSteps.response.as(ExchangeRateResponse.class);
        final Double usdToAedExchangeRate = exchangeRateResponse.getRates().get(Currency.AED);
        Assert.assertTrue("USD to AED price is not within range",
                usdToAedExchangeRate >= lowerBound && usdToAedExchangeRate <= upperBound);
    }

    @Then("API response time is within {int} seconds from current time")
    public void apiResponseTimeIsWithinSecondsFromCurrentTime(int maxSeconds) {
        String dateString = exchangeRateSteps.response.getHeader("Date");
        ZonedDateTime responseTime = ZonedDateTime.parse(dateString, DateTimeFormatter.RFC_1123_DATE_TIME);
        ZonedDateTime currentTime = Instant.now().atZone(responseTime.getZone());
        long timeDifferenceInSeconds = currentTime.toEpochSecond() - responseTime.toEpochSecond();
        assertTrue("API response time is more than " + maxSeconds + " seconds from current time",
                timeDifferenceInSeconds <= maxSeconds);
    }

    @Then("response contains {int} currency pairs")
    public void responseContainsCurrencyPairs(int expectedCount) {
        ExchangeRateResponse exchangeRateResponse = exchangeRateSteps.response.as(ExchangeRateResponse.class);
        Assert.assertEquals("Number of currency pairs in the response is not as expected",
                expectedCount, exchangeRateResponse.getRates().size());
    }

}