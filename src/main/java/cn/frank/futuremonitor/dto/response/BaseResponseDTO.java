package cn.frank.futuremonitor.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BaseResponseDTO {

    protected String status;

    protected long ts;

    @JsonProperty("err_code")
    protected String errCode;

    @JsonProperty("err_msg")
    protected String errMsg;
}
