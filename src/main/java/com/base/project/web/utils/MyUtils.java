package com.base.project.web.utils;

import java.util.Random;

/**
 * Created by huang on 4/24/15.
 */
public class MyUtils {

    public static String generateTicket(String account) {
        return "";
    }

    public static String getNonceTicket() {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < 6; i ++) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();

    }
}
