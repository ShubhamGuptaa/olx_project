package com.atcs.olx.Controller.AuthenticateController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atcs.olx.Entity.Products.Product;
import com.atcs.olx.Service.ServiceAuthenticate.ServiceAuthenticate;
import com.atcs.olx.Service.UsersProductService.UserProductService;

@RestController
@RequestMapping("/olx")
public class UsersProduct {
    @Autowired
    ServiceAuthenticate serviceUsers;

    @Autowired
    UserProductService userProductService;

    String msg = "";

    @PostMapping("/user/add_product")
    public ResponseEntity<String> addProductByUser(@RequestBody Product product){
        userProductService.addProduct(product);
        msg = "Product added successfully!";
        return new ResponseEntity<String>(msg,HttpStatus.OK);

    }
}
