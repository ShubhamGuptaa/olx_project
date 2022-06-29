package com.atcs.olx.Service.UsersProductService;

import org.springframework.stereotype.Service;

import com.atcs.olx.Entity.Products.Product;

@Service
public interface UserProductService {
    public String addProduct(Product product);
}
