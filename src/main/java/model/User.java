package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import model.base.Base;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ToString(callSuper = true)
public class User extends Base{
    private List<Product> bin ;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;
    private String chatId;

    public User(String firstName, String chatId, String lastName, String phoneNumber, String username) {
        super();
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
    }

    public List<Product> getBin() {
        return bin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getChatId() {
        return chatId;
    }

    public void setBin(List<Product> bin) {
        this.bin = bin;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    @Override
    public String toString() {
        return " Foydalanuvchi Malumotlari \uD83D\uDCD1 "+ "\n" +
                " Ism :  " + firstName + '\n' +
                " Familiya :  " + lastName + '\n' +
                " Telefon raqam :  " + phoneNumber + '\n' +
                " Username :  " + username + '\n' +
                " " + chatId + '\n' +
                '}';
    }
}
