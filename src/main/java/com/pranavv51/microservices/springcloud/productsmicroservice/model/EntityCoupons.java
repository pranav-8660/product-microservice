package com.pranavv51.microservices.springcloud.productsmicroservice.model;


public class EntityCoupons {
    private Long id;
    private String code;
    private String expiryDate;
    private float discount;

    public EntityCoupons(Long id, String code, String expiryDate, float discount) {
        this.id = id;
        this.code = code;
        this.expiryDate = expiryDate;
        this.discount = discount;
    }

    public EntityCoupons() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
