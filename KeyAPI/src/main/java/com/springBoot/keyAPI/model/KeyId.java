package com.springBoot.keyAPI.model;

import java.io.Serializable;
import java.util.Objects;

public class KeyId implements Serializable {
    private Long userId,officeId;
    private String deviceId;

    public KeyId() {
    }

    public KeyId(Long userId, Long officeId, String deviceId) {
        this.userId = userId;
        this.officeId = officeId;
        this.deviceId = deviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyId keyId = (KeyId) o;
        return Objects.equals(userId, keyId.userId) &&
                Objects.equals(officeId, keyId.officeId) &&
                Objects.equals(deviceId, keyId.deviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, officeId, deviceId);
    }

    public Long getUserId() {
        return userId;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public String getDeviceId() {
        return deviceId;
    }
}
