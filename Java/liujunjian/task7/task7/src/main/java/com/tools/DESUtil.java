package com.tools;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

public class DESUtil {
    //密钥，位数为8的整数倍
//    @Value("${key}")
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    public String encrypt(String datasource) {
        //DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        try {
            //创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            //创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESUtil");
            //将DESKeySpec对象转换成SecretKey对象
            SecretKey secretKey = keyFactory.generateSecret(desKey);
            //创建Cipher对象，Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DESUtil");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
            //正式执行加密操作
            byte[] result = cipher.doFinal(datasource.getBytes());
            //用Base64把byte[]转化为String
            return Base64.encodeBase64String(result);
        } catch (Throwable e) {
            return null;
        }
    }

    public String decrypt(String src) {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        try {
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESUtil");
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey secretKey = keyFactory.generateSecret(desKey);
            // 创建Cipher对象，Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DESUtil");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, secretKey, random);
            // 真正开始解密操作,用Base64把String转化为byte[]
            byte[] result = cipher.doFinal(Base64.decodeBase64(src));
            //返回byte[]对应的String
            return new String(result);
        } catch (Throwable e) {
            return null;
        }
    }
}

