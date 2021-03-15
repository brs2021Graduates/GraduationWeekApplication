package cn.edu.brs._2021.service.web;

import cn.edu.brs._2021.dao.IActivityDao;
import cn.edu.brs._2021.dao.IUserDao;
import cn.edu.brs._2021.entity.Activity;
import cn.edu.brs._2021.service.utility.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRequestHandler {
    static IActivityDao activityDao;
    static IUserDao userDao;
    @Autowired
    public void setActivityDao(IActivityDao activityDao){
        UserRequestHandler.activityDao = activityDao;
    }
    @Autowired
    public void setUserDao(IUserDao userDao) { UserRequestHandler.userDao = userDao; }

    public static List<Activity> findActivity(){
        return activityDao.findAll(null);
    };
    public static List<Activity> findJoinableActivity() {
        return activityDao.findAll(new Activity().setJoinable(1));
    }
    public static int joinActivity(long studentId, int activityId) {
        return activityDao.joinActivity(studentId, activityId);
    }
}
