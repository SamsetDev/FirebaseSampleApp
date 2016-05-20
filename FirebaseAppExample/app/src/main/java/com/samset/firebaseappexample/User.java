package com.samset.firebaseappexample;

/**
 * Created by samset on 02/05/16.
 */
public  class User {
    public static String firstname;
    public static String lastname;
    public static String email;
    public static String contact;

    public  User()
    {

    }
    public User(String fname, String lanme, String mail, String con) {
      this.firstname =fname;
        this.lastname=lanme;
        this.email=mail;
        this.contact=con;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


}
