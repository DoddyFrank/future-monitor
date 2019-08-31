package cn.frank.futuremonitor.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class ContractCancelAllResponseDTO extends BaseResponseDTO {

    private ContractCancelAllData data;

}
