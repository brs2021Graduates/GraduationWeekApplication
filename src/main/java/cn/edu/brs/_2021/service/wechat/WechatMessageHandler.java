package cn.edu.brs._2021.service.wechat;

public interface WechatMessageHandler {
    String verifyAPI(String msg_signature, String timestamp, String nonce, String echostr);

    String processMessage(String wechatMessage);
}
