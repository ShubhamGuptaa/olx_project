package com.atcs.olx.ServiceImple;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcs.olx.Entity.Admin_Register;
import com.atcs.olx.Entity.Register;
import com.atcs.olx.Repository.AdminRegisterRepo;
import com.atcs.olx.Repository.RegisterRepo;
import com.atcs.olx.Service.ServiceAuthenticate;



@Component
public class ServiceUsersImple implements ServiceAuthenticate{

    @Autowired
    RegisterRepo registerRepo;

    @Autowired
    AdminRegisterRepo adminRegisterRepo;


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


    @Override
    public boolean setUserLoggedIn(Register register) {
        register.setUserLoggedIn(true);
        registerRepo.save(register);
        return true;
    }
    @Override
    public boolean setUserLoggedOut(Register register) {
        register.setUserLoggedIn(false);
        registerRepo.save(register);
        return false;
    }


    @Override
    public String registerAdmin(Admin_Register admin_register) {
        adminRegisterRepo.save(admin_register);
        return "Admin Registration Successfull!";
    }


    @Override
    public boolean setAdminLoggedIn(Admin_Register admin_register) {
        admin_register.setUserLoggedIn(true);
        adminRegisterRepo.save(admin_register);
        return true;
    }

    @Override
    public boolean setAdminLoggedOut(Admin_Register admin_register) {
        admin_register.setUserLoggedIn(false);
        adminRegisterRepo.save(admin_register);
        return false;
    }

    @Override
    public List<Admin_Register> getAllAdmin() {
        return  adminRegisterRepo.findAll();
    }
    
    @Override
    public Admin_Register getAdminById(long id){
        return adminRegisterRepo.findById(id).get();
    }

    @Override
    public Admin_Register updateAdminPasswordById(Admin_Register admin_register){
        return adminRegisterRepo.save(admin_register);
    }


}
