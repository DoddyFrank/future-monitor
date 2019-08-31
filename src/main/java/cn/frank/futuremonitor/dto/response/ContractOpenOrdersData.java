package cn.frank.futuremonitor.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ContractOpenOrdersData {

    private List<ContractOpenOrdersDataDetail> orders;

    @JsonProperty("total_page")
    private int totalPage;

    @JsonProperty("current_page")
    private int currentPage;

    @JsonProperty("total_size")
    private int totalSize;
}
