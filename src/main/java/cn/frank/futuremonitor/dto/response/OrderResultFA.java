package cn.frank.futuremonitor.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderResultFA {

    private int index;

    @JsonProperty("err_code")
    private int errCode;

    @JsonProperty("err_msg")
    private String errMsg;
}
