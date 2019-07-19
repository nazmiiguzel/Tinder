package com.example.digitustinder;

public class Model {

    public String e_mail;
    public String imaage_uri;


    public Model(String e_mail, String imaage_uri) {
        this.e_mail = e_mail;
        this.imaage_uri = imaage_uri;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getImaage_uri() {
        return imaage_uri;
    }

    public void setImaage_uri(String imaage_uri) {
        this.imaage_uri = imaage_uri;
    }
}
