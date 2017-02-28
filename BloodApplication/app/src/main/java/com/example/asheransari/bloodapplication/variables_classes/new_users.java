package com.example.asheransari.bloodapplication.variables_classes;

/**
 * Created by asher.ansari on 2/28/2017.
 */

public class new_users {
    private String name;
    private String email;
    private String blood;
    private String password;

    public new_users(String name, String email, String blood, String password) {
        this.name = name;
        this.email = email;
        this.blood = blood;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBlood() {
        return blood;
    }

    public String getPassword() {
        return password;
    }
}
