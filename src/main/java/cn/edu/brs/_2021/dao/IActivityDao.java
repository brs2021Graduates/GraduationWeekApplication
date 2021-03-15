package cn.edu.brs._2021.dao;

import cn.edu.brs._2021.dao.SQLProvider.ActivityDaoSQLProvider;
import cn.edu.brs._2021.entity.Activity;
import org.apache.ibatis.annotations.*;
import org.apache.xmlbeans.impl.jam.JProperty;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IActivityDao extends GenericDao<Activity>{
    @Override
    @Insert("INSERT INTO activity(activity_id, activity_name, description, start_time, end_time, place_id, activity_type) " +
            "VALUES (#{activityId}, #{name}, #{description}, #{startTime}, #{endTime}, #{placeId}, #{activityType})")
    @Options(useGeneratedKeys = true, keyProperty = "activityId", keyColumn = "activity_id")
    void insert(Activity entity);

    @Override
    @UpdateProvider(type = ActivityDaoSQLProvider.class, method = "provideUpdateSQL")
    int update(Activity entity);

    @Override
    @Delete("DELETE FROM activity WHERE activity_id = #{activityId}")
    int delete(Activity entity);

    @Override
    @SelectProvider(type = ActivityDaoSQLProvider.class, method = "provideCountSQL")
    int count(@Nullable Activity entity);

    @Override
    @Select("SELECT * FROM activity WHERE activity_id = #{activityId}")
    @Results(id = "activityMap", value = {
            @Result(property = "activityId", column = "activity_id"),
            @Result(property = "name", column = "activity_name"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "placeId", column = "place_id"),
            @Result(property = "description", column = "description"),
            @Result(property = "activityType", column = "activity_type"),
            @Result(property = "joinable", column = "joinable")
    })
    Activity get(Activity entity);

    @Results(id = "activityMapWithUserList", value = {
            @Result(property = "activityId", column = "activity_id"),
            @Result(property = "name", column = "activity_name"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "placeId", column = "place_id"),
            @Result(property = "activityType", column = "activity_type"),
            @Result(property = "description", column = "description"),
            @Result(property = "joinable", column = "joinable"),
            @Result(property = "participantList", column = "activity_id", many = @Many(select = "cn.edu.brs._2021.dao.IUserDao.findParticipateUserByActivityId" ))
    })
    @Select("SELECT a.* FROM user_activity_table AS ua LEFT JOIN activity AS a on a.activity_id = ua.activity_id WHERE ua.student_id = #{studentId}")
    List<Activity> findParticipateActivityByUserId(long studentId);

    @ResultMap("activityMap")
    @SelectProvider(type = ActivityDaoSQLProvider.class, method = "provideFindSQL")
    List<Activity> findAll(@Nullable Activity entity);

    @Insert("INSERT INTO user_activity_table(student_id, activity_id) VALUES (#{studentId}, #{activityId})")
    @Options(useGeneratedKeys = true, keyProperty = " ", keyColumn = "activity_record_id")
    int joinActivity(@Param("studentId") long studentId, @Param("activityId") int activityId);




}
