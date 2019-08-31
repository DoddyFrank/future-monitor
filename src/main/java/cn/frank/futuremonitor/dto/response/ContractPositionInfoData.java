package cn.frank.futuremonitor.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContractPositionInfoData {

    private String symbol;

    @JsonProperty("contract_code")
    private String contractCode;

    @JsonProperty("contract_type")
    private String contractType;

    private int volume;

    private int available;

    private BigDecimal frozen;

    @JsonProperty("cost_open")
    private BigDecimal costOpen;

    @JsonProperty("cost_hold")
    private BigDecimal costHold;

    @JsonProperty("profit_unreal")
    private BigDecimal profitUnreal;

    @JsonProperty("profit_rate")
    private BigDecimal profitRate;

    private BigDecimal profit;

    @JsonProperty("position_margin")
    private BigDecimal positionMargin;

    @JsonProperty("lever_rate")
    private int leverRate;

    private String direction;
}
