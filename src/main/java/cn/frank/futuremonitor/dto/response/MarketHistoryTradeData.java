package cn.frank.futuremonitor.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class MarketHistoryTradeData {

    private long id;

    private long ts;

    private List<MarketHistoryTradeDataL2> data;
}
