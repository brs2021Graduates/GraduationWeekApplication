package cn.edu.brs._2021.dao;

import cn.edu.brs._2021.service.utility.RedisUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class redisUtilTest {

    @Before
    public void init(){
        new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void testHasKey_SetKey_GetValue_And_DeleteKey(){
        RedisUtil.setKey("testKey", "testValue");
        Assert.assertTrue(RedisUtil.hasKey("testKey"));
        Assert.assertEquals("testValue", RedisUtil.getValue("testKey"));
        RedisUtil.deleteKey("testKey");
        Assert.assertFalse(RedisUtil.hasKey("testKey"));
    }

}
