package edu.gisi.magic.thesismanagement.entity;

/**
 * Created by AlexXu on 2016/12/12.
 */

public class UserInfo {

    private int accountId;

    private Dep dep;

    private String email;

    private String gender;

    private int id;

    private String name;

    private String phone;

    private String picPath;

    private int state;

    private boolean result;

    public void setAccountId(int accountId){
        this.accountId = accountId;
    }
    public int getAccountId(){
        return this.accountId;
    }
    public void setDep(Dep dep){
        this.dep = dep;
    }
    public Dep getDep(){
        return this.dep;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public String getGender(){
        return this.gender;
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
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setPicPath(String picPath){
        this.picPath = picPath;
    }
    public String getPicPath(){
        return this.picPath;
    }
    public void setState(int state){
        this.state = state;
    }
    public int getState(){
        return this.state;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
