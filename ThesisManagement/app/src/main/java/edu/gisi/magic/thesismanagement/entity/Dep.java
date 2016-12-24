package edu.gisi.magic.thesismanagement.entity;

/**
 * Created by xulih on 2016/12/24.
 */

public class Dep {
    private String address;

    private int id;

    private String name;

    private int state;

    private String telephone;

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setState(int state){
        this.state = state;
    }

    public int getState(){
        return this.state;
    }

    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    public String getTelephone(){
        return this.telephone;
    }
}
