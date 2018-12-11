package com.ptteng.util.API;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class SendCloudUtil {

    private String url;
    private String apiUser;
    private String apiKey;
    private String fromName;
    private String from;
    private String subject;


    public SendCloudUtil(String url, String apiUser, String apiKey, String fromName, String from, String subject) {
        this.url = url;
        this.apiUser = apiUser;
        this.apiKey = apiKey;
        this.fromName = fromName;
        this.from = from;
        this.subject = subject;
    }


    public String SendCloud(String email) throws IOException {

        int randomCode = (int) ((Math.random() * 9 + 1) * 100000);
        String code = String.valueOf(randomCode);
        String html = "你的验证码是:" + code + ";打死不要告诉别人喔！";

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httPost = new HttpPost(url);

        List params = new ArrayList();
        // 您需要登录SendCloud创建API_USER，使用API_USER和API_KEY才可以进行邮件的发送。
        params.add(new BasicNameValuePair("apiUser", apiUser));
        params.add(new BasicNameValuePair("apiKey", apiKey));
        params.add(new BasicNameValuePair("from", from));
        params.add(new BasicNameValuePair("fromName", fromName));
        params.add(new BasicNameValuePair("to", email));
        params.add(new BasicNameValuePair("subject", subject));
        params.add(new BasicNameValuePair("html", html));


        httPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        // 请求
        HttpResponse response = httpclient.execute(httPost);
        // 处理响应
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
            // 读取xml文档
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        } else {
            System.err.println("error");
        }
        httPost.releaseConnection();

        return code;
    }


}





