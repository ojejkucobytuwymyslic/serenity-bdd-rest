package endpoints.latest;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.given;

public class ExchangeRateSteps {

    Response response;

    @Step("User makes GET request to {0}")
    public void whenUserMakesGETRequest(String endpoint) {
        response = given().when().get(endpoint);
    }

}