package com.andy.messagepush.getui.template;

import com.andy.messagepush.getui.GetUIAppInfo;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.StartActivityTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.AbstractNotifyStyle;
import jodd.datetime.JDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

/**
 * Description: 个推平台 推送模板构建
 * <pre>
 *      推送模板设置，共5种模板，参考：http://docs.getui.com/getui/server/java/template/
 *
 *      1）TransmissionTemplate：透传模板】自定义消息（透传消息是指消息传递到客户端只有消息内容，展现形式由客户端自行定义。客户端可自定义通知的展现形式，也可自定义通知到达之后的动作，或者不做任何展现。）
 *      2）NotificationTemplate：【通知模板】打开应用首页（在通知栏显示一条通知，用户点击后打开应用的首页）
 *      3）StartActivityTemplate：【通知模板】打开应用内页面（在通知栏显示一条通知，用户点击后打开应用内指定的页面。）
 *      4）LinkTemplate：【通知模板】打开浏览器网页（在通知栏显示一条通知，用户点击可打开浏览器的指定网页。）
 *
 *      5）RevokeTemplate：【通知模板】通知消息撤回（消息撤回是指对已经发送通知消息进行撤回。客户端接收到消息撤回的指令，会将通知栏中展示的消息进行删除，不再展示。）
 * </pre>
 * Author: Andy.wang
 * Date: 2019/10/15 13:15
 */
@Component
public class GetUIPushTemplate {

    private static GetUIPushTemplate pushTemplate;

    @PostConstruct
    public void init() {
        pushTemplate = this;
        pushTemplate.appInfo = this.appInfo;
    }

    @Autowired
    private GetUIAppInfo appInfo;


    /**
     * 【透传模板】自定义消息
     * <pre>
     *     透传消息是指消息传递到客户端只有消息内容，展现形式由客户端自行定义。
     *     客户端可自定义通知的展现形式，也可自定义通知到达之后的动作，或者不做任何展现。
     * </pre>
     * <pre>
     *     参数信息：
     *      setAppID 	            String 	是 		设定接收的应用
     *      setAppkey 	            String 	是 		用于鉴定身份是否合法
     *      setTransmissionContent 	String 	是 		透传内容，不支持转义字符
     *      setTransmissionType 	int 	是 		搭配transmissionContent使用，可选值为1、2；
     *                                              1：立即启动APP（不推荐使用，影响客户体验）
     *                                              2：客户端收到消息后需要自行处理
     *      setAPNInfo 	            Payload 	否 		【iOS】用于设置标题、内容、语音、多媒体、VoIP（基于IP的语音传输）等。离线走APNs时起效果，具体样式见iOS通知样式
     *      setSmsInfo 	            SmsInfo 	否 		设置短信相关参数，详见短信补量
     * </pre>
     *
     * @param transmissionContent 透传内容，不支持转义字符
     * @param transmissionType    透传类型
     * @return
     */
    public final static TransmissionTemplate transmissionTemplate(String transmissionContent, TransmissionType transmissionType) {
        Assert.notNull(transmissionContent, "透传内容 不能为空");
        TransmissionTemplate template = new TransmissionTemplate();

        template.setAppId(pushTemplate.appInfo.getAppId());
        template.setAppkey(pushTemplate.appInfo.getAppKey());

        template.setTransmissionType(transmissionType.type);
        template.setTransmissionContent(transmissionContent);

        /*template.setAPNInfo(getAPNPayload());
        template.setAPNInfo(getVoIPPayload());*/

        // template.setSmsInfo(PushSmsInfo.getSmsInfo());
        return template;
    }


    /**
     * 【通知模板】打开应用首页
     * <pre>
     *     在通知栏显示一条通知，用户点击后打开应用的首页。
     *     参数信息：
     *      setAppId 	            String 	    是 		设定目标应用
     *      setAppkey 	            String 	    是 		用于鉴定身份是否合法
     *      setDuration 	String, String 	    否 		【Android】格式yyyy-MM-dd HH:mm:ss, 收到消息后，在此时间区间展示，如果此区间APP不在前台，就会错过展示，例如2019年8月14日8点-2019年8月14日9点，展示早间新闻
     *      transmissionContent 	String 	    否 		透传内容，不在通知栏中展示，开发者自行处理，不支持转义字符
     *      setTransmissionType 	int 	    否 		搭配transmissionContent使用，可选值为1、2；
     *                                                      1：立即启动APP
     *                                                      2：客户端收到消息后需要自行处理
     *      setStyle 	AbstractNotifyStyle 	否 	 	【Android】用于设置标题、内容、提示音、震动、背景图等。具体样式见Android通知样式
     *      setNotifyid 	        Integer 	否 		在消息推送的时候设置notifyid。如果需要覆盖此条消息，则下次使用相同的notifyid发一条新的消息。客户端sdk会根据notifyid进行覆盖。详见消息覆盖
     *      setSmsInfo 	            SmsInfo 	否 		设置短信相关参数，详见短信补量
     * </pre>
     *
     * @param style
     * @param transmissionContent
     * @param transmissionType
     * @return
     * @throws Exception
     */
    public final static NotificationTemplate notificationTemplate(AbstractNotifyStyle style, String transmissionContent, TransmissionType transmissionType) throws Exception {
        NotificationTemplate template = new NotificationTemplate();

        template.setAppId(pushTemplate.appInfo.getAppId());
        template.setAppkey(pushTemplate.appInfo.getAppKey());

        template.setStyle(style);
        template.setTransmissionType(transmissionType.type);
        template.setTransmissionContent(transmissionContent);

        JDateTime jDateTime = new JDateTime();

        template.setDuration(jDateTime.toString("YYYY-MM-DD hh:mm:ss"),
                jDateTime.addHour(1).toString("YYYY-MM-DD hh:mm:ss"));

        // template.setNotifyid(123);
        // template.setSmsInfo(PushSmsInfo.getSmsInfo()); //短信补量推送
        return template;
    }


