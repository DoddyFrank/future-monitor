package cn.frank.futuremonitor.dto.response;

import lombok.Data;

@Data
public class MarketDetailTradeData {

    private String amount;
    private String direction;
    private long id;
    private String price;
    private long ts;
}
