package edu.gisi.magic.thesismanagement.entity;

/**
 * Created by AlexXu on 2016/12/11.
 */

public class AccountInfo {

    private int accountId;

    private boolean result;

    private int studentId;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
