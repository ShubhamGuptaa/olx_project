package com.atcs.olx.Controller.UsersMessageController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atcs.olx.Controller.AuthenticateController.Authenticate;
import com.atcs.olx.Entity.Authenticate.Register;
import com.atcs.olx.Entity.Messages.Message;
import com.atcs.olx.Entity.Messages.UsersTable;
import com.atcs.olx.Repository.ProductRepo.ProductRepo;
import com.atcs.olx.Service.MessagesService.MessageService;
import com.atcs.olx.Service.ServiceAuthenticate.ServiceAuthenticate;
import com.atcs.olx.Service.UsersProductService.UserProductService;

@RestController
@RequestMapping("/olx")
public class UsersMessage {
    @Autowired
    ServiceAuthenticate serviceUsers;

    @Autowired
    UserProductService userProductService;

    @Autowired
    MessageService messageService;

    @Autowired
    ProductRepo productRepo;


    String msg = "";

    @GetMapping("/user/send_message_to" )
    public ResponseEntity<List<UsersTable>> sendMessage(){
        List<UsersTable> usersTable  = productRepo.sortusers();
        try{
            // List<Register> usersList = serviceUsers.getAllUsers();
            // if(Authenticate.checkUser == true){
            //     List<Register> usersList = serviceUsers.getAllUsers();
            //     // List<UsersTable> usersTable  =  messageService.showAllTables();
            //     for(Register r: usersList){
            //         if(message.getEmail().equals(r.getEmail())){
            //            System.out.println(usersTable.toString());
            //         }
            //     } 
            // }
            return new ResponseEntity<List<UsersTable>>(usersTable,HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e);
        }

       return null;

    }
}
