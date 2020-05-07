package com.springBoot.keyAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.security.PublicKey;
@Entity
@IdClass(KeyId.class)
@Table
public class Key implements Serializable {
    @Id
    private Long userId;
    @Id
    private Long officeId;
    @Id
    private String deviceId;

    private PublicKey publicKey;

    public Key() {
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

    public PublicKey getPublicKey() {
        return publicKey;
    }

    @Override
    public String toString() {
        return "Key{" +
                "userId=" + userId +
                ", officeId=" + officeId +
                ", deviceId='" + deviceId + '\'' +
                ", publicKey=" + publicKey +
                '}';
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
}
