package com.andy.messagepush.getui.push;

import com.andy.messagepush.getui.GetUIAppInfo;
import com.andy.messagepush.getui.param.GetUIStyleParam;
import com.andy.messagepush.getui.template.GetUIPushTemplate;
import com.andy.messagepush.getui.template.GetUIStyleTemplate;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.AbstractTemplate;
import com.gexin.rp.sdk.template.style.AbstractNotifyStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 个推平台 “toApp”，对指定应用的所有（或符合筛选条件的）用户群发推送消息。有定时、定速功能。参考：http://docs.getui.com/getui/server/java/push/
 *
 *
 * <pre>
 *     注意事项：此接口频次限制100次/天，每分钟不能超过5次，定时推送功能需要申请开通才可以使用
 * </pre>
 *
 * Author: Andy.wang
 * Date: 2019/10/17 10:08
 */
@Component
@Slf4j
public class GETUIPushToApp {
    private static GETUIPushToApp pushToApp;

    @PostConstruct
    public void init() {
        pushToApp = this;
        pushToApp.appInfo = this.appInfo;
    }

    @Autowired
    private GetUIAppInfo appInfo;

    //[NotificationTemplate] start

    /**
     * style0 （普通）样式 + NotificationTemplate 推送
     *
     * @param title               标题
     * @param text                内容
     * @param transmissionContent 透析内容（可为空）
     * @param pushTime            定时推送时间
     * @return
     */
    public final static String pushNotificationStyle0(String title, String text, String transmissionContent, String pushTime) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);


        AbstractNotifyStyle style = GetUIStyleTemplate.style0(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.notificationTemplate(style, transmissionContent, GetUIPushTemplate.TransmissionType.ONE);

            return GETUIPushToApp.push(template, pushTime);
        } catch (Exception e) {
            log.error("toApp‘执行群推’失败!", e);
        }

        return null;
    }

    /**
     * style6-1 （图片）样式 + NotificationTemplate 推送
     *
     * @param title               标题
     * @param text                内容
     * @param picUrl              通知栏图片URL地址
     * @param transmissionContent 透析内容（可为空）
     * @param pushTime            定时推送时间
     * @return
     */
    public final static String pushNotificationStyle6Pic(String title, String text, String picUrl, String transmissionContent, String pushTime) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);

        styleParam.setBigStyle6PicUrl(picUrl);


        AbstractNotifyStyle style = GetUIStyleTemplate.style6Pic(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.notificationTemplate(style, transmissionContent, GetUIPushTemplate.TransmissionType.ONE);

            return GETUIPushToApp.push(template, pushTime);
        } catch (Exception e) {
            log.error("toApp‘执行群推’失败!", e);
        }

        return null;
    }


    /**
     * style6-2 （长文本）样式 + NotificationTemplate 推送
     *
     * @param title               标题
     * @param text                内容
     * @param bigText             长文本
     * @param transmissionContent 透析内容（可为空）
     * @param pushTime            定时推送时间
     * @return
     */
    public final static String pushNotificationStyle6Text(String title, String text, String bigText, String transmissionContent, String pushTime) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);

        styleParam.setBigStyle6Text(bigText);


        AbstractNotifyStyle style = GetUIStyleTemplate.style6Text(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.notificationTemplate(style, transmissionContent, GetUIPushTemplate.TransmissionType.ONE);

            return GETUIPushToApp.push(template, pushTime);
        } catch (Exception e) {
            log.error("toApp‘执行群推’失败!", e);
        }

        return null;
    }
    //[NotificationTemplate] end


    //[TransmissionTemplate] start

    /**
     * TransmissionContent 推送
     *
     * @param transmissionContent
     * @param pushTime            定时推送时间
     * @return
     */
    public final static String pushTransmission(String transmissionContent, String pushTime) {
        try {
            AbstractTemplate template = GetUIPushTemplate.transmissionTemplate(transmissionContent, GetUIPushTemplate.TransmissionType.ONE);

            return GETUIPushToApp.push(template, pushTime);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    //[TransmissionTemplate] end

    //[LinkTemplate] start

    /**
     * style0 （普通）样式 + LinkTemplate 推送
     *
     * @param title    标题
     * @param text     内容
     * @param linkUrl  跳转链接地址
     * @param pushTime 定时推送时间
     * @return
     */
    public final static String pushLinkStyle0(String title, String text, String linkUrl, String pushTime) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);

        AbstractNotifyStyle style = GetUIStyleTemplate.style0(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.linkTemplate(style, linkUrl);

            return GETUIPushToApp.push(template, pushTime);
        } catch (Exception e) {
            log.error("toApp‘执行群推’失败!", e);
        }

        return null;
    }

    /**
     * style6-1 （图片）样式 + LinkTemplate 推送
     *
     * @param title    标题
     * @param text     内容
     * @param picUrl   通知栏图片URL地址
     * @param linkUrl  跳转链接地址
     * @param pushTime 定时推送时间
     * @return
     */
    public final static String pushLinkStyle6Pic(String title, String text, String picUrl, String linkUrl, String pushTime) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);

        styleParam.setBigStyle6PicUrl(picUrl);


        AbstractNotifyStyle style = GetUIStyleTemplate.style6Pic(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.linkTemplate(style, linkUrl);

            return GETUIPushToApp.push(template, pushTime);
        } catch (Exception e) {
            log.error("toApp‘执行群推’失败!", e);
        }

        return null;
    }


    /**
     * style6-2 （长文本）样式 + LinkTemplate 推送
     *
     * @param title    标题
     * @param text     内容
     * @param bigText  长文本
     * @param linkUrl  跳转链接地址
     * @param pushTime 定时推送时间
     * @return
     */
    public final static String pushLinkStyle6Text(String title, String text, String bigText, String linkUrl, String pushTime) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);

        styleParam.setBigStyle6Text(bigText);


        AbstractNotifyStyle style = GetUIStyleTemplate.style6Text(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.linkTemplate(style, linkUrl);

            return GETUIPushToApp.push(template, pushTime);
        } catch (Exception e) {
            log.error("toApp‘执行群推’失败!", e);
        }

        return null;
    }
    //[LinkTemplate] end

    ///////////以上封装方法，便于调用（若不满足情况，可用下面方法自己组装，调用可参考测试类ToAPPPushTest.java）//////////////

    /**
     * “执行群推” 消息
     * <pre>
     *     功能说明；对指定应用的所有用户群发推送消息。有定时、定速功能。
     *
     *     pushTime 参数注意：
     *     1）定时推送功能需要申请开通才可以使用
     *     2）使用推送的sdk包版本必须大于等于4.0.1.17
     *     3）设定推送的时间格式为yyyyMMddHHmm 例如:201908081900,任务将会在2019年08月08日19点00分推送。
     *     4）对时间的设定有一定的要求:
     *      4-1）时间格式不正确 提交任务时 将直接返回失败。
     *      4-2）下发时间小于当前时间 提交任务时将直接返回失败。
     *      4-3）下发时间超过系统所允许的最大时间点 提交任务 将直接返回失败
     * </pre>
     *
     * @param template
     * @param pushTime 定时推送时间（需在‘个推’平台开通权限，没有开通权限则设置可以为空）
     * @return
     */
    public final static String push(AbstractTemplate template, String pushTime) {
        log.info("向app用户【{}】‘toApp’一条消息.", pushToApp.appInfo.getAppId());
        // 配置返回每个用户返回用户状态，可选
        System.setProperty("gexin_pushList_needDetails", "true");

        IGtPush push = new IGtPush(pushToApp.appInfo.getAppKey(),
                pushToApp.appInfo.getMasterSecret());

        IPushResult ret = push.pushMessageToApp(appMessage(template, pushTime));

        String result = null;
        if (ret != null) {
            result = ret.getResponse().toString();
            log.info("推送结果：{}", result);
        } else {
            log.info("服务器响应异常");
        }

        return result;
    }

    /**
     * 设置 toApp 消息类型 AppMessage
     *
     * <pre>
     *     参数信息：
     *      isOffline 	        boolean 	否 	false 	是否保持离线消息
     *      offlineExpireTime 	long 	    否 	1小时 	过多久该消息离线失效（单位毫秒） 支持1-72小时*3600000毫秒
     *      pushNetWorkType 	long 	    否 	0 	    推送网络要求
     *                                                      0:联网方式不限;
     *                                                      1:仅wifi;
     *                                                      2:仅移动网络
     *      data 	        ITemplate 	    是 	无 	    设置推送效果，点击查看详细说明
     *      appIdList 	List<String> 	    是 	无 	    appId列表，只有第一个有效
     *      conditions 	AppConditions 	    否 	无 	    筛选条件交并补功能
     *      speed 	            int 	    否 	无 	    定速推送（例如100，个推控制下发速度在100条/秒左右）
     *      pushTime 	        String 	    否 	无 	    定时推送，格式要求为yyyyMMddHHmm 需要申请开通套餐
     *
     *
     * </pre>
     *
     * @param template
     * @param pushTime 定时推送（可以为空）
     * @return
     */
    private final static AppMessage appMessage(AbstractTemplate template, String pushTime) {
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setOffline(true);
        message.setOfflineExpireTime(2 * 3600 * 1000);
        message.setPushNetWorkType(0);

        List<String> appIdList = new ArrayList<String>();
        appIdList.add(pushToApp.appInfo.getAppId());
        message.setAppIdList(appIdList);

        // 定速推送
        message.setSpeed(100);
        // 定时推送（需要申请开通套餐）：格式要求为yyyyMMddHHmm
        if (!StringUtils.isEmpty(pushTime)) {
            try {
                /**
                 * 对时间的设定有一定的要求:
                 *
                 *     1）时间格式不正确 提交任务时 将直接返回失败。
                 *     2）下发时间小于当前时间 提交任务时将直接返回失败。
                 *     3）下发时间超过系统所允许的最大时间点 提交任务 将直接返回失败
                 */
                message.setPushTime(pushTime);
            } catch (ParseException e) {
                log.warn("定时推送 时间设置格式不正确。期望格式是：yyyyMMddHHmm.", e);
            }
        }

        return message;
    }


}
