package com.example.generator.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userid;

    private String phone;

    private String email;

    private String password;

    private String name;

    private String profile;

    private LocalDate banTime;
    private Integer punishnum;
    public Integer getPunishnum() {
        return punishnum;
    }

    public void setPunishnum(Integer punishnum) {
        this.punishnum = punishnum;
    }

    public User() {
        
    }

    public User(Integer userid, String phone, String email, String password, String name, String profile, LocalDate banTime) {
        this.userid = userid;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
        this.profile = profile;
        this.banTime = banTime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
    public LocalDate getBanTime() {
        return banTime;
    }

    public void setBanTime(LocalDate banTime) {
        this.banTime = banTime;
    }

    @Override
    public String toString() {
        return "User{" +
            "userid=" + userid +
            ", phone=" + phone +
            ", email=" + email +
            ", password=" + password +
            ", name=" + name +
            ", profile=" + profile +
            ", banTime=" + banTime +
        "}";
    }
}
