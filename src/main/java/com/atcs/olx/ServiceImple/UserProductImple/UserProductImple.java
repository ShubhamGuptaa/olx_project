package com.atcs.olx.ServiceImple.UserProductImple;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println(newUserProduct.toString());
        return newUserProduct;
    }

    @Override
    public Contact getContactDetails(Product prod){
        String email = prod.getRegister().getEmail();
        String firstname  = prod.getRegister().getFirstname();
        String lastname  = prod.getRegister().getLastname();
        String phone_number  = prod.getRegister().getPhone_number();
        Contact c = new Contact(firstname,lastname,email,phone_number);
        return c;
    }

    @Override
    public List<Product> getAllProducts(){
       return productRepo.findAll();
    }

    
    
}
