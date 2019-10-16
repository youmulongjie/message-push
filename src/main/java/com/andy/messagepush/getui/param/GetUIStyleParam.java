package com.andy.messagepush.getui.param;

import lombok.Data;

/**
 * Description: 个推平台 样式参数类
 * Author: Andy.wang
 * Date: 2019/10/15 09:58
 */
@Data
public class GetUIStyleParam {
    /**
     * 推送标题
     */
    private String title;

    /**
     * 推送内容
     */
    private String text;

    /**
     * 通知栏图标
     */
    private String logo;

    /**
     * 通知栏网络图标
     */
    private String logoUrl;

    /**
     * 通知展示大图样式，参数是大图的URL地址（构建 style6 大图使用）
     */
    private String bigStyle6PicUrl;

    /**
     * 通知展示文本+长文本样式，参数是长文本（构建 style6 长文本使用）
     */
    private String bigStyle6Text;

    /**
     * 通知渠道id，唯一标识（非必填）
     */
    private String channel;

    /**
     * 通知渠道名称（非必填）
     */
    private String channelName;

    /**
     * 通知渠道重要性
     */
    private int channelLevel = ChannelLevel.LEVEL4.level;

    /**
     * 收到通知是否响铃（true响铃，false不响铃）
     */
    private boolean ring = true;

    /**
     * 收到通知是否振动（true振动，false不振动）
     */
    private boolean vibrate = true;

    /**
     * 通知是否可清除（true可清除，false不可清除)
     */
    private boolean clearable = true;

    /**
     * 通知渠道重要性 枚举
     */
    public enum ChannelLevel {
        // 0：无声音，无震动，不显示。(不推荐)
        LEVEL0(0),
        // 1：无声音，无震动，锁屏不显示，通知栏中被折叠显示，导航栏无logo。
        LEVEL1(1),
        // 2：无声音，无震动，锁屏和通知栏中都显示，通知不唤醒屏幕。
        LEVEL2(2),
        // 3：有声音，有震动，锁屏和通知栏中都显示，通知唤醒屏幕。（推荐
        LEVEL3(3),
        // 4：有声音，有震动，亮屏下通知悬浮展示，锁屏通知以默认形式展示且唤醒屏幕。（推荐）
        LEVEL4(4);

        private int level;

        ChannelLevel(int level) {
            this.level = level;
        }
    }
}
