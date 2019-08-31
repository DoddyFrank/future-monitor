package cn.frank.futuremonitor.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class OrderResponseDTO extends BaseResponseDTO {

    @JsonProperty("order_id")
    private long orderId;

    @JsonProperty("client_order_id")
    private long clientOrderId;

}
