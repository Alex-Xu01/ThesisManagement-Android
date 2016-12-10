package edu.gisi.magic.thesismanagement.tools;

public class UrlTool {
    private static final String IP = "http://192.168.1.9:8080";
    private static final String urlPre = IP + "/GTMS";

    public static final String URL_USER_REGISTER = createUrl("/user/register");
    public static final String URL_USER_LOGIN = createUrl("/user/login");

    public static final String URL_TYPE_QUERY_ALL = createUrl("/type/queryAll");
    public static final String URL_TYPE_QUERY_ALL_ACTIVITY = createUrl("/type/queryAllActivity");

    public static final String URL_ORDER_ADD = createUrl("/order/addOrder");
    public static final String URL_ORDER_QUERY_BY_USERNAME = createUrl("/order/queryByUserId");

    private static String createUrl(String urlInfo) {
        return String.format("%s%s", urlPre, urlInfo);
    }

    public static String createImageUrl(String imageName) {
        return String.format("%s/images/%s", urlPre, imageName);
    }

}
