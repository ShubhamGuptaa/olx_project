package com.atcs.olx.Service.ServiceAuthenticate;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atcs.olx.Entity.Authenticate.Admin_Register;
import com.atcs.olx.Entity.Authenticate.Register;

@Service
public interface ServiceAuthenticate {
    public String registerUsers(Register register);
    public List<Register> getAllUsers();
    public Register getUserById(long id);
    public Register updateUserPasswordById(Register register);
    public boolean  isValidPassword(String password);
    public boolean setUserLoggedIn(Register register);
    public boolean setUserLoggedOut(Register register);
    public String registerAdmin(Admin_Register admin_register);
    public boolean setAdminLoggedIn(Admin_Register admin_register);
    public boolean setAdminLoggedOut(Admin_Register admin_register);
    public List<Admin_Register> getAllAdmin();
    public Admin_Register getAdminById(long id);
    public Admin_Register updateAdminPasswordById(Admin_Register getAdmin);
    
    
}
