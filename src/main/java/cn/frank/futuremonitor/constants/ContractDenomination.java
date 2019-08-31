package cn.frank.futuremonitor.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum ContractDenomination {

    BTC("BTC", "100", ""),
    ETH("ETH", "10", ""),
    LTC("LTC", "10", ""),
    BCH("BCH", "10", "");

    private String symbol;

    private String denomination;

    private String desc;

    public static BigDecimal getDenominationByCurrency(Currency currency) {
        for (ContractDenomination contractDenomination : ContractDenomination.values()) {
            if (contractDenomination.symbol.equalsIgnoreCase(currency.getSymbol())) {
                return new BigDecimal(contractDenomination.getDenomination());
            }
        }
        return null;
    }
}
