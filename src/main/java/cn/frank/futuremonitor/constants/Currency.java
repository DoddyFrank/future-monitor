package cn.frank.futuremonitor.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Currency {

    BTC("BTC"),
    LTC("LTC"),
    BCH("BCH"),
    ETH("ETH");

    private String symbol;
}
