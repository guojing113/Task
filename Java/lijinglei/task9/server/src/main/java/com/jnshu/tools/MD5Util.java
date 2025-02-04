package com.jnshu.tools;




import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class MD5Util {

    /**
     * 普通MD5
     * @author daniel
     * @time 2016-6-11 下午8:00:28
     * @param
     * @return
     */
    public static String MD5(String input) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return "check jdk";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = input.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }




    /**
     * 加盐MD5
     * @author daniel
     * @time 2016-6-11 下午8:45:04
     * @param password
     * @return
     */
    public static String generate(String password,String salt) {
        password = MD5(password + salt);
        return password;
    }

    /**
     *
     * 盐值
     */
    public static String salt(){
        String salt =  UUID.randomUUID().toString().replaceAll("-", "");
        return salt;
    }

}
