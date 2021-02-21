package cn.edu.brs._2021.entity.activity;

import cn.edu.brs._2021.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class GroupMatch extends AbstractMatch{
    private List<Group> groupList;
    private Map<Group, String> groupScoreList;
}
