package cn.edu.brs._2021.entity.activity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class StandardActivity implements Serializable {
    //活动Id
    private Integer activityId;
    //活动名字
    private String name;
    //活动介绍
    private String description;
    //开始日期
    private Date startTime;
    //结束时间
    private Date endTime;
    //地点
    private Integer placeId;

    private Integer activityType;

    public static final int NORMAL_ACTIVITY = 1;
    public static final int RANK_ACTIVITY = 2;
    public static final int SINGLE_MATCH_ACTIVITY = 3;
    public static final int GROUP_MATCH_ACTIVITY = 4;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandardActivity that = (StandardActivity) o;
        return Objects.equals(activityId, that.activityId) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(placeId, that.placeId) && Objects.equals(activityType, that.activityType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, name, description, startTime, endTime, placeId, activityType);
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public StandardActivity(Integer activityId, String name, String description, Date startTime, Date endTime, Integer placeId, Integer activityType) {
        this.activityId = activityId;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.placeId = placeId;
        this.activityType = activityType;
    }

    public StandardActivity() {
    }

    @Override
    public String toString() {
        return "StandardActivity{" +
                "activityId=" + activityId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", placeId=" + placeId +
                ", activityType=" + activityType +
                '}';
    }
}
