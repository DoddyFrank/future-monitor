package cn.frank.futuremonitor.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class MarketDetailTradeTick {
    private long id;

    private long ts;

    private List<MarketDetailTradeData> data;
}
