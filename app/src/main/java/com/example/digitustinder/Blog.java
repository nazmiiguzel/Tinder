package com.example.digitustinder;

class Blog {
    private String name;
    private String imaage_uri;

    public Blog(String name, String imaage_uri) {
        this.name = name;
        this.imaage_uri = imaage_uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImaage_uri() {
        return imaage_uri;
    }

    public void setImaage_uri(String imaage_uri) {
        this.imaage_uri = imaage_uri;
    }
}
