package cn.frank.futuremonitor.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContractInfoData {
    private String symbol;

    @JsonProperty("contract_code")
    private String contractCode;

    @JsonProperty("contract_code")
    private String contractType;

    @JsonProperty("contract_size")
    private BigDecimal contractSize;

    @JsonProperty("price_tick")
    private BigDecimal priceTick;

    @JsonProperty("delivery_date")
    private String deliveryDate;

    @JsonProperty("create_date")
    private String createDate;

    @JsonProperty("contract_status")
    private String contractStatus;
}
