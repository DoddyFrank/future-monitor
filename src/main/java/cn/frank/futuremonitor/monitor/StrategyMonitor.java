package cn.frank.futuremonitor.monitor;

import cn.frank.futuremonitor.api.HuobiApi;
import cn.frank.futuremonitor.constants.Currency;
import cn.frank.futuremonitor.constants.KlinePeriod;
import cn.frank.futuremonitor.constants.LastDirection;
import cn.frank.futuremonitor.constants.StrategyReturnCode;
import cn.frank.futuremonitor.dto.response.MarketHistoryKLineData;
import cn.frank.futuremonitor.util.IndexUtils;
import cn.frank.futuremonitor.util.SMSUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 自定义新的变量，结合交易量的均线系统
 */
@Service
@Slf4j
@Data
public class StrategyMonitor {

    private final HuobiApi huobiApi;
    private final SMSUtils smsUtils;

    //用来防止在短时间内围绕均线波动的情况，只有达到阈值，才进行交易,现在默认
    private int tradingDecision = 0;
    private int decisionThreshold = 3;

    private LastDirection lastDirection = LastDirection.INIT;

    @Autowired
    public StrategyMonitor(HuobiApi huobiApi, SMSUtils smsUtils) {
        this.huobiApi = huobiApi;
        this.smsUtils = smsUtils;
    }


    public StrategyReturnCode monitor(Currency currency, int movingAverageNumber, KlinePeriod period, String contractCode) {

        List<MarketHistoryKLineData> kLines;

        String klineSymbol = currency.getSymbol() + "_CQ";

        try {
            kLines = huobiApi.futureMarketHistoryKline(klineSymbol, period.getType(), String.valueOf(movingAverageNumber)).getData();
        } catch (Exception e) {
            log.error("获取K线数据失败: {}", e);
            return StrategyReturnCode.ERR_FETCH_K_LINE_FAIL;
        }

        //这里用debug level，让info级别日志更清爽
        log.debug("获取K线数据" + kLines);

        //防止数据不对
        if (CollectionUtils.isEmpty(kLines)) {
            log.error("K线数据样本为空");
            return StrategyReturnCode.ERR_RETURN_EMPTY_DATA;
        }

        if (kLines.size() != movingAverageNumber) {
            log.error("K线数据样本不是期望值");
            return StrategyReturnCode.ERR_RETURN_LIST_NOT_MATCH;
        }

        //根据N条K线获取均线
        BigDecimal MA = IndexUtils.MA(kLines);

        //获取最新的一条K线，查看成交价
        BigDecimal latestClosePrice = kLines.get(movingAverageNumber - 1).getClose();

        log.info("MA : {}, 当前close价格 : {}", MA, latestClosePrice);

        StrategyReturnCode returnCode = StrategyReturnCode.SUCCESS;
        if (latestClosePrice.compareTo(MA) > 0) {
            //处理多头情况
            if (tradingDecision < 0) {
                tradingDecision = 0;
            }

            tradingDecision = tradingDecision + 1;
            if (tradingDecision >= decisionThreshold) {
                returnCode = handleLong();
            } else {
                log.info("静默期： {}", tradingDecision);
            }

        } else if (latestClosePrice.compareTo(MA) < 0) {
            //处理空头情况
            if (tradingDecision > 0) {
                tradingDecision = 0;
            }

            tradingDecision = tradingDecision - 1;
            if (tradingDecision <= -decisionThreshold) {
                returnCode = handleShort();
            } else {
                log.info("静默期： {}", tradingDecision);
            }
        } else {
            log.info("当前价格等于MA60，跳过");
        }
        return returnCode;
    }

    private StrategyReturnCode handleLong() {

        if (lastDirection.getCode() == LastDirection.BEAR.getCode()) {
            smsUtils.sendSmsTradeCodeSuccess("BullAction");
        }

        lastDirection = LastDirection.BULL;

        return StrategyReturnCode.SUCCESS;
    }

    private StrategyReturnCode handleShort() {

        if (lastDirection.getCode() == LastDirection.BULL.getCode()) {
            smsUtils.sendSmsTradeCodeSuccess("BearAction");
        }

        lastDirection = LastDirection.BEAR;

        return StrategyReturnCode.SUCCESS;
    }

}
