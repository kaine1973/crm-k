package com.shsxt.crm.utils;


import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncyptUtil {

    public static String encypt(String password) {
        String encypt = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(password.getBytes());
            encypt = Base64.encodeBase64String(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encypt;
    }

    public static void main(String[] args) {
        System.out.println(encypt("123"));
    }

    public static String idEncode(String id) {
        String encodeString = org.apache.commons.codec.binary.Base64.encodeBase64String(id.getBytes());
        String timeString = org.apache.commons.codec.binary.Base64.encodeBase64String((System.currentTimeMillis() + "").getBytes());
        String encode = timeString + timeString.substring(4, 8) + encodeString;
        encode = new StringBuilder(encode).reverse().toString();

        return encode.replaceAll("=", "#");
    }

    public static String idDecode(String id) {
        String str = "";
        try {
            id = URLDecoder.decode(id, "utf-8");

            String encode = id.replaceAll("#", "=");

            encode = new StringBuilder(encode).reverse().toString();
            /**
             * 原先encode = 系统时间转换后的字符串 + 字符串的4位子字符串 + userId
             *    样例        xxxxxxxxxxxx==   +   4位数(xxxx)     + userId
             * 由于系统时间转换后的字符串必以‘==’字符结尾，所以可以算出 userId的位置 = encode.indexOf("==")  + 6;
             */

            int userIdIndex = encode.indexOf("==") + 6;

            String userIdCode = encode.substring(userIdIndex);

            str = new String(Base64.decodeBase64(userIdCode.getBytes()));

            userIdCode = URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

}
