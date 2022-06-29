package com.atcs.olx.ServiceImple.UserProductImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcs.olx.Entity.Products.Product;
import com.atcs.olx.Repository.ProductRepo.ProductRepo;
import com.atcs.olx.Service.UsersProductService.UserProductService;

@Component
public class UserProductImple implements UserProductService{

    @Autowired
    ProductRepo productRepo;

    @Override
    public String addProduct(Product product) {   
        productRepo.save(product);
        // System.out.println();
        return "Product added Successfully!";
    }
    
}
