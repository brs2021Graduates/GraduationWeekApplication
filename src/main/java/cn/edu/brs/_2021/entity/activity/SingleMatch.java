package cn.edu.brs._2021.entity.activity;

import cn.edu.brs._2021.entity.User;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class SingleMatch extends AbstractMatch {
    private List<User> participantList;
    private Map<User, String> userScoreList;
}
