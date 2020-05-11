package com.ciisa.simplemonkey.utils;

public class PrimaryKeyGenerator {
    public static long generate(long uid) {
        String strPrimaryKey = "" + uid + System.currentTimeMillis() / 1000L;;
        return Long.parseLong(strPrimaryKey);
    }
}
