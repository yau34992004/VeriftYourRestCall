package com.test.verifyyourrestcall;

/**
 * Created by rex.yau on 5/15/2015.
 */
public class ServerConfig {

    private static final String SCHEME = "http://";
    private static final String END_POINT = "127.0.0.1";
    public static final String PATH_BINARY = "/binary";
    public static final String PATH_JSON = "/json";
    public static final int PORT = 5000;

    private ServerConfig() {
    }

    public static String getBinaryUrl() {
        return SCHEME + END_POINT + ":" + PORT + PATH_BINARY;
    }

    public static String getJSONUrl() {
        return SCHEME + END_POINT + ":" + PORT + PATH_JSON;
    }

}
