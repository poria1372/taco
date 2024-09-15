package com.example.taco.security;

import com.example.taco.UserObj;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;
    public UserObj toUser(PasswordEncoder passwordEncoder) {
        UserObj obj = new UserObj();
        obj.setUsername(username);
        obj.setPassword(passwordEncoder.encode(password));
        obj.setFullname(fullname);
        obj.setStreet(street);
        obj.setCity(city);
        obj.setState(state);
        obj.setZip(zip);
        obj.setPhoneNumber(phone);
        return obj;
    }
}

