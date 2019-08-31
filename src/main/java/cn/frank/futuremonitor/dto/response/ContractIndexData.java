package cn.frank.futuremonitor.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContractIndexData {

    private String symbol;

    @JsonProperty("index_price")
    private BigDecimal indexPrice;

    @JsonProperty("index_ts")
    private long indexTs;
}
