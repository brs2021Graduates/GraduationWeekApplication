package cn.edu.brs._2021.dao.SQLProvider;

import cn.edu.brs._2021.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserDaoSQLProvider {
    public String provideUpdateSQL(User user){
        return new SQL(){{
            UPDATE("user");
            if (user.getRole() != null) SET("role = #{role}");
            if (user.getClazz() != null) SET("clazz = #{clazz}");
            if (user.getName() != null) SET("user_name = #{name}");
            if (user.getSex() != null) SET("sex = #{sex}");
            if (user.getNickname() != null) SET("nickname = #{nickname}");
            if (user.getPoint() != null) SET("user_point = #{point}");
            if (user.getPassword() != null) SET("password = #{password}");
            if (user.getWechatId() != null) WHERE("wechat_id = #{wechatId}");
            else if (user.getStudentId() != null) WHERE("student_id = #{studentId}");
            else throw new IllegalArgumentException("不合法参数，此接口必须通过学生学号 / 微信号进行更新！");
        }}.toString();
    }

    public String provideDeleteSQL(User user){
        return new SQL(){{
            DELETE_FROM("user");
            if (user.getWechatId() != null) WHERE("wechat_id = #{wechatId}");
            else if (user.getStudentId() != null) WHERE("student_id = #{studentId}");
            else throw new IllegalArgumentException("不合法参数，此接口必须通过学生学号 / 微信号进行删除！");
        }}.toString();
    }

    public String provideCountSQL(User user){
        if (user == null){
            return new SQL(){{
                SELECT("count(*)");
                FROM("user");
            }}.toString();
        }
        return new SQL(){{
            SELECT("count(*)");
            FROM("user");
            if (user.getRole() != null) WHERE("role = #{role}");
            if (user.getClazz() != null) WHERE("clazz = #{clazz}");
            if (user.getName() != null) WHERE("user_name = #{name}");
            if (user.getSex() != null) WHERE("sex = #{sex}");
            if (user.getNickname() != null) WHERE("nickname = #{nickname}");
            if (user.getPoint() != null) WHERE("password = #{password}");
            if (user.getPassword() != null) WHERE("user_point = #{point}");
            if (user.getWechatId() != null) WHERE("wechat_id = #{wechatId}");
            if (user.getStudentId() != null) WHERE("student_id = #{studentId}");
        }}.toString();
    }

    //TODO:解决两个同时不为null的问题，采用异或？
    public String provideGetSQL(User user){
        return new SQL(){{
            SELECT("*");
            FROM("user");
            if (user.getWechatId() != null) WHERE("wechat_id = #{wechatId}");
            else if (user.getStudentId() != null) WHERE("student_id = #{studentId}");
            else throw new IllegalArgumentException("不合法参数，此接口必须通过学生学号 / 微信号进行删除！");
        }}.toString();
    }
}
