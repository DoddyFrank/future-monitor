package cn.frank.futuremonitor.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContractOpenOrdersDataDetail {

    private String symbol;

    @JsonProperty("contract_type")
    private String contractType;

    @JsonProperty("contract_code")
    private String contractCode;

    private BigDecimal volume;

    private BigDecimal price;

    @JsonProperty("order_price_type")
    private String orderPriceType;

    private String direction;

    private String offset;

    @JsonProperty("lever_rate")
    private int leverRate;

    @JsonProperty("order_id")
    private long orderId;

    @JsonProperty("client_order_id")
    private long clientOrderId;

    @JsonProperty("created_at")
    private long createdAt;

    @JsonProperty("trade_volume")
    private BigDecimal tradeVolume;

    @JsonProperty("trade_turnover")
    private BigDecimal tradeTurnover;

    private BigDecimal fee;

    @JsonProperty("trade_avg_price")
    private BigDecimal tradeAvgPrice;

    @JsonProperty("margin_frozen")
    private BigDecimal marginFrozen;

    private BigDecimal profit;

    /**
     * (3未成交 4部分成交 5部分成交已撤单 6全部成交 7已撤单)
     */
    private int status;

    @JsonProperty("order_source")
    private String orderSource;

}
