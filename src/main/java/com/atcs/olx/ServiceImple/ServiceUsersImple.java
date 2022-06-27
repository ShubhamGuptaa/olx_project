package com.atcs.olx.ServiceImple;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcs.olx.Entity.Register;
import com.atcs.olx.Repository.RegisterRepo;
import com.atcs.olx.Service.ServiceUsers;



@Component
public class ServiceUsersImple implements ServiceUsers{

    @Autowired
    RegisterRepo registerRepo;
    @Override
    public String registerUsers(Register register) {
      registerRepo.save(register);
      return "Registration Successfull!";
    }
    

    public  boolean
    isValidPassword(String password)
    {
        String regex_pass = "^(?=.*[0-9])"
        + "(?=.*[a-z])(?=.*[A-Z])"
        + "(?=.*[@#$%^&+=])"
        + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex_pass);

        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }


    @Override
    public List<Register> getAllUsers() {
        return  registerRepo.findAll();
    }
    
    @Override
    public Register getUserById(long id){
        return registerRepo.findById(id).get();
    }

    @Override
    public Register updateUserPasswordById(Register register){
        return registerRepo.save(register);
    }
}
