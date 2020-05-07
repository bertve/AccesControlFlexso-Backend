package com.springBoot.keyAPI.model.dto;

public class KeyRequest {
    private Long userId,officeId;
    private String deviceId,publicKey;

    public KeyRequest(Long userId, Long officeId, String deviceId, String publicKey) {
        this.userId = userId;
        this.officeId = officeId;
        this.deviceId = deviceId;
        this.publicKey = publicKey;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
