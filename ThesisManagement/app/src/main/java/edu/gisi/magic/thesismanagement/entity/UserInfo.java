package edu.gisi.magic.thesismanagement.entity;

/**
 * Created by AlexXu on 2016/12/11.
 */
//实体类
public class UserInfo {
    private int id;
    private String username;
    private String password;
    private boolean result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
