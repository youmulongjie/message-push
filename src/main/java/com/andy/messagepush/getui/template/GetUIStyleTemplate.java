package com.andy.messagepush.getui.template;

import com.andy.messagepush.getui.param.GetUIStyleParam;
import com.gexin.rp.sdk.template.style.AbstractNotifyStyle;
import com.gexin.rp.sdk.template.style.Style0;
import com.gexin.rp.sdk.template.style.Style6;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Description: 个推平台 推送样式构建
 * <pre>
 *      通知样式设置，共3种样式，参考：http://docs.getui.com/getui/server/java/template/
 *      1）Style0：系统样式
 *      2）Style6-1：大图+文本样式
 *      3）Style6-2：长文本样式
 * </pre>
 * Author: Andy.wang
 * Date: 2019/10/15 09:55
 */
public class GetUIStyleTemplate {

    /**
     * 构建 Style0：系统样式
     *
     * @param param 样式类实例
     * @return
     */
    public final static AbstractNotifyStyle style0(GetUIStyleParam param) {
        Style0 style = new Style0();

        Assert.notNull(param.getTitle(), "[通知标题]为空");
        Assert.notNull(param.getText(), "[通知内容]为空");

        // 设置通知栏标题与内容
        style.setTitle(param.getTitle());
        style.setText(param.getText());

        //配置通知栏图标，需要在客户端开发时嵌入，默认为push.png
        String logo = param.getLogo();
        logo = StringUtils.isEmpty(logo) ? "push.png" : logo;
        style.setLogo(logo);

        String logoUrl = param.getLogoUrl();
        if (!StringUtils.isEmpty(logoUrl)) {
            style.setLogoUrl(logoUrl);
        }

        // 设置通知是否响铃，震动，或者可清除
        style.setRing(param.isRing());
        style.setVibrate(param.isVibrate());
        style.setClearable(param.isClearable());

        // 设置 渠道信息
        String channel = param.getChannel();
        if (!StringUtils.isEmpty(channel)) {
            style.setChannel(channel);
        }
        String channelName = param.getChannelName();
        if (!StringUtils.isEmpty(channelName)) {
            style.setChannelName(channelName);
        }
        style.setChannelLevel(param.getChannelLevel());

        return style;
    }

    /**
     * Style6-1：大图+文本样式
     *
     * @param param 样式类实例
     * @return
     */
    public final static AbstractNotifyStyle style6Pic(GetUIStyleParam param) {
        Style6 style6 = buildStyle6(param);
        style6.setBigStyle1(param.getBigStyle6PicUrl());

        return style6;
    }

    /**
     * Style6-2：长文本样式
     *
     * @param param 样式类实例
     * @return
     */
    public final static AbstractNotifyStyle style6Text(GetUIStyleParam param) {
        Style6 style6 = buildStyle6(param);
        style6.setBigStyle2(param.getBigStyle6Text());

        return style6;
    }

    /**
     * 构建 Style6 样式
     * @param param
     * @return
     */
    private final static Style6 buildStyle6(GetUIStyleParam param){
        Style6 style = new Style6();

        Assert.notNull(param.getTitle(), "[通知标题]为空");
        Assert.notNull(param.getText(), "[通知内容]为空");

        // 设置通知栏标题与内容
        style.setTitle(param.getTitle());
        style.setText(param.getText());

        //配置通知栏图标，需要在客户端开发时嵌入，默认为push.png
        String logo = param.getLogo();
        logo = StringUtils.isEmpty(logo) ? "push.png" : logo;
        style.setLogo(logo);

        String logoUrl = param.getLogoUrl();
        if (!StringUtils.isEmpty(logoUrl)) {
            style.setLogoUrl(logoUrl);
        }

        // 设置通知是否响铃，震动，或者可清除
        style.setRing(param.isRing());
        style.setVibrate(param.isVibrate());
        style.setClearable(param.isClearable());

        // 设置 渠道信息
        String channel = param.getChannel();
        if (!StringUtils.isEmpty(channel)) {
            style.setChannel(channel);
        }
        String channelName = param.getChannelName();
        if (!StringUtils.isEmpty(channelName)) {
            style.setChannelName(channelName);
        }
        style.setChannelLevel(param.getChannelLevel());

        return style;
    }
}
