package com.aditya.repsfinder.constants;

public class AppAPI {

    public static final String SERVER_BASE_HOME = "https://api.github.com/";

    public class Headers {
        public static final String ACCEPT_KEY = "Accept";
        public static final String ACCEPT_VALUE = "application/vnd.myrefers.v0+json";
        public static final String ACCEPT_JSON = "application/json";
        public static final String AUTHORIZATION_KEY = "Authorization";
        public static final String CAN_RENDER_HTML = "X-HTML-CanRender";
        public static final String CONTENT_TYPE = "Content-Type";
    }

    public static final String SLASH = "/";
    public static final String OPEN_REQUEST = SERVER_BASE_HOME + "repos/";
    public static final String CLOSED_REQUEST = SERVER_BASE_HOME + "repos/";
    public static final String OPEN_STATE = "/issues?state=open";
    public static final String CLOSED_STATE = "/issues?state=closed";

}
