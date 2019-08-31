package cn.frank.futuremonitor.util;

import cn.frank.futuremonitor.dto.response.MarketHistoryKLineData;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class IndexUtils {

    /**
     * 此方法用来在某些特定阶段看空看多使用
     * 比如看到确定的鱼尾行情，想要规避损失的时候，又需要避免系统性风险时
     */
    public static BigDecimal FixedPrice(final List<MarketHistoryKLineData> list) {
        return new BigDecimal("10300.00");
    }

    public static BigDecimal MA(final List<MarketHistoryKLineData> list) {

        double ma = list.get(0).getClose().doubleValue();
        for (int i = 1; i < list.size(); i++) {
            ma += list.get(i).getClose().doubleValue();
        }
        return new BigDecimal(String.valueOf(ma / list.size()));
    }


    /**
     * 量价平均指数：自己总结出来，通常MA指数滞后性比较严重，震荡区间会回吐不少利润，鱼尾区间也会回吐比较多的利润。
     * 参考大量文章和实际交易图发现，通常趋势指标加入交易量会减少每次止损量，为何不采用一个简单结合交易量的均线指标呢？
     * 类似EMA指标，不过加权方式采取以交易量为指标的加权方式，多次回测感觉效果比较明显，尤其是在趋势尾部区域。
     * 高波动性产品回测效果尤其好。
     */
    public static BigDecimal VMA(final List<MarketHistoryKLineData> list) {

        BigDecimal wmaTotal = new BigDecimal("0");
        int totalCount = 0;
        for (MarketHistoryKLineData marketHistoryKLineData : list) {
            BigDecimal average = marketHistoryKLineData.getClose()
                    .add(marketHistoryKLineData.getOpen())
                    .add(marketHistoryKLineData.getHigh())
                    .add(marketHistoryKLineData.getLow())
                    .multiply(BigDecimal.valueOf(marketHistoryKLineData.getCount()))
                    .divide(new BigDecimal("4"), 2);
            wmaTotal = wmaTotal.add(average);
            totalCount += marketHistoryKLineData.getCount();
        }
        return wmaTotal.divide(BigDecimal.valueOf(totalCount), 2);
    }


    /**
     * 最小二乘移动平均线
     * 优点：大波段中任然能捕捉到反向的小波段
     * 缺点：相对于MA EMA VMA等，交易次数相对较多，交易成本升高
     * <p>
     * 计算:
     * LSMA[i]=Sum/L2
     * <p>
     * 其中
     * Sum[i] = (Period - N)*Price[i] + (Period - N - 1)*Price[i-1] + … + (1 - N)*Price[i-Period+1],
     * N = (Period + 1)/3,
     * L2 = Period*(Period + 1)/6
     */
    //TODO update the LSMA calculation logic
    public static BigDecimal LSMA(final List<MarketHistoryKLineData> list) {

        BigDecimal sum = new BigDecimal("0");
        BigDecimal period = new BigDecimal(list.size());
        BigDecimal N = period.add(new BigDecimal("1")).divide(new BigDecimal("3"), 2);
        BigDecimal L2 = period.multiply(period.add(new BigDecimal("1"))).divide(new BigDecimal("6"), 2);
        for (int i = 0; i < list.size(); i++) {
            MarketHistoryKLineData data = list.get(i);
            sum = sum.add(
                    data.getClose().multiply(
                            period.subtract(new BigDecimal(i)).subtract(N)
                    )
            );
        }
        return sum.divide(L2, 2);
    }


    public static Double EMA(final List<Double> list, final int number) {
        // 开始计算EMA值，
        Double k = 2.0 / (number + 1.0);// 计算出序数
        Double ema = list.get(0);// 第一天ema等于当天收盘价
        for (int i = 1; i < list.size(); i++) {
            // 第二天以后，当天收盘 收盘价乘以系数再加上昨天EMA乘以系数-1
            ema = list.get(i) * k + ema * (1 - k);
        }
        return ema;
    }
}
