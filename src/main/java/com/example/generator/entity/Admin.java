package com.example.generator.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    private String account;

    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
            "account=" + account +
            ", password=" + password +
        "}";
    }
}
