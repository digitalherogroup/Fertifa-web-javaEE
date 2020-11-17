package com.fertifa.app.utils;


import java.util.Base64;

public class DecodingEncoding {

    public static String EncodePassword(String password) {
        Base64.Encoder encoder = Base64.getMimeEncoder();
        String str = encoder.encodeToString(password.getBytes());
        System.out.println(str);
        return str;
    }

    public static String EncodePassword(String[] passwords) {
        Base64.Encoder encoder = Base64.getMimeEncoder();
        String str = encoder.encodeToString(passwords[0].getBytes());
        System.out.println(str);
        return str;
    }
}
