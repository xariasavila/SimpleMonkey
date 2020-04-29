package com.example.simplemonkey.utils;

public class PrimaryKeyGenerator {
    public static int generate() {
        int uid = 000;
        String strPrimaryKey = "" + uid + System.currentTimeMillis() / 1000L;;
        return Integer.parseInt(strPrimaryKey);
    }
}
