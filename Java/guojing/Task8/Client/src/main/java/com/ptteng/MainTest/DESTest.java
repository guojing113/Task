//package com.ptteng.MainTest;
//
//
//import com.ptteng.entity.User;
//import com.ptteng.service.UserService;
//import com.ptteng.util.encryption.DESUtil;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.xml.crypto.dsig.DigestMethod;
//import java.io.UnsupportedEncodingException;
//
//public class DESTest {
//
//
//
//    public static void main(String[] args) throws UnsupportedEncodingException {
//
////        模拟登录状态
//        User user=new User();
//        user.setId(6L);
//        user.setName("郭靖");
//        user.setPassword("123456");
////        登录信息加盐
//        user.setPassword(DigestUtils.md5Hex(user.getPassword()+"guojing"));
////       判断登录状态
////        Boolean loginType=userService.login(user.getName(),user.getPassword());
////        System.out.println(loginType);
//
//        DESUtil desUtil=new DESUtil();
//        user.setUpdateAt(System.currentTimeMillis());
//        System.out.println(System.currentTimeMillis());
//        String str1 = desUtil.encryptFromLong(user.getUpdateAt().longValue());
////        String str2 = desUtil.encryptFromLong(user.getId().longValue());
//        System.out.println(str1);
//        String token = desUtil.encrypt(str1);
////       打印加密后的cookie
//        System.out.println("加密后的"+token);
////        cookie解密
//        String str=desUtil.decrypt(token);
////       打印解密后的token
//        System.out.println("解密后的"+str);
//
//        Long str6=desUtil.decryptToLong(str);
//
//        System.out.println(str6);
//
////split方法是切割字符串的方法，得到的结果是字符串数组。
////        String str45 = str.split("\\|")[1];
//
////        System.out.println(str45);
//
//
//
//
//
//    }
//
//
//
//}
