package cn.frank.futuremonitor.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class PriceLimitResponseDTO extends BaseResponseDTO {
    private List<PriceLimitData> data;
}
