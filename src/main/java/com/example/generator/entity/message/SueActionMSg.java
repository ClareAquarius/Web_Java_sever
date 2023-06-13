package com.example.generator.entity.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SueActionMSg {
    private int sueID;

    public int getSueID() {
        return sueID;
    }
    @JsonProperty("sueID")
    public void setSueID(int sueID) {
        this.sueID = sueID;
    }
}
