package cn.frank.futuremonitor.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class BatchOrderResult {

    private List<OrderResultFA> errors;

    private List<OrderResultSU> success;
}
