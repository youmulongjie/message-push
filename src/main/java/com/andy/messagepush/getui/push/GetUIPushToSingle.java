package com.andy.messagepush.getui.push;

import com.andy.messagepush.getui.GetUIAppInfo;
import com.andy.messagepush.getui.param.GetUIStyleParam;
import com.andy.messagepush.getui.template.GetUIPushTemplate;
import com.andy.messagepush.getui.template.GetUIStyleTemplate;
import com.gexin.rp.sdk.base.IBatch;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.AbstractTemplate;
import com.gexin.rp.sdk.template.style.AbstractNotifyStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

/**
 * Description: 个推平台 “单推”，指向单个用户推送消息。参考：http://docs.getui.com/getui/server/java/push/
 * Author: Andy.wang
 * Date: 2019/10/15 15:53
 */
@Component
@Slf4j
public class GetUIPushToSingle {
    private static GetUIPushToSingle pushToSingle;

    @PostConstruct
    public void init() {
        pushToSingle = this;
        pushToSingle.appInfo = this.appInfo;
    }

    @Autowired
    private GetUIAppInfo appInfo;

    //[NotificationTemplate] start

    /**
     * style0 （普通）样式 + NotificationTemplate 推送
     *
     * @param cid                 推送目标
     * @param title               标题
     * @param text                内容
     * @param transmissionContent 透析内容（可为空）
     * @return
     */
    public final static String pushNotificationStyle0(String cid, String title, String text, String transmissionContent) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);


        AbstractNotifyStyle style = GetUIStyleTemplate.style0(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.notificationTemplate(style, transmissionContent, GetUIPushTemplate.TransmissionType.ONE);

            return GetUIPushToSingle.push(cid, template);
        } catch (Exception e) {
            log.error("批量单推失败!", e);
        }

        return null;
    }

    /**
     * style6-1 （图片）样式 + NotificationTemplate 推送
     *
     * @param cid                 推送目标
     * @param title               标题
     * @param text                内容
     * @param picUrl              通知栏图片URL地址
     * @param transmissionContent 透析内容（可为空）
     * @return
     */
    public final static String pushNotificationStyle6Pic(String cid, String title, String text, String picUrl, String transmissionContent) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);

        styleParam.setBigStyle6PicUrl(picUrl);


        AbstractNotifyStyle style = GetUIStyleTemplate.style6Pic(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.notificationTemplate(style, transmissionContent, GetUIPushTemplate.TransmissionType.ONE);

            return GetUIPushToSingle.push(cid, template);
        } catch (Exception e) {
            log.error("批量单推失败!", e);
        }

        return null;
    }


    /**
     * style6-2 （长文本）样式 + NotificationTemplate 推送
     *
     * @param cid                 推送目标
     * @param title               标题
     * @param text                内容
     * @param bigText             长文本
     * @param transmissionContent 透析内容（可为空）
     * @return
     */
    public final static String pushNotificationStyle6Text(String cid, String title, String text, String bigText, String transmissionContent) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);

        styleParam.setBigStyle6Text(bigText);


        AbstractNotifyStyle style = GetUIStyleTemplate.style6Text(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.notificationTemplate(style, transmissionContent, GetUIPushTemplate.TransmissionType.ONE);

            return GetUIPushToSingle.push(cid, template);
        } catch (Exception e) {
            log.error("批量单推失败!", e);
        }

        return null;
    }
    //[NotificationTemplate] end


    //[TransmissionTemplate] start

    /**
     * TransmissionContent 推送
     *
     * @param transmissionContent
     * @return
     */
    public final static String pushTransmission(String cid, String transmissionContent) {
        try {
            AbstractTemplate template = GetUIPushTemplate.transmissionTemplate(transmissionContent, GetUIPushTemplate.TransmissionType.ONE);

            GetUIPushToSingle.push(cid, template);
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
     * @param cid     推送目标
     * @param title   标题
     * @param text    内容
     * @param linkUrl 跳转链接地址
     * @return
     */
    public final static String pushLinkStyle0(String cid, String title, String text, String linkUrl) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);

        AbstractNotifyStyle style = GetUIStyleTemplate.style0(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.linkTemplate(style, linkUrl);

            GetUIPushToSingle.push(cid, template);
        } catch (Exception e) {
            log.error("批量单推失败!", e);
        }

        return null;
    }

    /**
     * style6-1 （图片）样式 + LinkTemplate 推送
     *
     * @param cid     推送目标
     * @param title   标题
     * @param text    内容
     * @param picUrl  通知栏图片URL地址
     * @param linkUrl 跳转链接地址
     * @return
     */
    public final static String pushLinkStyle6Pic(String cid, String title, String text, String picUrl, String linkUrl) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);

        styleParam.setBigStyle6PicUrl(picUrl);


        AbstractNotifyStyle style = GetUIStyleTemplate.style6Pic(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.linkTemplate(style, linkUrl);

            GetUIPushToSingle.push(cid, template);
        } catch (Exception e) {
            log.error("批量单推失败!", e);
        }

        return null;
    }


    /**
     * style6-2 （长文本）样式 + LinkTemplate 推送
     *
     * @param cid     推送目标
     * @param title   标题
     * @param text    内容
     * @param bigText 长文本
     * @param linkUrl 跳转链接地址
     * @return
     */
    public final static String pushLinkStyle6Text(String cid, String title, String text, String bigText, String linkUrl) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);

        styleParam.setBigStyle6Text(bigText);


        AbstractNotifyStyle style = GetUIStyleTemplate.style6Text(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.linkTemplate(style, linkUrl);

            GetUIPushToSingle.push(cid, template);
        } catch (Exception e) {
            log.error("批量单推失败!", e);
        }

        return null;
    }
    //[LinkTemplate] end


    ///////////以上封装方法，便于调用（若不满足情况，可用下面方法自己组装，调用可参考测试类ToSinglePushTest.java）//////////////

    /**
     * 根据cid 向单个用户发送消息
     *
     * @param cid
     * @param template
     */
    public final static String push(String cid, AbstractTemplate template) {
        log.info("向用户【{}】单推一条消息", cid);
        IGtPush push = new IGtPush(pushToSingle.appInfo.getAppKey(),
                pushToSingle.appInfo.getMasterSecret());

        SingleMessage message = getSingleMessage(template);
        Target target = new Target();
        target.setAppId(pushToSingle.appInfo.getAppId());
        target.setClientId(cid);

        IPushResult ret;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }

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
     * 执行批量单推
     * <pre>
     *     功能说明：合并多个单推任务，统一提交，可提升推送效率。
     *
     *     调用步骤：
     *      1）：调用push.getBatch() 获取操作对象
     *      2）：调用batch.add() 添加单推任务
     *      3）：调用batch.submit() 提交任务
     * </pre>
     *
     * @param batchParamMap
     */
    public final static String batchPush(Map<String, AbstractTemplate> batchParamMap) {
        log.info("批量向用户【{}】单推一条消息", batchParamMap.keySet());

        IGtPush push = new IGtPush(pushToSingle.appInfo.getAppKey(),
                pushToSingle.appInfo.getMasterSecret());

        // 获取操作对象
        IBatch batch = push.getBatch();

        // 循环添加 添加单推任务
        batchParamMap.forEach((cid, template) -> {
            SingleMessage message = getSingleMessage(template);

            Target target = new Target();
            target.setAppId(pushToSingle.appInfo.getAppId());
            target.setClientId(cid);

            try {
                batch.add(message, target);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 提交任务
        try {
            IPushResult ret = batch.submit();

            String result = null;
            if (ret != null) {
                result = ret.getResponse().toString();
                log.info("推送结果：{}", result);
            } else {
                log.info("服务器响应异常");
            }

            return result;
        } catch (IOException e) {
            log.error("批量单推失败!", e);
        }
        return null;
    }

    /**
     * 设置 单推消息类型 SingleMessage
     * <pre>
     *     参数信息：
     *      isOffline 	        boolean 	否 	false 	是否保持离线消息
     *      offlineExpireTime 	long 	    否 	1小时 	过多久该消息离线失效（单位毫秒） 支持1-72小时*3600000毫秒
     *      pushNetWorkType 	long 	    否 	0 	推送网络要求
     *                                              0:联网方式不限;
     *                                              1:仅wifi;
     *                                              2:仅移动网络
     *       data 	        ITemplate 	    是 	无 	设置推送效果，点击查看详细说明
     * </pre>
     *
     * @param template
     * @return
     */
    private final static SingleMessage getSingleMessage(AbstractTemplate template) {
        SingleMessage message = new SingleMessage();
        message.setData(template);
        message.setOffline(true);
        message.setOfflineExpireTime(2 * 3600 * 1000);
        message.setPushNetWorkType(0);
        return message;
    }

}
