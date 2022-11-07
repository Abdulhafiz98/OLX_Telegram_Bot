package model;

import lombok.*;
import model.base.Base;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class User extends Base {
    List<Product> bin = new ArrayList<>();
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;

    public User(String firstName, String lastName, String phoneNumber, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
    }
}
