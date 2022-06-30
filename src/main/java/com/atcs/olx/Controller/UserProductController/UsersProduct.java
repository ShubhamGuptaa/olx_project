package com.atcs.olx.Controller.UserProductController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        }
        msg = " email not found!";
       return new ResponseEntity<List<Product>>(HttpStatus.BAD_GATEWAY);

    }

    @GetMapping("/user/get/contact_details_of_any_product/{id}")
    public ResponseEntity<Contact> contactDetails(@PathVariable("id") long id){
        List<Product> products = userProductService.getAllProducts();
        for(Product a: products){
            if(a.getId().equals(id)){
               return new ResponseEntity<Contact>(userProductService.getContactDetails(a),HttpStatus.OK);

            }
        }
        msg = "Id is not associated with any product!";
        return new ResponseEntity<Contact>(HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/user/list_all_product")
    public ResponseEntity<List<Product>> getAllProduct(){
       List<Product> prod =  userProductService.getAllProducts();
       if (prod != null){
        return new ResponseEntity<List<Product>>(prod,HttpStatus.OK);
       }
       else{
        msg = "No product available ";
        return new ResponseEntity<List<Product>>(HttpStatus.BAD_GATEWAY);
       }
    }

    @PostMapping
    public ResponseEntity<String> addContactByUserProduct(@RequestBody Contact contact){
        msg = "Product added successfully!";
        return new ResponseEntity<String>(msg,HttpStatus.OK);
    }

    @DeleteMapping("/delete_product_by_id/{id}")
    public  ResponseEntity<String> deleteProductById(@PathVariable("id") long id){

        msg = "Product Deleted Successfully!";
        return new ResponseEntity<String>(msg,HttpStatus.OK) ;

    }

    @DeleteMapping("/user/delete_product/{id}")
    public ResponseEntity<String> deleteProdByUser(@PathVariable("id") long id, @RequestBody ListProductByEmail email){
        List<Register> users = serviceUsers.getAllUsers();
        for(Register r: users){
            if(r.getEmail().equals(email.getEmail())){
                List<Product> prod = userProductService.listProductByUser(r);
               for(Product p:prod){
                    if(p.getId().equals(id)){
                        userProductService.deleteProductById(id);
                        msg = "Product deleted successfully!";
                        return new ResponseEntity<String>(msg,HttpStatus.OK) ;
                    }
               }
                msg = "Invalid Product Id!";
                return new ResponseEntity<String>(msg,HttpStatus.BAD_GATEWAY);
            }

                }
            
            msg = "Invalid User!";
            return new ResponseEntity<String>(msg,HttpStatus.BAD_GATEWAY) ;
    }

}
