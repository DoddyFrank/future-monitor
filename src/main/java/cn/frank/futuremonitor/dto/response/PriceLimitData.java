package cn.frank.futuremonitor.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceLimitData {
    private String symbol;

    @JsonProperty("high_limit")
    private BigDecimal highLimit;

    @JsonProperty("low_limit")
    private BigDecimal lowLimit;

    @JsonProperty("contract_code")
    private String contractCode;

    @JsonProperty("contract_type")
    private String contractType;
}
