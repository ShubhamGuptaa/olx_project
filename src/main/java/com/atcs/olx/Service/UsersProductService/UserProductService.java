package com.atcs.olx.Service.UsersProductService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atcs.olx.Entity.Authenticate.Register;
import com.atcs.olx.Entity.Products.Contact;
import com.atcs.olx.Entity.Products.Product;

@Service
public interface UserProductService {
    public String addProduct(Product product);
    public List<Product> listProductByUser(Register register);
    public Contact getContactDetails(Product prod);
    public List<Product> getAllProducts();
}
