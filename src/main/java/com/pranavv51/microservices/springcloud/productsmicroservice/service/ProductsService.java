package com.pranavv51.microservices.springcloud.productsmicroservice.service;

import com.pranavv51.microservices.springcloud.productsmicroservice.model.EntityCoupons;
import com.pranavv51.microservices.springcloud.productsmicroservice.model.ProductsEntity;
import com.pranavv51.microservices.springcloud.productsmicroservice.proxy.CouponsProxy;
import com.pranavv51.microservices.springcloud.productsmicroservice.repository.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;


@Service
public class ProductsService {

    @Autowired
    private ProductsRepo instanceAll;

    @Autowired
    private CouponsProxy proxyInst;

    private boolean validatePIDandPName(int pid,String pname){
        ProductsEntity inst = instanceAll.findByid(pid);
        if(inst.getName().equals(pname)){
            return true;
        }
        return false;
    }
    private boolean ProductExistValidator(int pid,String pname){
        ProductsEntity res = instanceAll.findByIdAndName(pid,pname);
        if(res==null){
            return false;
        }
        return true;
    }


    //create
    public String addAProductEntireData(ProductsEntity entityInst){

        ProductsEntity entityAttr = instanceAll.findByname(entityInst.getName());

        if(entityAttr==null){
            entityInst.setName(entityInst.getName().toUpperCase());
            instanceAll.save(entityInst);
            return "Success!";
        }
        return "Record already exists for the same product name!";


    }

    public String deleteProductEntireProduct(int pid, String pname){
        boolean res =  validatePIDandPName(pid,pname);
        if(res){
            instanceAll.deleteById(pid);
            return "Success";
        }
        else{
            return "Failure";
        }

    }

    public String updateProductEntireProduct(ProductsEntity entityAttr){
        if(ProductExistValidator(entityAttr.getId(),entityAttr.getName())){
            instanceAll.save(entityAttr);
            return "Success";
        }
        return "Product not found!";
    }

    public String getStatement(long id){
        //EntityCoupons
        ResponseEntity<EntityCoupons> Couponresponse = proxyInst.fetchACoupon(id);
        EntityCoupons fetchedCoupon = Couponresponse.getBody();

        String date = fetchedCoupon.getExpiryDate();


        LocalDate expDate = LocalDate.parse(date);
        if(!expDate.isBefore(LocalDate.now())){
            date="The expiry date of coupon is: "+date+" The discount is: "+fetchedCoupon.getDiscount()+"%.   ";
            date+="The coupon is available, not yet expired!";
        }
        else{
            date="The expiry date of coupon is: "+date+" The discount is: "+fetchedCoupon.getDiscount()+"%.   ";
            date+="The coupon is not available, it has expired!";
        }


        return date;
    }

    public String applycouponDisc(long cid,int pid){
        ResponseEntity<EntityCoupons> Couponresponse = proxyInst.fetchACoupon(cid);
        EntityCoupons fetchedCoupon = Couponresponse.getBody();

        String date = fetchedCoupon.getExpiryDate();
        String result_str="";

        LocalDate expDate = LocalDate.parse(date);
        if(!expDate.isBefore(LocalDate.now())){
            ProductsEntity product = instanceAll.findByid(pid);
            if(product!=null){
                double discount = (product.getPrice()/100)*fetchedCoupon.getDiscount();
                double final_price = product.getPrice() - discount;
                result_str = "Product name: "+product.getName()+". Product price: "+product.getPrice()+". Discount: "+String.valueOf(discount)+". Final Price: "+String.valueOf(final_price);
            }
            else{
                result_str = "Unable to fetch the product!";
            }
        }
        else{
            result_str = "The entered coupon is expired!Try it with a different coupon";
        }

        return result_str;

    }

    public List<ProductsEntity> getAllproducts(){
        return instanceAll.findAll();
    }


}
