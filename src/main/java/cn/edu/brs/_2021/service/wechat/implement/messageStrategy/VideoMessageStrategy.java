package cn.edu.brs._2021.service.wechat.implement.messageStrategy;

import cn.edu.brs._2021.entity.WechatMessage;
import cn.edu.brs._2021.service.wechat.WechatMessageStrategy;
import org.springframework.stereotype.Component;

@Component
public class VideoMessageStrategy implements WechatMessageStrategy {
    @Override
    public WechatMessage operateMessage(WechatMessage wechatMessage) {
        return null;
    }
}
