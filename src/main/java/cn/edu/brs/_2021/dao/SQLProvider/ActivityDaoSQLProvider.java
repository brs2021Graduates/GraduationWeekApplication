package cn.edu.brs._2021.dao.SQLProvider;

import cn.edu.brs._2021.entity.User;
import cn.edu.brs._2021.entity.activity.StandardActivity;
import org.apache.ibatis.jdbc.SQL;

public class ActivityDaoSQLProvider {
    public String provideUpdateSQL(StandardActivity standardActivity){
        if (standardActivity.getActivityId() == null) throw new IllegalArgumentException("不合法参数，此接口必须通过活动Id进行更新！");
        return new SQL(){{
            UPDATE("activity");
            if (standardActivity.getName() != null) SET("activity_name = #{name}");
            if (standardActivity.getDescription() != null) SET("description = #{description}");
            if (standardActivity.getStartTime() != null) SET("start_time = #{start_time}");
            if (standardActivity.getEndTime() != null) SET("end_time = #{endTime}");
            if (standardActivity.getPlaceId() != null) SET("place_id = #{placeId}");
            if (standardActivity.getActivityType() != null) SET("activity_type = #{activityType}");
            WHERE("activity_id = #{activityId}");
        }}.toString();
    }

    public String provideCountSQL(StandardActivity standardActivity){
        if (standardActivity == null){
            return new SQL(){{
                SELECT("count(*)");
                FROM("activity");
            }}.toString();
        }
        return new SQL(){{
            SELECT("count(*)");
            FROM("activity");
            if (standardActivity.getName() != null) WHERE("activity_name = #{name}");
            if (standardActivity.getDescription() != null) WHERE("description = #{description}");
            if (standardActivity.getStartTime() != null) WHERE("start_time = #{start_time}");
            if (standardActivity.getEndTime() != null) WHERE("end_time = #{endTime}");
            if (standardActivity.getPlaceId() != null) WHERE("place_id = #{placeId}");
            if (standardActivity.getActivityType() != null) WHERE("activity_type = #{activityType}");
            if (standardActivity.getActivityId()!= null) WHERE("activity_id = #{activityId}");
        }}.toString();
    }




}
