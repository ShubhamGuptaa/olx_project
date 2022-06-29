package com.atcs.olx.ServiceImple.UserProductImple;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.atcs.olx.Entity.Authenticate.Register;
import com.atcs.olx.Entity.Products.Contact;
import com.atcs.olx.Entity.Products.Product;
import com.atcs.olx.Repository.AuthenticateRepo.RegisterRepo;
import com.atcs.olx.Repository.ProductRepo.ProductRepo;
import com.atcs.olx.Service.UsersProductService.UserProductService;

@Component
public class UserProductImple implements UserProductService{

    @Autowired
    ProductRepo productRepo;

    @Autowired
    RegisterRepo registerRepo;

    @Override
    public String addProduct(Product product) {   
        productRepo.save(product);
        // System.out.println();
        return "Product added Successfully!";
    }

    @Override
    public List<Product> listProductByUser(Register user){    
        List<Product> newUserProduct = new ArrayList<Product>();     
        newUserProduct = user.getProduct();
        System.out.println(newUserProduct);
        return newUserProduct;
    }

    @Override
    public ResponseEntity<String> addContactByUserProduct(Contact contact) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
