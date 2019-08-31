package cn.frank.futuremonitor.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class SMSUtils {

    //SMS configuration
    @Value("${sms.access.key.id}")
    private String smsAccessKeyId;
    @Value("${sms.access.secret}")
    private String smsSecret;
    @Value("${sms.domain}")
    private String smsDomain;
    @Value("${sms.version}")
    private String smsVersion;
    @Value("${sms.action}")
    private String smsAction;
    @Value("${sms.region.id}")
    private String smsRegionId;
    @Value("${sms.phone.number}")
    private String smsPhoneNumber;
    @Value("${sms.sign.name}")
    private String smsSignName;

    @Value("${sms.init.code}")
    private String smsInitCode;
    @Value("${sms.trade.code}")
    private String smsTradeCode;

    private IAcsClient client;
    private CommonRequest request;

    @PostConstruct
    public void init() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsAccessKeyId, smsSecret);
        client = new DefaultAcsClient(profile);
        request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(smsDomain);
        request.setVersion(smsVersion);
        request.setAction(smsAction);
        request.putQueryParameter("RegionId", smsRegionId);
        request.putQueryParameter("PhoneNumbers", smsPhoneNumber);
        request.putQueryParameter("SignName", smsSignName);
        request.putQueryParameter("TemplateCode", smsInitCode);
        //boolean initStatus = sendSmsInitCodeSuccess();
        //log.info("初始化系统短信通知状态： {}", initStatus);
        log.info("初始化短信功能成功，Good luck！");
    }

    private boolean sendSmsInitCodeSuccess() {
        String smsContent = "{\"code\":\"%s\"}";

        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String now = dtf2.format(time);

        String smsContentDetail = String.format(smsContent, now);
        request.putQueryParameter("TemplateCode", smsInitCode);
        request.putQueryParameter("TemplateParam", smsContentDetail);
        log.info("短信详细内容: {}", request);
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("send SMS: {}", response.getData());
        } catch (ClientException e) {
            return false;
        }
        return true;
    }

    public boolean sendSmsTradeCodeSuccess(String action) {
        String smsContent = "{\"code\":\"%s\"}";

        String smsContentDetail = String.format(smsContent, action);

        request.putQueryParameter("TemplateCode", smsTradeCode);
        request.putQueryParameter("TemplateParam", smsContentDetail);
        log.info("短信详细内容: {}", request);
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("send SMS: {}", response.getData());
        } catch (ClientException e) {
            return false;
        }
        return true;
    }

}
