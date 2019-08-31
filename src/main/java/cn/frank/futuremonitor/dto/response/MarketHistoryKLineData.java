package cn.frank.futuremonitor.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MarketHistoryKLineData {
    private long id;

    private int vol;

    private int count;

    private BigDecimal open;

    private BigDecimal close;

    private BigDecimal low;

    private BigDecimal high;

    private BigDecimal amount;
}