    /**
     * 【通知模板】打开应用内页面
     * <pre>
     *     描述：在通知栏显示一条通知，用户点击后打开应用内指定的页面。
     *     应用场景：推广促销活动，用户点击通知栏信息，直接打开到应用内指定的促销活动页面。
     *
     *     参数信息：
     *      setAppId 	        String 	    是 		设定目标应用
     *      setAppkey 	        String 	    是 		用于鉴定身份是否合法
     *      setDuration 	String, String 	否 		【Android】格式yyyy-MM-dd HH:mm:ss, 收到消息后，在此时间区间展示，如果此区间APP不在前台，就会错过展示，例如2019年8月14日8点-2019年8月14日9点，展示早间新闻
     *      setIntent 	        String 	    是 		【Android】长度小于1000字节，通知带intent传递参数（以intent:开头，;end结尾）
     *                                                  示例：intent:#Intent;component=你的包名/你要打开的 activity 全路径;S.parm1=value1;S.parm2=value2;end
     *      setStyle 	AbstractNotifyStyle 否 	 	【Android】用于设置标题、内容、提示音、震动、背景图等。具体样式见Android通知样式
     *      setNotifyid 	    Integer 	否 		在消息推送的时候设置notifyid。如果需要覆盖此条消息，则下次使用相同的notifyid发一条新的消息。客户端sdk会根据notifyid进行覆盖。详见消息覆盖
     *      setSmsInfo 	        SmsInfo 	否 		设置短信相关参数，详见短信补量
     * </pre>
     *
     * @param style
     * @param intent
     * @return
     */
    public final static StartActivityTemplate startActivityTemplate(AbstractNotifyStyle style, String intent) {
        StartActivityTemplate template = new StartActivityTemplate();

        template.setAppId(pushTemplate.appInfo.getAppId());
        template.setAppkey(pushTemplate.appInfo.getAppKey());

        template.setStyle(style);

        // 例如："intent:#Intent;component=com.yourpackage/.NewsActivity;end";
        template.setIntent(intent);

        /*template.setNotifyid(123);
        template.setSmsInfo(PushSmsInfo.getSmsInfo()); //短信补量推送*/
        return template;
    }


    /**
     * 【通知模板】打开浏览器网页
     * <pre>
     *     描述：在通知栏显示一条通知，用户点击可打开浏览器的指定网页。
     *     应用场景：推广促销活动，用户点击通知栏信息，直接打开到指定的促销活动页面，推送直接到达指定页面，免去了中间过程的用户流失。
     *
     *     参数信息：
     *      setAppId 	    String 	        是 		设定目标应用
     *      setAppkey 	    String 	        是 		用于鉴定身份是否合法
     *      setUrl 	        String 	        是 		点击通知后打开的网页地址
     *      setDuration 	String, String 	否 		【Android】格式yyyy-MM-dd HH:mm:ss, 收到消息后，在此时间区间展示，如果此区间APP不在前台，就会错过展示，例如2019年8月14日8点-2019年8月14日9点，展示早间新闻
     *      setStyle 	AbstractNotifyStyle 否 	 	【Android】用于设置标题、内容、提示音、震动、背景图等。具体样式见Android通知样式
     *      setNotifyid 	Integer 	    否 		在消息推送的时候设置notifyid。如果需要覆盖此条消息，则下次使用相同的notifyid发一条新的消息。客户端sdk会根据notifyid进行覆盖。详见消息覆盖
     *      setSmsInfo 	    SmsInfo 	    否    	设置短信相关参数，详见短信补量
     * </pre>
     * @param style
     * @param url
     * @return
     */
    public final static LinkTemplate linkTemplate(AbstractNotifyStyle style, String url) {
        LinkTemplate template = new LinkTemplate();

        template.setAppId(pushTemplate.appInfo.getAppId());
        template.setAppkey(pushTemplate.appInfo.getAppId());

        template.setStyle(style);
        template.setUrl(url);

        /*template.setNotifyid(123);
        template.setSmsInfo(PushSmsInfo.getSmsInfo());*/
        return template;
    }


    /**
     * TransmissionTemplate 对应的属性 TransmissionType
     */
    public enum TransmissionType {
        // 立即启动APP（不推荐使用，影响客户体验）
        ONE(1),
        // 客户端收到消息后需要自行处理
        TWO(2);
        private int type;

        TransmissionType(int type) {
            this.type = type;
        }
    }

}