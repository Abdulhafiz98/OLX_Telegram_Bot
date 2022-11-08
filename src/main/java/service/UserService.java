package service;

import dataBase.DataBase;
import model.User;

import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;

public class UserService extends DataBase {

     public void saveUserToData(Message message){
         User user = new User();
         user.setChatId(String.valueOf(message.getChat().getId()));
         user.setFirstName(message.getContact().getFirstName());
         user.setLastName(message.getContact().getLastName());
         user.setPhoneNumber(message.getContact().getPhoneNumber());
         user.setUsername(String.valueOf(message.getContact().getVCard()));

         saveUserToDataBase(user);


     }

}
