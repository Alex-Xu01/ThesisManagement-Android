package edu.gisi.magic.thesismanagement.config;

/**
 * Created by AlexXu on 2016/12/9.
 */

public class Urls {

    private static final String IP = "http://192.168.1.6:8080";

    public static final String URL_GENERAL = createUrl("/queryNumbers");

    public static final String URL_USER_LOGIN = createUrl("/LoginAct");
    public static final String URL_USER_INFO = createUrl("/showStudentInfo");
    public static final String URL_USER_CHANGE_INFO = createUrl("/editStudentAct");
    public static final String URL_USER_CHANGE_PASSWORD = createUrl("/editAccountAct");

    public static final String URL_MY_THESIS = createUrl("/showChoose");
    public static final String URL_THESIS_ALL = createUrl("/queryAllPassPapers");
    public static final String URL_THESIS = createUrl("/choosePaperPre");
    public static final String URL_THESIS_ADD = createUrl("/choosePaperAct");

    private static String createUrl(String urlInfo) {
        return String.format("%s%s", IP, urlInfo);
    }

}