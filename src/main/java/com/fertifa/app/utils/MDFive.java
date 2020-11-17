package com.fertifa.app.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MDFive {
    public static String getMD5(String data) throws NoSuchAlgorithmException
    {
        MessageDigest messageDigest= MessageDigest.getInstance("MD5");

        messageDigest.update(data.getBytes());
        byte[] digest=messageDigest.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(Integer.toHexString(b & 0xff));
        }
        return sb.toString();
    }
}
