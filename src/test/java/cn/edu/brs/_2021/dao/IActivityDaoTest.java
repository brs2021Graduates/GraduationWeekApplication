package cn.edu.brs._2021.dao;


import cn.edu.brs._2021.entity.Activity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IActivityDaoTest {
    @Autowired
    private IActivityDao activityDao;

    @Test
    @Transactional
    public void insert() {
        Activity activity = new Activity(null, "测试普通活动", "这是一个普通活动的测试",
                new Date(2021 - 1900, Calendar.SEPTEMBER,5, 0, 0),
                new Date(2021 - 1900,Calendar.OCTOBER,5, 0, 0),
                1 , Activity.NORMAL_ACTIVITY);
        activityDao.insert(activity);
        System.out.println("创建的活动主键id为: " + activity.getActivityId());
    }

    @Test
    @Transactional
    public void update() {
        Activity activity = new Activity(null, "测试普通活动", "这是一个普通活动的测试",
                new Date(2021 - 1900, Calendar.SEPTEMBER,5, 0, 0),
                new Date(2021 - 1900,Calendar.OCTOBER,5, 0, 0),
                1 , Activity.NORMAL_ACTIVITY);
        activityDao.insert(activity);
        Activity pramActivity = new Activity();
        pramActivity.setActivityId(activity.getActivityId());
        pramActivity.setDescription("修改修改修改");
        activityDao.update(pramActivity);
    }

    @Test
    @Transactional
    public void delete() {
    }

    @Test
    @Transactional
    public void count() {
        for (int i = 0; i < 10; i++) {
            insert();
        }
        Assert.assertEquals(10, activityDao.count(null));
    }

    @Test
    @Transactional
    public void get() {
        System.out.println(activityDao.get(new Activity().setActivityId(1)));
    }


    @Test
    @Transactional
    public void selectAll() {
        for (int i = 0; i < 10; i++) {
            insert();
        }
        System.out.println(activityDao.selectAll());
    }


    @Test
    @Transactional
    public void findParticipateActivityByUserId(){
        System.out.println(activityDao.findParticipateActivityByUserId(20181015519L));
    }

    @Test
    @Transactional
    public void testFindAll(){
        System.out.println(activityDao.findAll(null));
        System.out.println(activityDao.findAll(new Activity().setJoinable(1)));
    }

    @Test
    @Transactional
    public void testJoinActivity(){
        System.out.println(activityDao.joinActivity(20181015519L, 1));
    }

    @Test
    @Transactional
    public void testFindUserPoint(){
        System.out.println(
        );
    }
}