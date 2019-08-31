package cn.frank.futuremonitor.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StrategyReturnCode {
    SUCCESS(0, "SUCCESS"),
    ERR_FETCH_K_LINE_FAIL(1, "ERR_FETCH_K_LINE_FAIL"),
    ERR_RETURN_EMPTY_DATA(2, "返回K线数量为空"),
    ERR_RETURN_LIST_NOT_MATCH(3, "返回的K线数量不匹配"),
    ERR_ORDER_FAILURE(4, "下单返回状态码错误"),
    ERR_ORDER_EXCEPTION(5, "下单调用异常"),
    ERR_PRE_CHECK_FAIL(6, "下单前检查各项参数失败"),
    ERR_SEND_SMS(7, "发送短信通知失败"),
    ERR_MAX_RETRY_LIMIT(999, "达到最大重试次数");

    private int code;
    private String desc;
}
