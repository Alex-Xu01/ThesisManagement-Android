package edu.gisi.magic.thesismanagement.entity;

/**
 * Created by AlexXu on 2016/12/11.
 */

public class ThesisResult {

    private String content;

    private int id;         //paperID

    private String title;   //标题

    private String subtitle;   //标题

    private String type;    //类型

    private String origin;  //来源

    private Teacher teacher;//教师类

    private String numbers;    //人数

    private Dep dep;        //部门类

    private int verifyState;//状态

    private boolean result;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public Dep getDep() {
        return dep;
    }

    public void setDep(Dep dep) {
        this.dep = dep;
    }

    public String getVerifyState() {
        if (verifyState == 0)
            return "未审核";
        else if (verifyState == 1)
            return "通过审核";
        else return "未通过审核";
    }

    public void setVerifyState(int verifyState) {
        this.verifyState = verifyState;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
