package endpoints.latest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRateResponse {

    @JsonProperty("result")
    private String result;

    @JsonProperty("provider")
    private String provider;

    @JsonProperty("documentation")
    private String documentation;

    @JsonProperty("terms_of_use")
    private String termsOfUse;

    @JsonProperty("time_last_update_unix")
    private Integer timeLastUpdateUnix;

    @JsonProperty("time_last_update_utc")
    private String timeLastUpdateUtc;

    @JsonProperty("time_next_update_unix")
    private Integer timeNextUpdateUnix;

    @JsonProperty("time_next_update_utc")
    private String timeNextUpdateUtc;

    @JsonProperty("time_eol_unix")
    private Integer timeEolUnix;

    @JsonProperty("base_code")
    private String baseCode;

    @JsonProperty("rates")
    private Map<Currency, Double> rates;
}