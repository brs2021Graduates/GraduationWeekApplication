package cn.edu.brs._2021.service.wechat.implement.messageStrategy;

import cn.edu.brs._2021.dao.IUserDao;
import cn.edu.brs._2021.entity.User;
import cn.edu.brs._2021.service.utility.RedisUtil;
import cn.edu.brs._2021.entity.WechatMessage;
import cn.edu.brs._2021.service.wechat.WechatMessageStrategy;
import cn.edu.brs._2021.service.utility.RepliedMessage;
import cn.edu.brs._2021.service.utility.WechatMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EventStrategy implements WechatMessageStrategy {
    IUserDao userDao;

    @Autowired
    public EventStrategy(IUserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public WechatMessage operateMessage(WechatMessage wechatMessage) {
        String senderId = wechatMessage.getFromUserName();
        if ("subscribe".equals(wechatMessage.getEvent())){
            if (userDao.count(new User().setWechatId(senderId)) == 0) {
                RedisUtil.setKey(senderId, "0-0");
                return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("0-0"));
            } else {
                RedisUtil.setKey(senderId, "9");
                return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("#"));
            }
        }

        if ("unsubscribe".equals(wechatMessage.getEvent()) && userDao.count(new User().setWechatId(senderId)) != 0){
            RedisUtil.deleteKey(senderId);
            userDao.unbindWechatId(new User().setWechatId(senderId));
            return WechatMessageUtil.generateTextMessage(senderId, " ");
        }

        return WechatMessageUtil.generateErrorMessage(senderId);
    }

}
