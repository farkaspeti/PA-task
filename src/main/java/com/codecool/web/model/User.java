package com.codecool.web.model;

import java.util.Objects;
import com.codecool.web.model.enums.UserType;

public final class User extends AbstractModel {

    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final UserType userType;
    
    public User(int id, String email, String password, String firstName, String lastName, UserType userType) {
        super(id);
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public UserType getUserType() {
        return userType;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
            Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, password);
    }
}
