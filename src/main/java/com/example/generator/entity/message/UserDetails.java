package com.example.generator.entity.message;

import com.example.generator.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDetails {
    private boolean IDpass;
    private boolean ban;
    private String email;
    private String name;
    private Integer num;
    private String phone;
    private Integer punishnum;

    public UserDetails(User user) {
        IDpass= true;
        // 先设置为false
        ban= false;
        email= user.getEmail();
        name=user.getName();
        num=user.getUserid();
        phone= user.getPhone();
        punishnum=user.getPunishnum();
    }

    @JsonProperty("IDpass")
    public boolean isIDpass() {
        return IDpass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPunishnum() {
        return punishnum;
    }

    public void setPunishnum(Integer punishnum) {
        this.punishnum = punishnum;
    }

    public void setIDpass(boolean IDpass) {
        this.IDpass = IDpass;
    }

    public boolean isBan() {
        return ban;
    }
    @JsonProperty("ban")
    public void setBan(boolean ban) {
        this.ban = ban;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
