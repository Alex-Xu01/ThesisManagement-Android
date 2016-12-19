package edu.gisi.magic.thesismanagement.config;

/**
 * Created by AlexXu on 2016/12/9.
 */

public class Urls {

    private static final String IP = "http://192.168.1.12:8080/";
    private static final String urlPre = IP + "/GTMS";

    public static final String URL_USER_REGISTER = createUrl("/user/register");
    public static final String URL_USER_LOGIN = createUrl("/user/login");

    //TODO 实现了获取已审核通过以及未满的论文数之后修改
    public static final String URL_GENERAL = "http://www.mocky.io/v2/584c46bc120000b11f372b0d";

    public static final String URL_TYPE_QUERY_ALL = createUrl("/type/queryAll");
    public static final String URL_TYPE_QUERY_ALL_ACTIVITY = createUrl("/type/queryAllActivity");

    public static final String URL_THESIS = "http://www.mocky.io/v2/584e6a511200005f093949c3";
    public static final String URL_ORDER_ADD = createUrl("/order/addOrder");
    public static final String URL_ORDER_QUERY_BY_USERNAME = createUrl("/order/queryByUserId");

    public static final String URL_USER_INFO = "http://www.mocky.io/v2/584da4150f00009a1ad40fa8";

    private static String createUrl(String urlInfo) {
        return String.format("%s%s", urlPre, urlInfo);
    }

}
