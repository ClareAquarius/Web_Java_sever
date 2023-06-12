package com.example.generator.entity.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserReturnMeg {
    public UserReturnMeg(String avatarURL, String name, String email) {
        this.avatarURL = avatarURL;
        this.name = name;
        this.email = email;
    }

    // RETURN的话要添加一个合适的序列化器 @JsonProperty
    @JsonProperty("avatarURL")
    private String avatarURL;

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

}
