package service;

import dataBase.DataBase;
import model.Product;
import model.User;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

public class UserService extends DataBase {

     public void saveUserToData(Message message){
         String chatId = String.valueOf(message.getChat().getId());
         String firstName = message.getContact().getFirstName();
         String lastName = message.getContact().getLastName();
         String phoneNumber = message.getContact().getPhoneNumber();
         String username = message.getFrom().getUserName();
         List<Product>list = new ArrayList<>();
         User user = new User(list,firstName,lastName,phoneNumber,username,chatId);

         saveUserToDataBase(user);


     }

}
