package cn.edu.brs._2021.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Match<T> {
    Date matchStartTime;
    Date matchEndTime;
    int placeId;
    String rule;
    long judgeStudentId;

    public static final int SINGLE_MATCH_ACTIVITY = 3;
    public static final int GROUP_MATCH_ACTIVITY = 4;

    List<T> participantUserList;
    Map<T, String> MatchResultMap;
}
