package cn.edu.brs._2021.service.aspect;

import cn.edu.brs._2021.entity.WechatMessage;
import com.mysql.cj.log.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class LogUtil {
    private static final Logger logger = LoggerFactory.getLogger(Logger.class);

    @Pointcut("execution(* cn.edu.brs._2021.service.wechat.implement.messageStrategy.TextMessageStrategy.operateMessage(..))")
    public void messageLogPointCut() {
    }

    @Before("messageLogPointCut()")
    public void BeforeOperate(JoinPoint joinPoint){
        WechatMessage wechatMessage = (WechatMessage) joinPoint.getArgs()[0];
        logger.info("\n" +
                "==============  [微信消息] ==============            \n" +
                "senderId:    " + wechatMessage.getFromUserName() + "\n" +
                "createTime:  " + wechatMessage.getCreateTime() +   "\n" +
                "content:     " + wechatMessage.getContent() +      "\n" +
                "==============  [消息终了] =============="
        );
    }

}
