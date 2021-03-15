package cn.edu.brs._2021.dao.SQLProvider;

import cn.edu.brs._2021.entity.Activity;
import org.apache.ibatis.jdbc.SQL;

public class ActivityDaoSQLProvider {
    public String provideUpdateSQL(Activity activity){
        if (activity.getActivityId() == null) throw new IllegalArgumentException("不合法参数，此接口必须通过活动Id进行更新！");
        return new SQL(){{
            UPDATE("activity");
            if (activity.getName() != null) SET("activity_name = #{name}");
            if (activity.getDescription() != null) SET("description = #{description}");
            if (activity.getStartTime() != null) SET("start_time = #{start_time}");
            if (activity.getEndTime() != null) SET("end_time = #{endTime}");
            if (activity.getPlaceId() != null) SET("place_id = #{placeId}");
            if (activity.getJoinable() != null) SET("joinable = #{joinable}");
            if (activity.getActivityType() != null) SET("activity_type = #{activityType}");
            WHERE("activity_id = #{activityId}");
        }}.toString();
    }

    public String provideCountSQL(Activity activity){
        if (activity == null){
            return new SQL(){{
                SELECT("count(*)");
                FROM("activity");
            }}.toString();
        }
        return new SQL(){{
            SELECT("count(*)");
            FROM("activity");
            if (activity.getName() != null) WHERE("activity_name = #{name}");
            if (activity.getDescription() != null) WHERE("description = #{description}");
            if (activity.getStartTime() != null) WHERE("start_time = #{start_time}");
            if (activity.getEndTime() != null) WHERE("end_time = #{endTime}");
            if (activity.getPlaceId() != null) WHERE("place_id = #{placeId}");
            if (activity.getActivityType() != null) WHERE("activity_type = #{activityType}");
            if (activity.getJoinable() != null) WHERE("joinable = #{joinable}");
            if (activity.getActivityId()!= null) WHERE("activity_id = #{activityId}");
        }}.toString();
    }

    public String provideFindSQL(Activity activity){
        if (activity == null){
            return new SQL(){{
                SELECT("*");
                FROM("activity");
            }}.toString();
        }
        return new SQL(){{
            SELECT("*");
            FROM("activity");
            if (activity.getName() != null) WHERE("activity_name = #{name}");
            if (activity.getDescription() != null) WHERE("description = #{description}");
            if (activity.getStartTime() != null) WHERE("start_time = #{start_time}");
            if (activity.getEndTime() != null) WHERE("end_time = #{endTime}");
            if (activity.getPlaceId() != null) WHERE("place_id = #{placeId}");
            if (activity.getActivityType() != null) WHERE("activity_type = #{activityType}");
            if (activity.getJoinable() != null) WHERE("joinable = #{joinable}");
            if (activity.getActivityId()!= null) WHERE("activity_id = #{activityId}");
        }}.toString();
    }



}
