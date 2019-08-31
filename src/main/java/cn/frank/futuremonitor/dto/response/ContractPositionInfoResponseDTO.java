package cn.frank.futuremonitor.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class ContractPositionInfoResponseDTO extends BaseResponseDTO {

    private List<ContractPositionInfoData> data;

}
