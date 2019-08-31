package cn.frank.futuremonitor.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OpenInterestData {

    private String symbol;

    @JsonProperty("contract_type")
    private String contractType;

    private BigDecimal volume;

    private BigDecimal amount;

    @JsonProperty("contract_code")
    private String contractCode;
}
