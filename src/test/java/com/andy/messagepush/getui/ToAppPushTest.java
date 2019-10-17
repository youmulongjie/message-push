package com.andy.messagepush.getui;

import com.andy.messagepush.getui.param.GetUIStyleParam;
import com.andy.messagepush.getui.push.GetUIPushToApp;
import com.andy.messagepush.getui.template.GetUIPushTemplate;
import com.andy.messagepush.getui.template.GetUIStyleTemplate;
import com.gexin.rp.sdk.template.AbstractTemplate;
import com.gexin.rp.sdk.template.style.AbstractNotifyStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description: 群推（toAPP）测试
 * Author: Andy.wang
 * Date: 2019/10/17 11:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ToAppPushTest {
    private final String pushTime = null;

    // 测试 单推（style0 样式 + NotificationTemplate）
    @Test
    public void push() {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle("发工资了");
        styleParam.setText("您的2019-10月份工资已到账，请注意查收");


        AbstractNotifyStyle style = GetUIStyleTemplate.style0(styleParam);

        String transmissionContent = "这是Andy.wang 用个推做的第三个demo（批量推：style0 样式 + NotificationTemplate）";

        try {
            AbstractTemplate template = GetUIPushTemplate.notificationTemplate(style, transmissionContent, GetUIPushTemplate.TransmissionType.ONE);

            GetUIPushToApp.push(template, pushTime);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /////////////////////////////以下是封装的方法，便于调用//////////////////////////////////

    // 测试 单推（style0 样式 + NotificationTemplate）,同 方法 toSingle()
    @Test
    public void pushNotificationStyle0() {
        String title = "饭点到了";
        String text = "韩梅梅你妈喊你回家吃中午饭了";

        GetUIPushToApp.pushNotificationStyle0(title, text, null, pushTime);
    }

    // 测试 单推（style6-1 样式 + NotificationTemplate）
    @Test
    public void pushNotificationStyle6Pic() {
        String title = "开源中国";
        String text = "我的开源中国账号头像是葫芦娃";
        String picUrl = "https://oscimg.oschina.net/oscnet/up-c433d2b39ed5624fd813783da456026b.jpg";

        GetUIPushToApp.pushNotificationStyle6Pic(title, text, picUrl, null, pushTime);
    }

    // 测试 单推（style6-2 样式 + NotificationTemplate）
    @Test
    public void pushNotificationStyle6Text() {
        String title = "积分落户";
        String text = "2019北京积分落户分数线出炉了";
        String bigText = "2019北京积分落户分数线什么时候才能出来?10月15日，" +
                "北京市人力资源社会保障局发布消息，本市2019年积分落户申报审核工作已全部结束，" +
                "自10月15日起进入公示及落户办理阶段。" +
                "届时，今年申报积分落户的106403名申请人可登录积分落户在线申报系统查看本人年度积分和排名";

        GetUIPushToApp.pushNotificationStyle6Text(title, text, bigText, null, pushTime);
    }

    // 测试 单推（TransmissionTemplate），同方法 toSingle2()
    @Test
    public void pushTransmission() {
        String transmissionContent = "2019北京积分落户分数线什么时候才能出来?10月15日，" +
                "北京市人力资源社会保障局发布消息，本市2019年积分落户申报审核工作已全部结束，" +
                "自10月15日起进入公示及落户办理阶段。" +
                "届时，今年申报积分落户的106403名申请人可登录积分落户在线申报系统查看本人年度积分和排名";

        GetUIPushToApp.pushTransmission(transmissionContent, pushTime);
    }

    // 测试 单推（style0 样式 + NotificationTemplate）,同 方法 toSingle3()
    @Test
    public void pushLinkStyle0() {
        String title = "github采访";
        String text = "欢迎来到我的github主页，喜欢的话留下star，谢谢";

        String linkUrl = "https://github.com/youmulongjie";

        GetUIPushToApp.pushLinkStyle0(title, text, linkUrl, pushTime);
    }

    // 测试 单推（style6-1 样式 + NotificationTemplate）
    @Test
    public void pushLinkStyle6Pic() {
        String title = "开源中国";
        String text = "hello，我是葫芦娃，欢迎来到我的开源中国账号";
        String picUrl = "https://oscimg.oschina.net/oscnet/up-c433d2b39ed5624fd813783da456026b.jpg";

        String linkUrl = "https://my.oschina.net/andy1989";

        GetUIPushToApp.pushLinkStyle6Pic(title, text, picUrl, linkUrl, pushTime);
    }

    // 测试 单推（style6-2 样式 + NotificationTemplate）
    @Test
    public void pushLinkStyle6Text() {
        String title = "积分落户";
        String text = "关于公布2019年积分落户公示名单的通告";
        String bigText = "根据《北京市积分落户管理办法（试行）》（京政办发〔2016〕39号）、《北京市积分落户操作管理细则（试行）》" +
                "（京人社调发〔2018〕64号）有关规定，经北京市人民政府批准，现将2019年积分落户人员名单公示如下（具体名单见附件），" +
                "接受社会监督";

        String linkUrl = "http://rsj.beijing.gov.cn/jflh/jf_zcwj/jf_gg/201910/t20191015_85290.html";

        GetUIPushToApp.pushLinkStyle6Text(title, text, bigText, linkUrl, pushTime);
    }
}
