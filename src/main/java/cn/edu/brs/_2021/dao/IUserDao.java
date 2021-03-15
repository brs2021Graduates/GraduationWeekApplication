package cn.edu.brs._2021.dao;

import cn.edu.brs._2021.dao.SQLProvider.UserDaoSQLProvider;
import cn.edu.brs._2021.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface IUserDao extends GenericDao<User> {
    @Override
    @Insert("INSERT INTO user(student_id, wechat_id, clazz, user_name, role, sex, nickname, user_point, password) " +
            "VALUES (#{studentId}, #{wechatId}, #{clazz}, #{name}, #{role}, #{sex}, #{nickname}, #{point}, #{password})")
    void insert(User user);

    //此方法必须有 wechat_id / user_id
    @Override
    @UpdateProvider(type = UserDaoSQLProvider.class, method = "provideUpdateSQL")
    int update(User user);

    //TODO: CONSIDER TO CLOSE, THIS WILL CAUSE ALL RECORDS RELATED TO THE STUDENT DELETED.
    @Override
    @DeleteProvider(type = UserDaoSQLProvider.class, method = "provideDeleteSQL")
    int delete(User user);

    @Override
    @SelectProvider(type = UserDaoSQLProvider.class, method = "provideCountSQL")
    int count(@Nullable User user);

    @Override
    @Results(id = "userMap", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "wechatId", column = "wechat_id"),
            @Result(property = "point", column = "user_point"),
            @Result(property = "name", column = "user_name")
    })
    @SelectProvider(type = UserDaoSQLProvider.class, method = "provideGetSQL")
    User get(User user);

    @Override
    @ResultMap("userMap")
    @Select("SELECT * FROM user")
    List<User> selectAll();

    /**
     * 此方法用于绑定微信账号时使用用户姓名更新微信号
     * @param user 必须提供 wechatId, name参数。
     * @return 更新的记录数量。正常为1。
     */
    @Update("UPDATE user SET wechat_id = #{wechatId} WHERE user_name = #{name}")
    int updateWechatIdByName(User user);

    /**
     * 此方法用于解除微信Id绑定。
     * @param user 必须提供weChatId。
     * @return 更新的记录数量，正常为1。
     */
    @Update("UPDATE user SET wechat_id = NULL WHERE wechat_id = #{wechatId}")
    int unbindWechatId(User user);


    @Select("SELECT count(*) FROM user WHERE student_id = #{studentId} and password = #{password}")
    int validateUser(User user);

    @ResultMap("userMap")
    @Select("SELECT u.* FROM user_activity_table AS ua LEFT JOIN user AS u on u.student_id = ua.student_id WHERE ua.activity_id = #{activityID}")
    //SELECT u.*, a.activity_id FROM user_activity_table AS ua LEFT JOIN user AS u on u.student_id = ua.student_id LEFT JOIN" +
        //            "activity a on a.activity_id = ua.activity_id WHERE ua.activity_id = #{activityID}
    List<User> findParticipateUserByActivityId(int activityId);

    @ResultMap("userMap")
    @Select("SELECT student_id, user_point, user_name FROM user ORDER BY user_point DESC")
    List<User> findUserPoint(@Param("limit") int limit);
}
