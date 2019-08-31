package cn.frank.futuremonitor.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderResultSU {

    private int index;

    @JsonProperty("order_id")
    private long orderId;

    @JsonProperty("client_order_id")
    private long clientOrderId;

}
