package cn.frank.futuremonitor.api.impl;

import cn.frank.futuremonitor.api.HuobiApi;
import cn.frank.futuremonitor.dto.response.*;
import cn.frank.futuremonitor.util.HttpUtil;
import cn.frank.futuremonitor.util.HuobiHttpClient;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Data
public class HuobiApiImpl implements HuobiApi {

    private static final String HUOBI_FUTURE_TICKER = "/market/detail/merged?symbol=%s";
    private static final String HUOBI_FUTURE_DEPTH = "/market/depth";
    private static final String HUOBI_FUTURE_KLINE = "/market/history/kline?symbol=%s&period=%s&size=%s";
    private static final String HUOBI_FUTURE_TRADE = "/market/history/trade?symbol=%s&size=%s";
    // 获取合约信息 /v1/contract_contract_info
    private static final String HUOBI_FUTURE_CONTRACT_INFO = "/api/v1/contract_contract_info?symbol=%s&contract_type=%s&contract_code=%s";
    // 获取合约指数v1/contract_index
    private static final String HUOBI_FUTURE_CONTRACT_INDEX = "/api/v1/contract_index?symbol=%s";
    // 获取合约最低最高限价/v1/contract_price_limit
    private static final String HUOBI_FUTURE_CONTRACT_PRICE_LIMIT = "/api/v1/contract_price_limit?symbol=%s&contract_type=%s&contract_code=%s";
    // 获取合约总持仓量
    private static final String HUOBI_FUTURE_CONTRACT_OPEN_INTEREST = "/api/v1/contract_open_interest?symbol=%s&contract_type=%s&contract_code=%s";

    @Value("${huobi.url.prefix}")
    private String urlPrefix;

    private final HuobiHttpClient huobiHttpClient;
    private final HttpUtil httpUtil;

    @Autowired
    public HuobiApiImpl(HuobiHttpClient huobiHttpClient, HttpUtil httpUtil) {
        this.huobiHttpClient = huobiHttpClient;
        this.httpUtil = httpUtil;
    }

    @Override
    public ContractInfoResponseDTO futureContractInfo(String symbol, String contractType, String contractCode) {

        String url = String.format(urlPrefix + HUOBI_FUTURE_CONTRACT_INFO, symbol, contractType, contractCode);

        String response = httpUtil.getForObject(url, String.class);

        return JSON.parseObject(response, ContractInfoResponseDTO.class);
    }

    @Override
    public ContractIndexResponseDTO futureContractIndex(String symbol) {
        String url = String.format(urlPrefix + HUOBI_FUTURE_CONTRACT_INDEX, symbol);

        String response = httpUtil.getForObject(url, String.class);

        return JSON.parseObject(response, ContractIndexResponseDTO.class);
    }

    @Override
    public PriceLimitResponseDTO futurePriceLimit(String symbol, String contractType, String contractCode) {


        String url = String.format(urlPrefix + HUOBI_FUTURE_CONTRACT_PRICE_LIMIT, symbol, contractType, contractCode);

        String response = httpUtil.getForObject(url, String.class);

        return JSON.parseObject(response, PriceLimitResponseDTO.class);
    }

    @Override
    public OpenInterestResponseDTO futureOpenInterest(String symbol, String contractType, String contractCode) {

        String url = String.format(urlPrefix + HUOBI_FUTURE_CONTRACT_OPEN_INTEREST, symbol, contractType, contractCode);

        String response = httpUtil.getForObject(url, String.class);

        return JSON.parseObject(response, OpenInterestResponseDTO.class);
    }

    public String futureMarketDepth(String symbol, String type) {
        Map<String, String> params = new HashMap<>();
        if (!StringUtils.isEmpty(symbol)) {
            params.put("symbol", symbol);
        }
        if (!StringUtils.isEmpty(type)) {
            params.put("type", type);
        }
        return huobiHttpClient.doGet(urlPrefix + HUOBI_FUTURE_DEPTH, params);
    }

    public MarketHistoryKlineResponseDTO futureMarketHistoryKline(String symbol, String period, String size) {


        String url = String.format(urlPrefix + HUOBI_FUTURE_KLINE, symbol, period, size);

        String response = httpUtil.getForObject(url, String.class);

        return JSON.parseObject(response, MarketHistoryKlineResponseDTO.class);

    }

    public MarketDetailMergedResponseDTO futureMarketDetailMerged(String symbol) {
        String url = String.format(urlPrefix + HUOBI_FUTURE_TICKER, symbol);

        String response = httpUtil.getForObject(url, String.class);

        return JSON.parseObject(response, MarketDetailMergedResponseDTO.class);
    }

    public MarketDetailTradeResponseDTO futureMarketDetailTrade(String symbol, String size) {
        String url = String.format(urlPrefix + HUOBI_FUTURE_TRADE, symbol, size);

        String response = httpUtil.getForObject(url, String.class);

        return JSON.parseObject(response, MarketDetailTradeResponseDTO.class);
    }

    public MarketHistoryTradeResponseDTO futureMarketHistoryTrade(String symbol, String size) {

        String url = String.format(urlPrefix + HUOBI_FUTURE_TRADE, symbol, size);

        String response = httpUtil.getForObject(url, String.class);

        return JSON.parseObject(response, MarketHistoryTradeResponseDTO.class);
    }

}
