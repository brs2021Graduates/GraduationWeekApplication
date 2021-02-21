package cn.edu.brs._2021.service.wechat.implement.messageStrategy;

import cn.edu.brs._2021.dao.IUserDao;
import cn.edu.brs._2021.entity.User;
import cn.edu.brs._2021.service.wechat.utility.RedisUtil;
import cn.edu.brs._2021.entity.WechatMessage;
import cn.edu.brs._2021.service.wechat.WechatMessageStrategy;
import cn.edu.brs._2021.service.wechat.utility.RepliedMessage;
import cn.edu.brs._2021.service.wechat.utility.WechatMessageUtil;
import org.springframework.stereotype.Service;

@Service
public class TextMessageStrategy implements WechatMessageStrategy {
    final IUserDao userDao;

    public TextMessageStrategy(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public WechatMessage operateMessage(WechatMessage wechatMessage) {
        String senderId = wechatMessage.getFromUserName();
        String message = wechatMessage.getContent();

        //没 key 没号，还不是走关注的
        if (!RedisUtil.hasKey(senderId)){
            if(userDao.count(new User().setWechatId(senderId)) == 0)
                RedisUtil.setKey(senderId, "NA");
            else
                RedisUtil.setKey(senderId, 9);
        }

        String questionCode = (String) RedisUtil.getValue(senderId);

        return switch (questionCode) {
            case "NA"  -> _00IllegalRegister(senderId, message);
            case "0-0" -> _00(senderId, message);
            case "0-1" -> _01(senderId, message);
            case "0-2" -> _02(senderId, message);
            case "0-3" -> _03(senderId, message);
            case "0-4" -> _04(senderId, message);
            case "9"   -> _9(senderId, message);
            default -> WechatMessageUtil.generateErrorMessage(senderId);
        };

    }

    /**
     *
     * @param senderId 发送者微信Id
     * @param message 本条消息中 message 应为用户姓名。
     * @return 回复用户的消息。
     */
    private WechatMessage _00(String senderId, String message){
        //名字
        //名字是否不存在
        int isNameContain = userDao.count(new User().setName(message));
        if (isNameContain == 0){
            return WechatMessageUtil.generateTextMessage(senderId,
                    RepliedMessage.getRepliedMessage("0-0-nameNotFound")
                            .replace("#{这个名字}", message));
        }
        //名字是否已经被占用
        int isWechatIdContained = userDao.count(new User().setWechatId(message));
        if (isWechatIdContained != 0){
            return WechatMessageUtil.generateTextMessage(senderId,
                    RepliedMessage.getRepliedMessage("0-0-alreadyBind")
                            .replace("#{这个名字}", message));
        }
        //成功
        RedisUtil.setKey(senderId, "0-1");
        RedisUtil.setKey(senderId + "Name", message, 3600);
        return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("0-1"));
    }

    private WechatMessage _00IllegalRegister(String senderId, String message){
        RedisUtil.setKey(senderId, "0-0");
        return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("0-0"));
    }

    private WechatMessage _01(String senderId, String message){
        //学号
        //Redis记录过期
        if (!RedisUtil.hasKey(senderId + "Name")){
            RedisUtil.setKey(senderId, "0-0");
            return WechatMessageUtil.generateTextMessage(senderId,
                    RepliedMessage.getRepliedMessage("0-0-redisExpire"));
        }
        String username = (String) RedisUtil.getValue(senderId + "Name");

        //学号格式不对
        if (message.length() != 11 && message.startsWith("20")){
            return WechatMessageUtil.generateTextMessage(senderId,
                    RepliedMessage.getRepliedMessage("0-1-invalidInput"));
        }

        //Id对不上
        int studentId = userDao.count(new User().setStudentId(Long.parseLong(message)).setName(username));
        if (studentId == 0){
            return WechatMessageUtil.generateTextMessage(senderId,
                    RepliedMessage.getRepliedMessage("0-1-validationFailed"));
        }
        //成功
        userDao.updateWechatIdByName(new User().setWechatId(senderId).setName(username));
        RedisUtil.setKey(senderId, "0-2");
        return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("0-2"));
    }

    private WechatMessage _02(String senderId, String message){
        //性别
        if(!("0".equals(message) || "1".equals(message))){
            return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("0-2-invalidInput"));
        }
        userDao.update(new User().setWechatId(senderId).setSex(Integer.parseInt(message)));
        RedisUtil.setKey(senderId, "0-3");
        return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("0-3"));

    }

    private WechatMessage _03(String senderId, String message){
        //昵称
        //不满足条件
        if (!message.matches("^[\\u4E00-\\u9FA5A-Za-z0-9]{4,12}$")){
            return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("0-3-invalidInput"));
        }
        userDao.update(new User().setWechatId(senderId).setNickname(message));
        RedisUtil.setKey(senderId, "0-4");
        return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("0-4"));
    }

    private WechatMessage _04(String senderId, String message){
        //4-12位中文、英文、数字但不包括下划线等符号
        //不满足条件
        if (!message.matches("^[a-zA-Z]\\w{5,17}$")){
            return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("0-4-invalidInput"));
        }
        userDao.update(new User().setWechatId(senderId).setPassword(message));
        RedisUtil.setKey(senderId, "9");
        return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("9"));
    }

    private WechatMessage _9(String senderId, String message) {
        return WechatMessageUtil.generateTextMessage(senderId, message);
    }
}
