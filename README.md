message-push 
================================
#### 消息推送 工具封装，目前支持第三方推送平台有：

- [个推](#个推)

## 个推

+ 1）**个推介绍**
    + 个推官网：https://www.getui.com/
    
            个推的功能：消息推送、用户画像、应用统计、一键认证
    + 消息推送 功能（参考：http://docs.getui.com/getui/server/java/template/ ）：
        + 提供了如下3种通知样式：
            1) Style0：系统样式
            2) Style6-1：大图+文本样式
            3) Style6-2：长文本样式
        + 提供了如下5种通知模板：
            1) TransmissionTemplate：透传模板】自定义消息（透传消息是指消息传递到客户端只有消息内容，展现形式由客户端自行定义。客户端可自定义通知的展现形式，也可自定义通知到达之后的动作，或者不做任何展现。）
            2) NotificationTemplate：【通知模板】打开应用首页（在通知栏显示一条通知，用户点击后打开应用的首页）
            3) StartActivityTemplate：【通知模板】打开应用内页面（在通知栏显示一条通知，用户点击后打开应用内指定的页面。）
            4) LinkTemplate：【通知模板】打开浏览器网页（在通知栏显示一条通知，用户点击可打开浏览器的指定网页。）
            5) RevokeTemplate：【通知模板】通知消息撤回（消息撤回是指对已经发送通知消息进行撤回。客户端接收到消息撤回的指令，会将通知栏中展示的消息进行删除，不再展示。）
        + 提供了如下4种消息推送形式：
            1) [toSingle](#toSingle) ：简称“单推”，指向单个用户推送消息
            2) [toList](#toList)：简称“批量推”，指向制定的一批用户推送消息
            3) [toApp](#toApp)：简称“群推”，指向APP符合筛选条件的所有用户推送消息，支持定速推送、定时推送，支持条件的交并补功能
            4) toGroup：此方式为直播间定制方案，适用于直播间消息推送（暂未封装）
+ 2）**工具使用**
    + 代码路径：com.andy.messagepush.getui 包下
    + ##### toSingle
        + 封装工具类：GetUIPushToSingle.java
        + 根据 cid 单推一条消息
            + 自主选择模板（封装类不满足需求可以自主选择模板） 
                1) push(String cid, AbstractTemplate template)
            + 封装好的模板 
                1) pushNotificationStyle0(String cid, String title, String text, String transmissionContent)
                2) pushNotificationStyle6Pic(String cid, String title, String text, String picUrl, String transmissionContent)
                3) pushNotificationStyle6Text(String cid, String title, String text, String bigText, String transmissionContent)
                4) pushTransmission(String cid, String transmissionContent)
                5) pushLinkStyle0(String cid, String title, String text, String linkUrl)
                6) pushLinkStyle6Pic(String cid, String title, String text, String picUrl, String linkUrl)
                7) pushLinkStyle6Text(String cid, String title, String text, String bigText, String linkUrl)
        + 根据 cid 集合（合并）批量单推一条消息
            + 自主选择模板 
                1) GetUIPushToSingle.batchPush(Map<String, AbstractTemplate> batchParamMap)
        + 使用可参考测试类：ToSinglePushTest.java         
    + ##### toList
        + 封装工具类：GetUIPushToList.java
        + 向指定的 cid 集合 推送一条消息
            + 自主选择模板（封装类不满足需求可以自主选择模板） 
                1) push(List<String> cidList, AbstractTemplate template)
            + 封装好的模板
                1) pushNotificationStyle0(List<String> cidList, String title, String text, String transmissionContent)
                2) pushNotificationStyle6Pic(List<String> cidList, String title, String text, String picUrl, String transmissionContent)
                3) pushNotificationStyle6Text(List<String> cidList, String title, String text, String bigText, String transmissionContent)
                4) pushTransmission(List<String> cidList, String transmissionContent)
                5) pushLinkStyle0(List<String> cidList, String title, String text, String linkUrl)
                6) pushLinkStyle6Pic(List<String> cidList, String title, String text, String picUrl, String linkUrl)
                7) pushLinkStyle6Text(List<String> cidList, String title, String text, String bigText, String linkUrl)
       + 使用可参考测试类：ToListPushTest.java 
    + ##### toApp
        + 封装工具类：GetUIPushToApp.java
        + 向指定应用的所有（或符合筛选条件的）用户群发推送消息。有定时、定速功能
            + 自主选择模板（封装类不满足需求可以自主选择模板）
                1) push(AbstractTemplate template, String pushTime)
            + 封装好的模板
                1) pushNotificationStyle0(String title, String text, String transmissionContent, String pushTime)
                2) pushNotificationStyle6Pic(String title, String text, String picUrl, String transmissionContent, String pushTime)
                3) pushNotificationStyle6Text(String title, String text, String bigText, String transmissionContent, String pushTime)
                4) pushTransmission(String transmissionContent, String pushTime)
                5) pushLinkStyle0(String title, String text, String linkUrl, String pushTime)
                6) pushLinkStyle6Pic(String title, String text, String picUrl, String linkUrl, String pushTime)
                7) pushLinkStyle6Text(String title, String text, String bigText, String linkUrl, String pushTime)
        + 使用可参考测试类：ToAPPPushTest.java
+ 3）**注意事项**
    + 需先在“个推”平台上注册账号，创建应用，将应用信息配置在 application.yml 中（**doc** 文件夹下的 getui_sdk.apk，匹配本示例中的配置信息，可以直接拿来测试。）
    + 在“个推”平台上查看应用信息 <img src="doc/getui.png" width="95%" alt="个推应用配置信息"/>

## 欢迎交流

+ QQ
    
    <img src="doc/594580820.jpg" width="15%" alt="Andy.wang的QQ"/>
+ 微信

    <img src="doc/wx.jpg" width="15%" alt="Andy.wang的微信"/>



