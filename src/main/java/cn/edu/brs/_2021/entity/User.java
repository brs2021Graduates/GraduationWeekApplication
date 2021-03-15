package cn.edu.brs._2021.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    public static final int NORMAL_STUDENT = 0;
    public static final int MENTOR = 1;
    public static final int SUPERVISOR = 9;

    public static final int MALE = 1;
    public static final int FEMALE = 0;

    Long studentId;
    String wechatId;
    String name;
    String nickname;
    Integer clazz;
    Integer sex;
    Integer role;
    Float point;
    @JsonIgnore
    String password;


    public User() {
    }

    public User(Long studentId, String wechatId, String name, String nickname, Integer clazz, Integer sex, Integer role, Float point, String password) {
        this.studentId = studentId;
        this.wechatId = wechatId;
        this.name = name;
        this.nickname = nickname;
        this.clazz = clazz;
        this.sex = sex;
        this.role = role;
        this.point = point;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "role=" + role +
                ", clazz=" + clazz +
                ", studentId=" + studentId +
                ", wechatId='" + wechatId + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", nickname='" + nickname + '\'' +
                ", point=" + point +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getRole() {
        return role;
    }

    public User setRole(Integer role) {
        this.role = role;
        return this;
    }

    public Integer getClazz() {
        return clazz;
    }

    public User setClazz(Integer clazz) {
        this.clazz = clazz;
        return this;
    }

    public Long getStudentId() {
        return studentId;
    }

    public User setStudentId(Long studentId) {
        this.studentId = studentId;
        return this;
    }

    public String getWechatId() {
        return wechatId;
    }

    public User setWechatId(String wechatId) {
        this.wechatId = wechatId;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public User setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public User setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Float getPoint() {
        return point;
    }

    public User setPoint(Float point) {
        this.point = point;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(role, user.role) && Objects.equals(clazz, user.clazz) && Objects.equals(studentId, user.studentId) && Objects.equals(wechatId, user.wechatId) && Objects.equals(name, user.name) && Objects.equals(sex, user.sex) && Objects.equals(nickname, user.nickname) && Objects.equals(point, user.point) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, clazz, studentId, wechatId, name, sex, nickname, point, password);
    }
}
