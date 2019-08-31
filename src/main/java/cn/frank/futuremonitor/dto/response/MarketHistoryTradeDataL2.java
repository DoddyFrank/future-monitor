package cn.frank.futuremonitor.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MarketHistoryTradeDataL2 {
    private long id;

    private long ts;

    private int amount;

    private String direction;

    private BigDecimal price;
}
