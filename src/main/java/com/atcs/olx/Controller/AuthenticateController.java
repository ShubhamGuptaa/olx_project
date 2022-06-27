package com.atcs.olx.Controller;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.atcs.olx.Entity.Forgot;
import com.atcs.olx.Entity.Register;

import com.atcs.olx.Service.ServiceUsers;
import com.google.common.hash.Hashing;


@RestController
@RequestMapping("/olx")
public class AuthenticateController {

    @Autowired
    ServiceUsers serviceUsers;
   
    String email_regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"; 
    Pattern email = Pattern.compile(email_regex); 
    String msg = "";

    // Register New User
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Register register){
        
        Matcher email_matcher = email.matcher(register.getEmail());

        if(email_matcher.matches() == false){
            msg = "Email is not valid! (e.g: email@email.com)";
            return new ResponseEntity<String>(msg,HttpStatus.BAD_GATEWAY);
        }
        else if(serviceUsers.isValidPassword(register.getPassword()) == false){
            msg = "Password is not valid! (e.g: 8 characters length, 2 letters in Upper Case, 1 Special Character (!@#$&*), 2 numerals (0-9), 3 letters in Lower Case )";
            return new ResponseEntity<String>(msg,HttpStatus.BAD_GATEWAY);
        }
        else if(register.getPhone_number().length() < 10){
            msg = "Incorrect phone number!";
            return new ResponseEntity<String>(msg,HttpStatus.BAD_GATEWAY);
        }
        else{
            register.setPassword(Hashing.sha256()
            .hashString(register.getPassword(), StandardCharsets.UTF_8)
            .toString());

            serviceUsers.registerUsers(register);
            msg = "Registration Successfull!";
            return  new ResponseEntity<String>(msg,HttpStatus.OK);
        }   
    }

    // If User Forgot their Password
    @PutMapping("/forgot_password")
    public ResponseEntity<String> forgot_password(@RequestBody Forgot forgot){
        Matcher email_matcher = email.matcher(forgot.getEmail());
        if(email_matcher.matches() == false){
            msg = "Email is not valid! (e.g: email@email.com)";
            return new ResponseEntity<String>(msg,HttpStatus.BAD_GATEWAY);
        }
        else if(serviceUsers.isValidPassword(forgot.getPassword()) == false){
            msg = "Password is not valid! (e.g: 8 characters length, 2 letters in Upper Case, 1 Special Character (!@#$&*), 2 numerals (0-9), 3 letters in Lower Case )";
            return new ResponseEntity<String>(msg,HttpStatus.BAD_GATEWAY);
        }
        else {
            List<Register> allUsers = serviceUsers.getAllUsers();
            for(Register r: allUsers){
                if(r.getEmail().equals(forgot.getEmail())){
                   Long id = r.getId();
                   Register getUser = serviceUsers.getUserById(id);
                   getUser.setPassword(Hashing.sha256()
                   .hashString(forgot.getPassword(), StandardCharsets.UTF_8)
                   .toString());
                   serviceUsers.updateUserPasswordById(getUser);
                   msg = "Password reset Successfull!";
                   return new ResponseEntity<String>(msg,HttpStatus.OK);
                }
            }
            msg = "No account with this email id!";
            return new ResponseEntity<String>(msg,HttpStatus.BAD_GATEWAY);           
        }
     
    }
}


