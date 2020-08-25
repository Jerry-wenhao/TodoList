package com.xiawenhao.todolist;

import java.security.MessageDigest;

public class MD5Utils {
    public static String digest(String password) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(password.getBytes());
            for (byte b : bytes) {
                int c = b & 0xff;
                String result = Integer.toHexString(c);
                if (result.length() < 2) {
                    sb.append(0);
                }
                sb.append(result);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return password;
    }
}
