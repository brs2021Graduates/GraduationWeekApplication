package cn.edu.brs._2021.entity.activity;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractMatch implements Serializable {
    private int id;
    private String matchSuffix;
    private String matchRule;
    private Date matchStart;
    private Date matchEnd;
}
