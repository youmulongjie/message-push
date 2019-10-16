package com.andy.messagepush.getui.push;

import com.andy.messagepush.getui.GetUIAppInfo;
import com.andy.messagepush.getui.param.GetUIStyleParam;
import com.andy.messagepush.getui.template.GetUIPushTemplate;
import com.andy.messagepush.getui.template.GetUIStyleTemplate;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.AbstractTemplate;
import com.gexin.rp.sdk.template.style.AbstractNotifyStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 个推平台 “批量推”，指向制定的一批用户推送消息。参考：http://docs.getui.com/getui/server/java/push/
 * Author: Andy.wang
 * Date: 2019/10/16 15:01
 */
@Component
@Slf4j
public class GETUIPushToList {
    private static GETUIPushToList pushToList;

    @PostConstruct
    public void init() {
        pushToList = this;
        pushToList.appInfo = this.appInfo;
    }

    @Autowired
    private GetUIAppInfo appInfo;

    /**
     * style0 （普通）样式 + NotificationTemplate 推送
     *
     * @param cidList             推送目标List
     * @param title               标题
     * @param text                内容
     * @param transmissionContent 透析内容（可为空）
     * @return
     */
    public final static String pushNotificationStyle0(List<String> cidList, String title, String text, String transmissionContent) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);


        AbstractNotifyStyle style = GetUIStyleTemplate.style0(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.notificationTemplate(style, transmissionContent, GetUIPushTemplate.TransmissionType.ONE);

            return push(cidList, template);
        } catch (Exception e) {
            log.error("‘批量推’失败!", e);
        }

        return null;
    }

    /**
     * style6-1 （图片）样式 + NotificationTemplate 推送
     *
     * @param cidList             推送目标List
     * @param title               标题
     * @param text                内容
     * @param picUrl              通知栏图片URL地址
     * @param transmissionContent 透析内容（可为空）
     * @return
     */
    public final static String pushNotificationStyle6Pic(List<String> cidList, String title, String text, String picUrl, String transmissionContent) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);

        styleParam.setBigStyle6PicUrl(picUrl);


        AbstractNotifyStyle style = GetUIStyleTemplate.style6Pic(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.notificationTemplate(style, transmissionContent, GetUIPushTemplate.TransmissionType.ONE);

            return push(cidList, template);
        } catch (Exception e) {
            log.error("‘批量推’失败!", e);
        }

        return null;
    }


    /**
     * style6-2 （长文本）样式 + NotificationTemplate 推送
     *
     * @param cidList             推送目标List
     * @param title               标题
     * @param text                内容
     * @param bigText             长文本
     * @param transmissionContent 透析内容（可为空）
     * @return
     */
    public final static String pushNotificationStyle6Text(List<String> cidList, String title, String text, String bigText, String transmissionContent) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);

        styleParam.setBigStyle6Text(bigText);

        AbstractNotifyStyle style = GetUIStyleTemplate.style6Text(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.notificationTemplate(style, transmissionContent, GetUIPushTemplate.TransmissionType.ONE);

            return push(cidList, template);
        } catch (Exception e) {
            log.error("‘批量推’失败!", e);
        }

        return null;
    }
    //[NotificationTemplate] end


    //[TransmissionTemplate] start

    /**
     * TransmissionContent 推送
     *
     * @param cidList             推送目标List
     * @param transmissionContent
     * @return
     */
    public final static String pushTransmission(List<String> cidList, String transmissionContent) {
        try {
            AbstractTemplate template = GetUIPushTemplate.transmissionTemplate(transmissionContent, GetUIPushTemplate.TransmissionType.ONE);

            return push(cidList, template);
        } catch (Exception e) {
            log.error("‘批量推’失败!", e);
        }

        return null;
    }
    //[TransmissionTemplate] end

    //[LinkTemplate] start

    /**
     * style0 （普通）样式 + LinkTemplate 推送
     *
     * @param cidList             推送目标List
     * @param title   标题
     * @param text    内容
     * @param linkUrl 跳转链接地址
     * @return
     */
    public final static String pushLinkStyle0(List<String> cidList, String title, String text, String linkUrl) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);

        AbstractNotifyStyle style = GetUIStyleTemplate.style0(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.linkTemplate(style, linkUrl);

            return push(cidList, template);
        } catch (Exception e) {
            log.error("‘批量推’失败!", e);
        }

        return null;
    }

    /**
     * style6-1 （图片）样式 + LinkTemplate 推送
     *
     * @param cidList             推送目标List
     * @param title   标题
     * @param text    内容
     * @param picUrl  通知栏图片URL地址
     * @param linkUrl 跳转链接地址
     * @return
     */
    public final static String pushLinkStyle6Pic(List<String> cidList, String title, String text, String picUrl, String linkUrl) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);

        styleParam.setBigStyle6PicUrl(picUrl);


        AbstractNotifyStyle style = GetUIStyleTemplate.style6Pic(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.linkTemplate(style, linkUrl);

            return push(cidList, template);
        } catch (Exception e) {
            log.error("‘批量推’失败!", e);
        }

        return null;
    }


    /**
     * style6-2 （长文本）样式 + LinkTemplate 推送
     *
     * @param cidList             推送目标List
     * @param title   标题
     * @param text    内容
     * @param bigText 长文本
     * @param linkUrl 跳转链接地址
     * @return
     */
    public final static String pushLinkStyle6Text(List<String> cidList, String title, String text, String bigText, String linkUrl) {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle(title);
        styleParam.setText(text);

        styleParam.setBigStyle6Text(bigText);


        AbstractNotifyStyle style = GetUIStyleTemplate.style6Text(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.linkTemplate(style, linkUrl);

            return push(cidList, template);
        } catch (Exception e) {
            log.error("‘批量推’失败!", e);
        }

        return null;
    }
    //[LinkTemplate] end


    ///////////以上封装方法，便于调用（若不满足情况，可用下面方法自己组装，调用可参考测试类ToListPushTest.java）//////////////

    /**
     * “批量推” 消息
     *
     * @param cidList  目标集合
     * @param template 推送模板
     * @return
     */
    public final static String push(List<String> cidList, AbstractTemplate template) {
        log.info("向用户【{}】‘批量推’一条消息.", cidList);
        // 配置返回每个用户返回用户状态，可选
        System.setProperty("gexin_pushList_needDetails", "true");

        IGtPush push = new IGtPush(pushToList.appInfo.getAppKey(),
                pushToList.appInfo.getMasterSecret());

        // STEP1：创建消息公共体，获取taskId
        String taskId = getContentId(push, template);

        List<Target> targetList = new ArrayList<>(cidList.size());

        // 循环添加 添加目标对象
        cidList.forEach(cid -> {

            Target target = new Target();
            target.setAppId(pushToList.appInfo.getAppId());
            target.setClientId(cid);

            targetList.add(target);

        });

        // STEP2：调用【toList】执行推送接口执行下发
        IPushResult ret = push.pushMessageToList(taskId, targetList);

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
     * 获取 taskId
     *
     * @param push
     * @param template
     * @return
     */
    private static String getContentId(IGtPush push, AbstractTemplate template) {
        ListMessage listMessage = listMessage(template);

        String contentId = push.getContentId(listMessage);

        log.info("获取 taskId = {}.", contentId);
        return contentId;
    }

    /**
     * 设置 批量推 消息类型 ListMessage
     * <pre>
     *     参数信息：
     *      isOffline 	        boolean 	否 	false 	是否保持离线消息
     *      offlineExpireTime 	long 	    否 	1小时 	过多久该消息离线失效（单位毫秒） 支持1-72小时*3600000毫秒
     *      pushNetWorkType 	long 	    否 	0 	    推送网络要求
     *                                                      0:联网方式不限;
     *                                                      1:仅wifi;
     *                                                      2:仅移动网络
     *      data 	        ITemplate 	    是 	无 	    设置推送效果，点击查看详细说明
     * </pre>
     *
     * @param template
     * @return
     */
    private final static ListMessage listMessage(AbstractTemplate template) {
        log.info("创建消息公共体 ListMessage.");

        ListMessage message = new ListMessage();
        message.setData(template);
        message.setOffline(true);
        message.setOfflineExpireTime(2 * 3600 * 1000);
        message.setPushNetWorkType(0);
        return message;
    }
}
