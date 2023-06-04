package com.example.generator.entity.message;

public class BrowseMeg {
    public String getUserTelephone() {
        return userTelephone;
    }
    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getpartition() {
        return partition;
    }

    public void setpartition(String partition) {
        this.partition = partition;
    }

    public String getSearchinfo() {
        return searchinfo;
    }

    public void setSearchinfo(String searchinfo) {
        this.searchinfo = searchinfo;
    }

    private String userTelephone;
    private String partition;
    private String searchinfo;


}
