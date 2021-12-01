package com.website.message.request;

import javax.persistence.Column;

public class SignUpForm {

  //  @Size(min = 3, max = 50)
    private String name;

  //  @Size(min = 3, max = 50)
    private String username;

    @Column(name = "address", length = 120)
    private String address;

   // @Size(max = 60)
  //  @Email
    private String email;

  //  @Size(min = 3, max = 50)
    private String mobile;

    private String[] roles;

  //  @Size(min = 6, max = 40)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
