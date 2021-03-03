package cn.edu.brs._2021.service.wechat.implement.messageStrategy;

import cn.edu.brs._2021.dao.IUserDao;
import cn.edu.brs._2021.entity.User;
import cn.edu.brs._2021.service.wechat.utility.RedisUtil;
import cn.edu.brs._2021.entity.WechatMessage;
import cn.edu.brs._2021.service.wechat.WechatMessageStrategy;
import cn.edu.brs._2021.service.wechat.utility.RepliedMessage;
import cn.edu.brs._2021.service.wechat.utility.WechatMessageUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                RedisUtil.setKey(senderId, "9");
        }

        String questionCode = (String) RedisUtil.getValue(senderId);

        //正常情况，根据回应的问题编码调用对应的处理方法
        return switch (questionCode) {
            case "NA"  -> _00IllegalRegister(senderId, message);
            case "0-0" -> _00(senderId, message);
            case "0-1" -> _01(senderId, message);
            case "0-2" -> _02(senderId, message);
            case "0-3" -> _03(senderId, message);
            case "0-4" -> _04(senderId, message);

            case "9"   -> _9(senderId, message);
            case "1"   -> _1(senderId, message);
            case "2"   -> _2(senderId, message);
            case "3"   -> _3(senderId, message);



            case "end" -> _end(senderId, message);

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
        if (message.length() != 11 || !message.startsWith("20")){
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
        if (!message.matches("^[a-zA-Z0-9]\\w{5,17}$")){
            return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("0-4-invalidInput"));
        }
        userDao.update(new User().setWechatId(senderId).setPassword(message));
        RedisUtil.setKey(senderId, "9");
        return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("9"));
    }

    private WechatMessage _9(String senderId, String message) {
        if (message.matches("^[1-3]*$")){
            RedisUtil.setKey(senderId, message);
        }
        return switch (message) {
            case "1"  -> WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("1"));
            case "2" -> WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("2"));
            case "3" -> WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("3"));
            default -> WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("menu-invalidInput"));
        };
    }

    private WechatMessage _1(String senderId, String message){
        if (message.equals("9")){
            RedisUtil.setKey(senderId, "9");
        }
        return switch (message){
            case "1"  -> _11(senderId, message);
            case "2" -> _12(senderId, message);
            case "9" -> WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("9"));
            default -> WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("menu-invalidInput"));
        };
    }

    private WechatMessage _11(String senderId, String message){
        RedisUtil.setKey(senderId, "end");
        //TODO: 在数据库内查询活动信息并反馈。现做假数据实现。
        return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("1-1")
            .replace("#{普通活动信息}",
                "成人礼  2021/05/28 0:00a.m. Pending  未开始\n" +
                "毕业晚会 2021/05/31 0:00a.m. Pending  未开始\n" +
                "毕业典礼 2021/06/01 0:00a.m. Pending  未开始")
            .replace("#{排名比赛信息}", "暂无记录")
            .replace("#{单人比赛信息}", "暂无记录")
            .replace("#{团体比赛信息}", "暂无记录")
        );
    }

    private WechatMessage _12(String senderId, String message){
        RedisUtil.setKey(senderId, "end");
        return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("1-2")
            .replace("#{组队信息}", "暂无记录")
        );
    }

    private WechatMessage _2(String senderId, String message) {
        if (message.equals("9")){
            RedisUtil.setKey(senderId, 9);
        }
        return switch (message) {
            case "1" -> _21(senderId, message);
            case "2" -> _22(senderId, message);
            case "3" -> _23(senderId, message);
            case "9" -> WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("9"));
            default  -> WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("menu-invalidInput"));
        };
    }

    private WechatMessage _21(String senderId, String message){
        RedisUtil.setKey(senderId, "end");
        return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("2-1")
                .replace("#{个人积分}", "10.0")
                .replace("#{团队积分}", "暂无记录")
        );
    }

    private WechatMessage _22(String senderId, String message){
        RedisUtil.setKey(senderId, "end");
        return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("2-2")
                .replace("#{排行榜}", "暂无记录")
        );
    }

    private WechatMessage _23(String senderId, String message){
        RedisUtil.setKey(senderId, "end");
        return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("2-3")
                .replace("#{排行榜}", "暂无记录")
        );
    }

    private WechatMessage _3(String senderId, String message){
        //TODO: 将建议纳入数据库。
        RedisUtil.setKey(senderId, "end");
        return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("3-1"));
    }

    private WechatMessage _end(String senderId, String message){
        RedisUtil.setKey(senderId, "9");
        return WechatMessageUtil.generateTextMessage(senderId, RepliedMessage.getRepliedMessage("9"));
    };



}
