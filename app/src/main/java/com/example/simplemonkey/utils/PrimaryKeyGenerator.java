package com.example.simplemonkey.utils;

public class PrimaryKeyGenerator {
    public static int generate(int uid) {
        String strPrimaryKey = "" + uid + System.currentTimeMillis() / 1000L;;
        return Integer.parseInt(strPrimaryKey);
    }
}
