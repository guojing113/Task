package com.ptteng.util.API;


import com.ptteng.sdk.CCPRestSmsSDK;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class NoteVerifyUtil {

    private String serverIp;
    private String serverPort;
    private String accountSid;
    private String accountToken;
    private String appId;
    private String templateId;
    private String time;

    public NoteVerifyUtil(String serverIp, String serverPort, String accountSid,
                          String accountToken, String appId, String templateId,
                          String time) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.accountSid = accountSid;
        this.accountToken = accountToken;
        this.appId = appId;
        this.templateId = templateId;
        this.time = time;
    }

    //    获取六位数随机数
    public static String getSixRandom() {
        Random random = new Random();
        String sixRandom = random.nextInt(1000000) + "";
        int randLength = sixRandom.length();
        String code = null;
        if (randLength < 6) {
            for (int i = 1; i <= 6 - randLength; i++)
                code = String.valueOf(new Random().nextInt(10));
            sixRandom = code + sixRandom;
        }
        return sixRandom;
    }

    public String NoteVerify(String tel) {
        HashMap<String, Object> result = null;
//        生成六位随机验证码
        String VerifyCode = NoteVerifyUtil.getSixRandom();
//    初始化SDK
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
//    初始化服务器地址和代码
        restAPI.init(serverIp, serverPort);
//   初始化主账号和主账号令牌
        restAPI.setAccount(accountSid, accountToken);
//     初始化应用id
        restAPI.setAppId(appId);
//      调用发送模板短信的接口发送短信，主要内容是六位验证码
        result = restAPI.sendTemplateSMS(tel, templateId, new String[]{VerifyCode, time});
        System.out.println("SDKTestGetSubAccounts result=" + result);

        if ("000000".equals(result.get("statusCode"))) {
            //正常返回输出data包体信息（map）
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for (String key : keySet) {
                Object object = data.get(key);
                System.out.println(key + " = " + object);
            }
        } else {
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
        }

        return VerifyCode;
    }


}
