package com.base.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Phong on 11/9/2016.
 */

public class User implements Serializable{
    @SerializedName("user_id")
    @Expose
    private String user_id = "";

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
