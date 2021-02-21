package cn.edu.brs._2021.service.wechat;

import cn.edu.brs._2021.entity.WechatMessage;

public interface WechatMessageStrategy {
    WechatMessage operateMessage(WechatMessage wechatMessage);
}
