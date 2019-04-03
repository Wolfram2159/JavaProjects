package com.company.substring;

public class Substring {
    public static int substring(String a, String b) {
        double condition = Math.ceil((double) b.length() / (double) a.length());
        //System.out.println(condition);
        for (int i = 0; i < condition; i++) {
            if (a.contains(b)) {
                return i + 1;
            }
            a += a;
        }
        return -1;
    }
}
