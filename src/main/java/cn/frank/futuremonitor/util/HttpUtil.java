package cn.frank.futuremonitor.util;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class HttpUtil {

    private RestTemplate restTemplate;

    private HttpHeaders headers;


    @PostConstruct
    public void init() {
        initGeneralRestTemplate();
        initHeader();
    }

    private void initHeader() {
        headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
    }

    private void initGeneralRestTemplate() {
        HttpClient httpClient = HttpClients.custom().setMaxConnPerRoute(10).setMaxConnTotal(10).build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        requestFactory.setReadTimeout(3000);
        requestFactory.setConnectTimeout(3000);

        restTemplate = new RestTemplate(requestFactory);
    }


    public <T> T postForObject(String url, Object request, Class<T> responseType) {
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        return restTemplate.postForObject(url, entity, responseType);
    }

    public <T> T getForObject(String url, Class<T> responseType) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        log.debug("发送请求：{}", url);
        T response = restTemplate.getForObject(url, responseType);
        stopwatch.stop();
        log.debug("请求耗时: {}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return response;
    }

}
