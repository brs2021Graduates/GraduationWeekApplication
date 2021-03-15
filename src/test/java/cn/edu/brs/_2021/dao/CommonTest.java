package cn.edu.brs._2021.dao;

import cn.edu.brs._2021.entity.Activity;
import cn.edu.brs._2021.entity.User;
import cn.edu.brs._2021.service.utility.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CommonTest {
    @Autowired
    IActivityDao activityDao;
    @Test
    public void testGenerateJson() throws JsonProcessingException {
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("user", new User().setClazz(1));
//        System.out.println(JsonUtil.generateErrorJson(paramMap));
        System.out.println(JsonUtil.generateObjectJson(activityDao.findAll(null)));
    }
}
