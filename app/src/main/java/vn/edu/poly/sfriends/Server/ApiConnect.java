package vn.edu.poly.sfriends.Server;

public class ApiConnect {
    //API USER
    public static final String URL_REGISTER(String url) {
        return url + "/api/register";
    }
    public static final String URL_GET_USER_INFOR(String url) {
        return url + "/api/nks/user";
    }
    public static final String URL_SIGNIN(String url) {
        return url + "/oauth/token";
    }
    public static final String URL_UPDATE_USER_INFOR(String url) {
        return url + "/api/nks/users/updateInfo";
    }
    public static final String URL_UPDATE_USER_AVATAR(String url) {
        return url + "/api/nks/users/updateAvatar";
    }
    public static final String URL_CONNECT_AVATAR( String url) {
        return url + "/storage/app/public/";
    }
    //
    public static final String URL_GET_NEAR_PLACE( String url) {
        return url + "/api/nks/map/locations";
    }
}
