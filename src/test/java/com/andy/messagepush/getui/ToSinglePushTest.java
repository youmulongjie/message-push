package com.andy.messagepush.getui;

import com.andy.messagepush.getui.param.GetUIStyleParam;
import com.andy.messagepush.getui.push.GetUIPushToSingle;
import com.andy.messagepush.getui.template.GetUIPushTemplate;
import com.andy.messagepush.getui.template.GetUIStyleTemplate;
import com.gexin.rp.sdk.template.AbstractTemplate;
import com.gexin.rp.sdk.template.style.AbstractNotifyStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.TreeMap;

/**
 * Description: 单推（toSingle） 测试
 * Author: Andy.wang
 * Date: 2019/10/16 11:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ToSinglePushTest {
    private String cid = "a06ee9646c5dd70fb96ff989e5bc1839";


    // 测试 单推（style0 样式 + NotificationTemplate）
    @Test
    public void toSingle() {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle("缴费通知");
        styleParam.setText("2020年度农村医保缴费时间已开始");


        AbstractNotifyStyle style = GetUIStyleTemplate.style0(styleParam);

        String transmissionContent = "这是Andy.wang 用个推做的第一个demo（style0 样式 + NotificationTemplate）";

        try {
            AbstractTemplate template = GetUIPushTemplate.notificationTemplate(style, transmissionContent, GetUIPushTemplate.TransmissionType.ONE);

            GetUIPushToSingle.push(cid, template);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // 测试 单推（TransmissionTemplate）
    @Test
    public void toSingle2() {
        String transmissionContent = "这是Andy.wang 用个推做的第一个demo（style0 样式 + TransmissionTemplate）";

        try {
            AbstractTemplate template = GetUIPushTemplate.transmissionTemplate(transmissionContent, GetUIPushTemplate.TransmissionType.ONE);

            GetUIPushToSingle.push(cid, template);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 测试 单推（style0 样式 + LinkTemplate）
    @Test
    public void toSingle3() {
        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle("github通知");
        styleParam.setText("路过的朋友请留下star，谢谢支持");

        AbstractNotifyStyle style = GetUIStyleTemplate.style0(styleParam);

        try {
            AbstractTemplate template = GetUIPushTemplate.linkTemplate(style, "https://github.com/youmulongjie");

            GetUIPushToSingle.push(cid, template);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 测试 批量 单推（style6-2 样式 + notificationTemplate）
    @Test
    public void toSingleBatch() {
        Map<String, AbstractTemplate> batchParamMap = buildBatchMap();

        GetUIPushToSingle.batchPush(batchParamMap);
    }

    // 组件 批量单推 参数
    private Map buildBatchMap() {
        Map<String, AbstractTemplate> batchParamMap = new TreeMap<>();

        GetUIStyleParam styleParam = new GetUIStyleParam();
        styleParam.setTitle("积分落户");
        styleParam.setText("2019北京积分落户通知");

        // 两种测试，二选一
        // 1) 测试 长文本
        styleParam.setBigStyle6Text("2019北京积分落户分数线什么时候才能出来?10月15日，" +
                "北京市人力资源社会保障局发布消息，本市2019年积分落户申报审核工作已全部结束，" +
                "自10月15日起进入公示及落户办理阶段。" +
                "届时，今年申报积分落户的106403名申请人可登录积分落户在线申报系统查看本人年度积分和排名");

        // 2) 测试 图片
        // styleParam.setBigStyle6PicUrl("https://avatar.csdnimg.cn/5/1/7/1_andy_longjie.jpg");


        AbstractNotifyStyle style = GetUIStyleTemplate.style6Pic(styleParam);

        String transmissionContent = "这是Andy.wang 用个推做的第二个demo";

        try {
            AbstractTemplate template = GetUIPushTemplate.notificationTemplate(style, transmissionContent, GetUIPushTemplate.TransmissionType.ONE);

            batchParamMap.put(cid, template);

            String cid2 = "9c852a42e228ecb221c2710b6968f0d8";
            batchParamMap.put(cid2, template);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return batchParamMap;
    }

    /////////////////////////////以下是封装的方法，便于调用//////////////////////////////////

    // 测试 单推（style0 样式 + NotificationTemplate）,同 方法 toSingle()
    @Test
    public void pushNotificationStyle0() {
        String title = "饭点到了";
        String text = "韩梅梅你妈喊你回家吃中午饭了";

        GetUIPushToSingle.pushNotificationStyle0(cid, title, text, null);
    }

    // 测试 单推（style6-1 样式 + NotificationTemplate）
    @Test
    public void pushNotificationStyle6Pic() {
        String title = "开源中国";
        String text = "我的开源中国账号头像是葫芦娃";
        String picUrl = "https://oscimg.oschina.net/oscnet/up-c433d2b39ed5624fd813783da456026b.jpg";

        GetUIPushToSingle.pushNotificationStyle6Pic(cid, title, text, picUrl, null);
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

        GetUIPushToSingle.pushNotificationStyle6Text(cid, title, text, bigText, null);
    }

    // 测试 单推（TransmissionTemplate），同方法 toSingle2()
    @Test
    public void pushTransmission() {
        String transmissionContent = "2019北京积分落户分数线什么时候才能出来?10月15日，" +
                "北京市人力资源社会保障局发布消息，本市2019年积分落户申报审核工作已全部结束，" +
                "自10月15日起进入公示及落户办理阶段。" +
                "届时，今年申报积分落户的106403名申请人可登录积分落户在线申报系统查看本人年度积分和排名";

        GetUIPushToSingle.pushTransmission(cid, transmissionContent);
    }

    // 测试 单推（style0 样式 + NotificationTemplate）,同 方法 toSingle3()
    @Test
    public void pushLinkStyle0() {
        String title = "github采访";
        String text = "欢迎来到我的github主页，喜欢的话留下star，谢谢";

        String linkUrl = "https://github.com/youmulongjie";

        GetUIPushToSingle.pushLinkStyle0(cid, title, text, linkUrl);
    }

    // 测试 单推（style6-1 样式 + NotificationTemplate）
    @Test
    public void pushLinkStyle6Pic() {
        String title = "开源中国";
        String text = "hello，我是葫芦娃，欢迎来到我的开源中国账号";
        String picUrl = "https://oscimg.oschina.net/oscnet/up-c433d2b39ed5624fd813783da456026b.jpg";

        String linkUrl = "https://my.oschina.net/andy1989";

        GetUIPushToSingle.pushLinkStyle6Pic(cid, title, text, picUrl, linkUrl);
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

        GetUIPushToSingle.pushLinkStyle6Text(cid, title, text, bigText, linkUrl);
    }
}
