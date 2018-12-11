//package com.ptteng.MainTest;
//
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//public class MD5Test {
//
//    public static void main(String[] args) {
//        String input="9";
//        MessageDigest md5 = null;
//        try {
////         获取类实例
//            md5 = MessageDigest.getInstance("MD5");
//        } catch (NoSuchAlgorithmException e) {
//            System.out.println("check jdk");;
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("有问题呀，什么问题呢？我不告诉你");
//        }
////        input.toCharArray是将字符串对象中的字符转化为字符串数组
//        char[] charArray = input.toCharArray();
////      创建一个和字符串长度相同的字节数组
//        byte[] byteArray = new byte[charArray.length];
////将字符串数组转化为字节数组
//        for (int i = 0; i < charArray.length; i++){
//            byteArray[i] = (byte) charArray[i];
//            System.out.println("第一次的=="+ byteArray[i]);
//        }
////获取摘要信息的字节数组
//        byte[] md5Bytes = md5.digest(byteArray);
///*StringBuffer类中的方法主要偏重于对于字符串的变化，
//例如追加、插入和删除等，这个也是StringBuffer和String类的主要区别*/
//        StringBuffer hexValue = new StringBuffer();
//
//        for (int i = 0; i < md5Bytes.length; i++) {
//            System.out.println("第二次==="+md5Bytes[i]);
//            int val = ((int) md5Bytes[i]) & 0xff;
//            System.out.println("第三次"+val);
//            if (val < 16){
////               append方法是在后面追加，内容改变
//                hexValue.append("0");}
////     Integer.toHexString此方法返回的字符串表示的无符号整数参数所表示的值以十六进制（基数为16）
//            hexValue.append(Integer.toHexString(val));
//            System.out.println("每一次");
//        }
//        System.out.println("第四次"+hexValue.toString());
//
//
//
//
//        byte a=8;
//        int b=((int)a)& 0xff;
//        System.out.println(b);
//
//
//
//
//    }
//
//
//}
