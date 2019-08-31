package cn.frank.futuremonitor.api;

import cn.frank.futuremonitor.dto.response.*;
import org.apache.http.HttpException;

import java.io.IOException;

public interface HuobiApi {

    /**
     * 期货行情
     *
     * @param symbol       "BTC","ETH"...
     * @param contractType 合约类型: this_week:当周 next_week:下周 quarter:季度
     * @param contractCode 合约code
     */
    ContractInfoResponseDTO futureContractInfo(String symbol, String contractType, String contractCode)
            throws HttpException, IOException;

    /**
     * 获取合约指数
     *
     * @param symbol "BTC","ETH"...
     */
    ContractIndexResponseDTO futureContractIndex(String symbol) throws HttpException, IOException;

    /**
     * 获取合约最高限价和最低限价
     *
     * @param symbol       "BTC","ETH"...
     * @param contractType 合约类型: this_week:当周 next_week:下周 quarter:季度
     * @param contractCode 合约code
     */
    PriceLimitResponseDTO futurePriceLimit(String symbol, String contractType, String contractCode)
            throws HttpException, IOException;

    /**
     * 获取当前可用合约总持仓量
     *
     * @param symbol       "BTC","ETH"...
     * @param contractType 合约类型: this_week:当周 next_week:下周 quarter:季度
     * @param contractCode 合约code
     */
    OpenInterestResponseDTO futureOpenInterest(String symbol, String contractType, String contractCode)
            throws HttpException, IOException;

    /**
     * 获取行情深度数据
     *
     * @param symbol "BTC","ETH"...
     * @param type   step0, step1, step2, step3, step4, step5（合并深度0-5）；step0时，不合并深度
     */
    String futureMarketDepth(String symbol, String type) throws HttpException, IOException;

    /**
     * 获取K线数据
     *
     * @param symbol "BTC","ETH"...
     * @param period K线类型 1min, 5min, 15min, 30min, 60min, 1hour,4hour,1day, 1mon
     */
    MarketHistoryKlineResponseDTO futureMarketHistoryKline(String symbol, String period, String size);

    /**
     * 获取聚合行情
     *
     * @param symbol 如"BTC_CW"表示BTC当周合约，"BTC_NW"表示BTC次周合约，"BTC_CQ"表示BTC季度合约
     */
    MarketDetailMergedResponseDTO futureMarketDetailMerged(String symbol) throws HttpException, IOException;

    /**
     * 获取市场最近成交记录
     *
     * @param symbol 如"BTC_CW"表示BTC当周合约，"BTC_NW"表示BTC次周合约，"BTC_CQ"表示BTC季度合约
     * @return size 获取交易记录的数量 [1, 2000]
     */
    MarketDetailTradeResponseDTO futureMarketDetailTrade(String symbol, String size) throws HttpException, IOException;




}
