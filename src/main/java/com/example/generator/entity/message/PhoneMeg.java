package com.example.generator.entity.message;

public class PhoneMeg {

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
// 写了构造函数则不能作为后端POST路由函数输入参数
//    public PhoneMeg(String phone) {
//        this.phone = phone;
//    }

    private String phone;

}

