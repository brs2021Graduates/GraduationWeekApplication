package cn.edu.brs._2021.entity;

import java.io.Serializable;
import java.util.*;

public class Activity implements Serializable {
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
    //活动类型
    private Integer activityType;
    //能否报名
    private Integer joinable;

    public static final int NORMAL_ACTIVITY = 1;
    private List<User> participantList;

    public static final int RANK_ACTIVITY = 2;
    private Map<User, String> activityResult;

    public static final int SINGLE_MATCH_ACTIVITY = 3;
    private List<Match> activityMatches;

    public static final int GROUP_MATCH_ACTIVITY = 4;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(activityId, activity.activityId) && Objects.equals(name, activity.name) && Objects.equals(description, activity.description) && Objects.equals(startTime, activity.startTime) && Objects.equals(endTime, activity.endTime) && Objects.equals(placeId, activity.placeId) && Objects.equals(activityType, activity.activityType) && Objects.equals(participantList, activity.participantList) && Objects.equals(activityResult, activity.activityResult) && Objects.equals(activityMatches, activity.activityMatches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, name, description, startTime, endTime, placeId, activityType, participantList, activityResult, activityMatches);
    }

    public Integer getActivityId() {
        return activityId;
    }

    public Activity setActivityId(Integer activityId) {
        this.activityId = activityId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Activity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Activity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Activity setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Activity setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public Activity setPlaceId(Integer placeId) {
        this.placeId = placeId;
        return this;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public Activity setActivityType(Integer activityType) {
        this.activityType = activityType;
        return this;
    }

    public Integer getJoinable() {
        return joinable;
    }

    public Activity setJoinable(Integer joinable) {
        this.joinable = joinable;
        return this;
    }

    public Activity(Integer activityId, String name, String description, Date startTime, Date endTime, Integer placeId, Integer activityType) {
        this.activityId = activityId;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.placeId = placeId;
        this.activityType = activityType;
    }



    public Activity() {
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityId=" + activityId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", placeId=" + placeId +
                ", activityType=" + activityType +
                ", joinable=" + joinable +
                ", participantList=" + participantList +
                ", activityResult=" + activityResult +
                ", activityMatches=" + activityMatches +
                '}';
    }
}
