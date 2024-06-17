package com.pranavv51.microservices.springcloud.productsmicroservice.proxy;

import com.pranavv51.microservices.springcloud.productsmicroservice.model.EntityCoupons;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="couponServiceApplication")
public interface CouponsProxy {

    @GetMapping("coupon/get-a-coupon/couponId/{id}")
    public ResponseEntity<EntityCoupons> fetchACoupon(@PathVariable("id") Long id);

}
