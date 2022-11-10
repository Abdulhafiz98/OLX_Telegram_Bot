package model;

import lombok.*;
import model.base.Base;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class User extends Base {
    private List<Product> bin ;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;
    private String chatId;

    @Override
    public String toString() {
        return "   Foydalanuvchi Malumotlari \uD83D\uDCD1 "+ "\n" +
                " Ism :  " + firstName + '\n' +
                " Familiya :  " + lastName + '\n' +
                " Telefon raqam :  " + phoneNumber + '\n' +
                " Username :  " + username + '\n' +
                " ID  :  " + chatId + '\n';
    }
}
