package service;

import dataBase.DataBase;
import model.User;
import org.telegram.telegrambots.meta.api.objects.Message;

public class UserService extends DataBase {

     public void saveUserToData(Message message){
         String chatId = String.valueOf(message.getChat().getId());
         String firstName = message.getContact().getFirstName();
         String lastName = message.getContact().getLastName();
         String phoneNumber = message.getContact().getPhoneNumber();
         String username = message.getFrom().getUserName();
         User user = new User(null,firstName,lastName,phoneNumber,username,chatId);

         saveUserToDataBase(user);


     }

}
