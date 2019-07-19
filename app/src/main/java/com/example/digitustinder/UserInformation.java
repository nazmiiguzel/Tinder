package com.example.digitustinder;

import android.net.Uri;

public class UserInformation {

    String uid, e_mail , namee, date , city , passwordd, imaage_uri;

    public UserInformation(String uid, String e_mail, String namee, String date, String city, String passwordd, String imaage_uri) {
        this.uid = uid;
        this.e_mail = e_mail;
        this.namee = namee;
        this.date = date;
        this.city = city;
        this.passwordd = passwordd;
        this.imaage_uri = imaage_uri;
    }

    public UserInformation() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getNamee() {
        return namee;
    }

    public void setNamee(String namee) {
        this.namee = namee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPasswordd() {
        return passwordd;
    }

    public void setPasswordd(String passwordd) {
        this.passwordd = passwordd;
    }

    public String getImaage_uri() {
        return imaage_uri;
    }

    public void setImaage_uri(String imaage_uri) {
        this.imaage_uri = imaage_uri;
    }
}
