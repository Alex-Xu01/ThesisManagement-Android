package edu.gisi.magic.thesismanagement.entity;

/**
 * Created by xulih on 2016/12/24.
 */

public class Teacher {

    private Dep dep;

    private String email;

    private int id;

    private String name;

    private String phone;

    private String specialty;

    private int state;

    private String title;

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

    public void setSpecialty(String specialty){
        this.specialty = specialty;
    }

    public String getSpecialty(){
        return this.specialty;
    }

    public void setState(int state){
        this.state = state;
    }

    public int getState(){
        return this.state;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

}
