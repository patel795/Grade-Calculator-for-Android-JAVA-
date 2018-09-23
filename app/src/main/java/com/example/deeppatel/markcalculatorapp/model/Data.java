package com.example.deeppatel.markcalculatorapp.model;

/**
 * Created by Deep Patel on 2017-12-23.
 */

public class Data {
    long id;
    String password;

    public Data(){

    }
    public Data(int id, String password){
        this.id = id;
        this.password = password;
    }

    public long getID(){
        return id;
    }

    public void setID(long id){
        this.id = id;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString(){
        return password;
    }
}

