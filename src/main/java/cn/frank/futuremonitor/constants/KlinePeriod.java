package cn.frank.futuremonitor.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KlinePeriod {
    ONE_MIN("1min"),
    FIVE_MIN("5min"),
    FIFTEEN_MIN("15min"),
    THIRTY_MIN("30min"),
    SIXTY_MIN("60min"),
    FOUR_HOUR("4hour"),
    ONE_DAY("1day"),
    ONE_MONTH("1mon");

    private String type;
}
