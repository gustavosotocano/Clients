package com.gas.dominio;

import com.gas.dominio.exception.anotation.UniqueEmail;

;
public class Client {
    private String sharedKey;
    private String businessId;

    @UniqueEmail(message = "{Unique.user.email}")
    private String email;
    private String phone;

    public String getSharedKey() {
        return sharedKey;
    }

    public void setSharedKey(String sharedKey) {
        this.sharedKey = sharedKey;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
