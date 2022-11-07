package service;

import dataBase.DataBase;
import model.User;
import service.baseSerivice.BaseService;

public class UserService implements BaseService<User>,DataBase<User> {
    @Override
    public void add(User o) {
        // logic here
    }

}
