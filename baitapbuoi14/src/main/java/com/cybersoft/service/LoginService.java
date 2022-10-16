package com.cybersoft.service;

import com.cybersoft.model.UserModel;
import com.cybersoft.repository.LoginRepository;

import java.util.List;

public class LoginService {

    LoginRepository loginRepository = new LoginRepository();

    public boolean checkLogin(String email, String password){
       List<UserModel> list = loginRepository.getUserByEmailAndPassword(email,password);
         if (list.size()>0){
             return true;
         } else {
             return false;
         }
    }
}
