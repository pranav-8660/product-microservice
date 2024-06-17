package com.pranavv51.microservices.springcloud.productsmicroservice.controller;

import com.pranavv51.microservices.springcloud.productsmicroservice.model.ProductsEntity;
import com.pranavv51.microservices.springcloud.productsmicroservice.service.ProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products/")
public class ProductsController {

    private final ProductsService serviceInst;

    public ProductsController(ProductsService serviceInst) {
        this.serviceInst = serviceInst;
    }

    // http://localhost:8400/products/add-data/prodName/{pname}/prodDesc/{pdesc}/prodPrice/{pPrice}
    @PostMapping(value = "add-data/prodName/{pname}/prodDesc/{pdesc}/prodPrice/{pPrice}")
    public String addDataintoDb(@PathVariable("pname") String name,@PathVariable("pdesc") String desc,
        @PathVariable("pPrice") double price){
        return serviceInst.addAProductEntireData(new ProductsEntity(name,desc,price));
    }


    //  http://localhost:8400/products/delete-data/prodId/{pid}/prodName/{pname}
    @DeleteMapping(value="delete-data/prodId/{pid}/prodName/{pname}")
    public String deleteDatafromDb(@PathVariable("pid") int id,@PathVariable("pname") String name){
        return serviceInst.deleteProductEntireProduct(id,name.toUpperCase());
    }

    //  http://localhost:8400/products/update-data/prodId/{pid}/prodName/{pname}/prodDesc/{pdesc}/prodPrice/{pPrice}
    @PutMapping(value = "update-data/prodId/{pid}/prodName/{pname}/prodDesc/{pdesc}/prodPrice/{pPrice}")
    public String updateDataofDb(@PathVariable("pname") String name,@PathVariable("pdesc") String desc,
                               @PathVariable("pPrice") double price,@PathVariable("pid") int id){
            return serviceInst.updateProductEntireProduct(new ProductsEntity(id,name,desc,price));

    }

    @GetMapping("get-coupon-statement/")
    public String getCoup(@RequestParam("id") Long ng){return serviceInst.getStatement(ng);}

    // http://localhost:8400/products/applyCoupon/productId/{pid}/couponId/{cid}
    @GetMapping(value="applyCoupon/productId/{pid}/couponId/{cid}")
    public String applyCoupon(@PathVariable long cid,@PathVariable int pid){
        return serviceInst.applycouponDisc(cid,pid);

    }

    // http://localhost:8400/products/get-all-products/
    @GetMapping(value="get-all-products/")
    public ResponseEntity<List<ProductsEntity>> giveAllProducts(){
        return ResponseEntity.ok(serviceInst.getAllproducts());
    }

}
