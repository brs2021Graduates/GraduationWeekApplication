package cn.edu.brs._2021.service.wechat.implement;

import cn.edu.brs._2021.entity.WechatMessage;
import cn.edu.brs._2021.service.wechat.WechatMessageHandler;
import cn.edu.brs._2021.service.wechat.implement.messageStrategy.EventStrategy;
import cn.edu.brs._2021.service.wechat.implement.messageStrategy.PictureMessageStrategy;
import cn.edu.brs._2021.service.wechat.implement.messageStrategy.TextMessageStrategy;
import cn.edu.brs._2021.service.wechat.implement.messageStrategy.VideoMessageStrategy;
import cn.edu.brs._2021.service.wechat.utility.SHA1;
import cn.edu.brs._2021.service.wechat.utility.WechatMessageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service("MessageHandler")
public class WechatMessageHandlerImplement implements WechatMessageHandler {
    @Value("${AppSecret}") String appSecret;
    @Value("${AppID}") String appId;
    @Value("${token}") String token;
    @Value("${EncodingAesKey}") String encodingAesKey;

    private final EventStrategy eventStrategy;
    private final PictureMessageStrategy pictureMessageStrategy;
    private final TextMessageStrategy textMessageStrategy;
    private final VideoMessageStrategy videoMessageStrategy;

    public WechatMessageHandlerImplement(EventStrategy eventStrategy, PictureMessageStrategy pictureMessageStrategy, TextMessageStrategy textMessageStrategy, VideoMessageStrategy videoMessageStrategy) {
        this.eventStrategy = eventStrategy;
        this.pictureMessageStrategy = pictureMessageStrategy;
        this.textMessageStrategy = textMessageStrategy;
        this.videoMessageStrategy = videoMessageStrategy;
    }

    @Override
    public String verifyAPI(String msg_signature, String timestamp, String nonce, String echostr) {
        String result =  SHA1.getSHA1(token, timestamp, nonce).toLowerCase(Locale.ROOT);
        return msg_signature.equals(result) ? echostr : "error";
    }

    @Override
    public String processMessage(String xmlWechatMessageReceived) {
        WechatMessage wechatMessageReceived =
                WechatMessageUtil.convertXmlToWechatMessage(xmlWechatMessageReceived);
        WechatMessage wechatMessageReplied = null;
        return WechatMessageUtil.covertWechatMessageToXmlString(
            switch (wechatMessageReceived.getMsgType()) {
                case "event"    ->    eventStrategy.operateMessage(wechatMessageReceived);
                case "text"     ->    textMessageStrategy.operateMessage(wechatMessageReceived);
                case "picture"  ->    pictureMessageStrategy.operateMessage(wechatMessageReceived);
                case "video"    ->    videoMessageStrategy.operateMessage(wechatMessageReceived);
                default         ->    WechatMessageUtil.generateErrorMessage(wechatMessageReceived.getFromUserName());
        });
    }
}
