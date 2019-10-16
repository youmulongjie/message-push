package com.andy.messagepush.getui;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 个推平台 APP应用配置
 * Author: Andy.wang
 * Date: 2019/10/14 17:17
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "getui")
public class GetUIAppInfo {
    /**
     * AppID
     */
    private String appId;
    /**
     * AppKey
     */
    private String appKey;
    /**
     * MasterSecret
     */
    private String masterSecret;
}
