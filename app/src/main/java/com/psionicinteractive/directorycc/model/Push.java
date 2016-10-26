package com.psionicinteractive.directorycc.model;

/**
 * Created by PSIONIC on 10/26/2016.
 */

public class Push {

    //private variables
    int id;
    String title;
    String message;

    // Empty constructor
    public Push(){

    }
    // constructor
    public Push(int id, String title, String message){
        this.id = id;
        this.title = title;
        this.message = message;
    }

    // constructor
    public Push(String title, String message){
        this.title = title;
        this.message = message;
    }
    // getting ID
    public int getID(){
        return this.id;
    }

    // setting id
    public void setID(int id){
        this.id = id;
    }

    // getting title
    public String getTitle(){
        return this.title;
    }

    // setting title
    public void setTitle(String title){
        this.title = title;
    }

    // getting message
    public String getMessage(){
        return this.message;
    }

    // setting message
    public void setMessage(String message){
        this.message = message;
    }
}