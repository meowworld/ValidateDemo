package com.test.demo.domain;


public class IdentityInfo {

    /**
     * lenovo 的用户id
     */
    private Long AccountID;
    /**
     * lenovo的用户名
     */
    private String Username;

    private Integer verified;

    private String DeviceID;

    private String location;

    /**
     * 别名
     */
    private String CurtName;


    public Long getAccountID() {
        return AccountID;
    }

    public void setAccountID(String accountID) {
        AccountID = Long.parseLong(accountID);
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCurtName() {
        return CurtName;
    }

    public void setCurtName(String curtName) {
        CurtName = curtName;
    }
}
