package cn.frank.futuremonitor.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContractCancelAllDataErr {
    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("err_code")
    private int errCode;

    @JsonProperty("err_msg")
    private int errMsg;
}
