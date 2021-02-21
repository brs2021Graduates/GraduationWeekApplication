package cn.edu.brs._2021.dao;

import cn.edu.brs._2021.dao.SQLProvider.ActivityDaoSQLProvider;
import cn.edu.brs._2021.dao.SQLProvider.UserDaoSQLProvider;
import cn.edu.brs._2021.entity.activity.StandardActivity;
import org.apache.ibatis.annotations.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IActivityDao extends GenericDao<StandardActivity>{
    @Override
    @Insert("INSERT INTO activity(activity_id, activity_name, description, start_time, end_time, place_id, activity_type) " +
            "VALUES (#{activityId}, #{name}, #{description}, #{startTime}, #{endTime}, #{placeId}, #{activityType})")
    @Options(useGeneratedKeys = true, keyProperty = "activityId", keyColumn = "activity_id")
    void insert(StandardActivity entity);

    @Override
    @UpdateProvider(type = ActivityDaoSQLProvider.class, method = "provideUpdateSQL")
    int update(StandardActivity entity);

    @Override
    @Delete("DELETE FROM activity WHERE activity_id = #{activityId}")
    int delete(StandardActivity entity);

    @Override
    @SelectProvider(type = ActivityDaoSQLProvider.class, method = "provideCountSQL")
    int count(@Nullable StandardActivity entity);

    @Override
    @Select("SELECT * FROM activity WHERE activity_id = #{activityId}")
    @Results(id = "activityMap", value = {
            @Result(property = "activityId", column = "activity_id"),
            @Result(property = "name", column = "activity_name"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "placeId", column = "place_id"),
            @Result(property = "activityType", column = "activity_type")
    })
    StandardActivity get(StandardActivity entity);

    @Override
    @ResultMap("activityMap")
    @Select("SELECT * FROM activity")
    List<StandardActivity> selectAll();

    <T extends StandardActivity> T test(T entity);



}
