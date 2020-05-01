package com.springBoot.keyAPI.model.dto;

public class KeyRequest {
    private Long officeId;
    private Long userId;
    private String deviceId;

    public KeyRequest( Long userId,Long officeId,String deviceId) {
        this.officeId = officeId;
        this.userId = userId;
        this.deviceId = deviceId;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
