package cn.frank.futuremonitor.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FutureContractAccountData {

    private String symbol;

    @JsonProperty("margin_balance")
    private BigDecimal marginBalance;

    @JsonProperty("margin_position")
    private BigDecimal marginPosition;

    @JsonProperty("margin_frozen")
    private BigDecimal marginFrozen;

    @JsonProperty("margin_available")
    private BigDecimal marginAvailable;

    @JsonProperty("profit_real")
    private BigDecimal profitReal;

    @JsonProperty("profit_unreal")
    private BigDecimal profitUnreal;

    @JsonProperty("risk_rate")
    private BigDecimal riskRate;

    @JsonProperty("liquidation_price")
    private BigDecimal liquidationPrice;

    @JsonProperty("withdraw_available")
    private BigDecimal withdrawAvailable;

    @JsonProperty("lever_rate")
    private int leverRate;

}
