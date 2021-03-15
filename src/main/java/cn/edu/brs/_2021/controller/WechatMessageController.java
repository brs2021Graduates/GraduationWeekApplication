package cn.edu.brs._2021.controller;

import cn.edu.brs._2021.service.wechat.WechatMessageHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WechatMessageController {
    private final WechatMessageHandler wechatMessageHandler;

    public WechatMessageController(WechatMessageHandler wechatMessageHandler) {
        this.wechatMessageHandler = wechatMessageHandler;
    }

    //@RequestMapping(value = "/wechat", method = RequestMethod.GET)
    public @ResponseBody String validate(String signature, String timestamp, String nonce, String echostr) {
        return wechatMessageHandler.verifyAPI(signature, timestamp, nonce, echostr);
    }

    @RequestMapping(value = "/api/wechat", method = RequestMethod.POST, produces="application/xml;charset=UTF-8")
    public @ResponseBody String processMessage(@RequestBody String wechatMessage)
    {
        return wechatMessageHandler.processMessage(wechatMessage);
    }
}
