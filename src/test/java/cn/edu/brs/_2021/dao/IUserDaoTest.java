package cn.edu.brs._2021.dao;

import cn.edu.brs._2021.dao.IUserDao;
import cn.edu.brs._2021.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IUserDaoTest {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private TransactionManager transactionManager;


    @Test
    public void randomTest(){
        User pramUser = new User().setWechatId("oxyAn625SR1BSqYvB9z6l8a-yFkU").setPassword("123456");
        System.out.println(pramUser);
        userDao.update(pramUser);

    }
    @Test
    @Transactional
    public void testInsert(){
        User user1 = new User(20181015519L,"test1",  "何逸","帅哥何逸", 2 , User.MALE, User.SUPERVISOR
                 ,93.75F, "wfxxnb");
        User user2 = new User(20181015520L, "test2","测试工具人", "美女美女" ,3,  User.FEMALE, User.NORMAL_STUDENT
                 ,250.25F, "wfxxlj");
        userDao.insert(user1);
        userDao.insert(user2);
    }

    @Test
    @Transactional
    public void testUpdate(){
        //UpdateByStudentId
        User user1 = new User(20181015519L,"test1",  "何逸","帅哥何逸", 2 , User.MALE, User.SUPERVISOR
                ,93.75F, "wfxxnb");
        userDao.insert(user1);
        //理论结果
        //20181015519	test1	2	何逸	9	1	帅哥何逸	93.75	wfxxnb

        User userChange = new User();
        userChange.setClazz(10);
        userChange.setStudentId(20181015519L);
        Assert.assertEquals(1,userDao.update(userChange));
        //理论结果
        //20181015519	test1	10	何逸	9	1	帅哥何逸	93.75	wfxxnb

        try{
            userChange.setStudentId(null);
            userDao.update(userChange);
        }catch (Exception e){
            System.out.println("拦截成功");
        }

        //UpdateByWechatId
        userChange.setClazz(8);
        userChange.setStudentId(20181015519L);
        userDao.update(userChange);
        //理论结果
        //20181015519	test1	8	何逸	9	1	帅哥何逸	93.75	wfxxnb
    }

    @Test
    @Transactional
    public void testDelete() {
        User user1 = new User(20181015519L,"test1",  "何逸","帅哥何逸", 2 , User.MALE, User.SUPERVISOR
                ,93.75F, "wfxxnb");
        userDao.insert(user1);
        User pramUser = new User();
        pramUser.setStudentId(20181015519L);
        Assert.assertEquals(1, userDao.delete(pramUser));
        Assert.assertEquals(0, userDao.delete(pramUser));
    }

    @Test
    @Transactional
    public void testCount() {
        userDao.count(null);
        Assert.assertEquals(0, userDao.count(null));
        User user1 = new User(20181015519L,"test1",  "何逸","帅哥何逸", 2 , User.MALE, User.SUPERVISOR
                ,93.75F, "wfxxnb");
        userDao.insert(user1);
        Assert.assertEquals(1, userDao.count(null));
        Assert.assertEquals(1, userDao.count(user1));
        user1.setClazz(10);
        Assert.assertEquals(0, userDao.count(user1));
    }


    @Test
    @Transactional
    public void testGet() {
        User user1 = new User(20181015519L,"test1",  "何逸","帅哥何逸", 2 , User.MALE, User.SUPERVISOR
                ,93.75F, "wfxxnb");
        userDao.insert(user1);
        User pramUser = new User();
        pramUser.setStudentId(20181015519L);
        Assert.assertEquals(user1, userDao.get(pramUser));
    }

    @Test
    @Transactional
    public void testSelectAll() {
        testInsert();
        System.out.println(userDao.selectAll());
    }
}
