package cn.frank.futuremonitor.api;

import lombok.Data;

@Data
public class HbdmSignature {
    private String accessKey;
    private String secretKey;

    public HbdmSignature() {
        super();
    }

    public HbdmSignature(String accessKey, String secretKey) {
        super();
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

}
