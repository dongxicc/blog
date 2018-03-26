package com.cc.blog.util;

/**
 * @author CC
 * @description praseDouble
 * @create 2017-12-21 16:45
 **/
public class PraseUtil {
    public static Double praseDouble(String s) {
        if (s != null) {
            try {
                return Double.valueOf(s);
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Integer praseInteger(String s) {
        if (s != null) {
            try {
                return Integer.valueOf(s);
            } catch (NumberFormatException e) {
                return null;
            }

        } else {
            return null;
        }
    }

    public static String praseString(Object s) {
        if (s != null) {
            return s.toString();
        } else {
            return null;
        }
    }

}