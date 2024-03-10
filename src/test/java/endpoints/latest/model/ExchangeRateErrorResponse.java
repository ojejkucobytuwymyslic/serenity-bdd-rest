package endpoints.latest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExchangeRateErrorResponse {

    @JsonProperty("result")
    private String result;

    @JsonProperty("error-type")
    private String errorType;
}