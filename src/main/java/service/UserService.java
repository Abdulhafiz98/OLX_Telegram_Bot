package service;

import dataBase.DataBase;
import model.User;
import service.baseSerivice.BaseService;

import java.io.IOException;

public class UserService implements BaseService<User>{
    @Override
    public void add(User user) {
        DataBase.LIST_OF_USERS.add(user);
        DataBase.saveUserToDataBase(user);
    }

}
