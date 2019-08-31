package cn.frank.futuremonitor.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class MarketHistoryKlineResponseDTO extends BaseResponseDTO {

    private String ch; //数据所属的 channel，格式： market.period

    private List<MarketHistoryKLineData> data;

}
