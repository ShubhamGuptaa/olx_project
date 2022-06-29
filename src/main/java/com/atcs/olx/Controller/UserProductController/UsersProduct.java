package com.atcs.olx.Controller.UserProductController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atcs.olx.Entity.Authenticate.Register;
import com.atcs.olx.Entity.Products.Contact;
import com.atcs.olx.Entity.Products.ListProductByEmail;
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
        System.out.println(product.getId());
        System.out.println(product.getRegister());
        msg = "Product added successfully!";
        return new ResponseEntity<String>(msg,HttpStatus.OK);

    }

    @GetMapping("/user/list_product")
    public ResponseEntity<List<Product>> listProductByUser(@RequestBody ListProductByEmail listProductByEmail){
        List<Register> allUsers = serviceUsers.getAllUsers();
        for(Register a: allUsers){
            if(a.getEmail().equals(listProductByEmail.getEmail())){  
                return new ResponseEntity<List<Product>>(userProductService.listProductByUser(a),HttpStatus.OK);
            }
            else{
                msg = " email not found!";
                return new ResponseEntity<List<Product>>(HttpStatus.BAD_GATEWAY);
            }   
        }
        return null;

    }

    @PostMapping
    public ResponseEntity<String> addContactByUserProduct(@RequestBody Contact contact){
        
        msg = "Product added successfully!";

        return new ResponseEntity<String>(msg,HttpStatus.OK);
    }


}
