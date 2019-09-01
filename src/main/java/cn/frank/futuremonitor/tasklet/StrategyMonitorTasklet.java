package cn.frank.futuremonitor.tasklet;

import cn.frank.futuremonitor.constants.Currency;
import cn.frank.futuremonitor.constants.KlinePeriod;
import cn.frank.futuremonitor.constants.StrategyReturnCode;
import cn.frank.futuremonitor.monitor.StrategyMonitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//注意，这里要是实盘记得把DemoTasklet的Service注解注释掉，否则单例模式的isTesting设置会有问题
@Service
@Slf4j
public class StrategyMonitorTasklet {

    private final StrategyMonitor strategyMonitor;


    @Autowired
    public StrategyMonitorTasklet(StrategyMonitor strategyMonitor) {
        this.strategyMonitor = strategyMonitor;
    }


    @Scheduled(cron = "5 0/1 *  * * ? ")
    public void runStrategyA() {

        log.debug("本次周期任务执行开始。");

        StrategyReturnCode returnCode = executeMonitor(0);

        log.debug("本次周期任务执行结束: {}", returnCode);

    }

    @Scheduled(cron = "25 0/1 *  * * ? ")
    public void runStrategyB() {

        log.debug("本次周期任务执行开始。");

        StrategyReturnCode returnCode = executeMonitor(0);

        log.debug("本次周期任务执行结束: {}", returnCode);

    }

    @Scheduled(cron = "45 0/1 *  * * ? ")
    public void runStrategyC() {

        log.debug("本次周期任务执行开始。");

        StrategyReturnCode returnCode = executeMonitor(0);

        log.debug("本次周期任务执行结束: {}", returnCode);

    }

    private StrategyReturnCode executeMonitor(int retryCount) {

        //防止各种HTTP调用失败，每个周期的定时任务设定重试次数，如果失败则在允许次数范围内重试，尽量保证本周期逻辑完全跑完。
        int maxRetryLimit = 3;

        if (retryCount < maxRetryLimit) {
            StrategyReturnCode returnCode = strategyMonitor.monitor(Currency.BTC, 20, KlinePeriod.ONE_DAY, "BTC190927");

            log.info("本次周期任务执行结果; {}", returnCode);

            if (returnCode != StrategyReturnCode.SUCCESS) {
                retryCount++;
                return executeMonitor(retryCount);
            } else {
                return StrategyReturnCode.SUCCESS;
            }
        } else {

            log.info("本次周期任务执行结果; {}", StrategyReturnCode.ERR_MAX_RETRY_LIMIT);

            return StrategyReturnCode.ERR_MAX_RETRY_LIMIT;
        }
    }

}
