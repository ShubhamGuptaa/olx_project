package com.atcs.olx.Service;

import org.springframework.stereotype.Service;

import com.atcs.olx.Entity.Register;

@Service
public interface ServiceUsers {
    public String registerUsers(Register register);
    public boolean  isValidPassword(String password);
    
}
