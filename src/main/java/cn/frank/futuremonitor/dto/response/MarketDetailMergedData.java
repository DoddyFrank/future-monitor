package cn.frank.futuremonitor.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MarketDetailMergedData {

    private long id;

    private BigDecimal vol;

    private int count;

    private BigDecimal open;

    private BigDecimal close;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal amount;

    private String bid;

    private String ask;

    //private BidAskData ask;
}
