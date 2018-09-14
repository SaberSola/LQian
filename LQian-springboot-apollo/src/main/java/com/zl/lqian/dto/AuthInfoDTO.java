package com.zl.lqian.dto;

import java.io.Serializable;

public class AuthInfoDTO implements Serializable {

    private String userName;
    private String client_id;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
}
