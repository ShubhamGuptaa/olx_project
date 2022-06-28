package com.atcs.olx.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atcs.olx.Entity.Register;

@Service
public interface ServiceUsers {
    public String registerUsers(Register register);
    public List<Register> getAllUsers();
    public Register getUserById(long id);
    public Register updateUserPasswordById(Register register);
    public boolean  isValidPassword(String password);
    public boolean setUserLoggedIn(Register register);
    public boolean setUserLoggedOut(Register register);
    
}
