package cn.frank.futuremonitor.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LastDirection {

    INIT(0, "INIT"),
    BULL(1, "Bull"),
    BEAR(2, "Bear");

    private int code;
    private String desc;
}
