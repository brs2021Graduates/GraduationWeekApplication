package cn.edu.brs._2021.service.web;

import cn.edu.brs._2021.dao.IUserDao;
import cn.edu.brs._2021.entity.User;
import cn.edu.brs._2021.service.utility.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class Login {
    private static IUserDao userDao;

    @Autowired
    public void setUserDao(IUserDao userDao){
        Login.userDao = userDao;
    }

    public static boolean validate(User user){
        return userDao.count(new User().setStudentId(user.getStudentId()).setPassword(user.getPassword())) == 1;
    }
}
