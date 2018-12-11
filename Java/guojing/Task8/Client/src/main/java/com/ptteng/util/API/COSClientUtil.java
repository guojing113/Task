package com.ptteng.util.API;

import com.qcloud.cos.COSClient;

import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.apache.log4j.Logger;


import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class COSClientUtil {

    static Logger logger = Logger.getLogger(COSClientUtil.class);

    private String secretId;
    private String secretKey;
    private String region;
    private String bucketName;
    private COSCredentials cred;
    private ClientConfig clientConfig;
    private COSClient cosClient;

    public COSClientUtil(String secretId, String secretKey, String region, String bucketName) {
        this.secretId = secretId;
        this.secretKey = secretKey;
        this.region = region;
        this.bucketName = bucketName;

        // 1 初始化用户身份信息(secretId, secretKey)
       cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
// clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        clientConfig = new ClientConfig(new Region(region));
        // 3 生成cos客户端
        cosClient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式

    }


//    public URL uploadFile() {

    public URL uploadFile(String key, File file) {
        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20M以下的文件使用该接口
// 大文件上传请参照 API 文档高级 API 上传
//        File file = new File("D:\\MyIdeaProject\\Task7\\src\\main\\webapp\\WEB-INF\\static\\images\\7n.jpg");
        // 指定要上传到 COS 上对象键
// 对象键（Key）是对象在存储桶中的唯一标识。例如，在对象的访问域名 `bucket1-1250000000.cos.ap-guangzhou.myqcloud.com/doc1/pic1.jpg` 中，对象键为 doc1/pic1.jpg, 详情参考 [对象键](https://cloud.tencent.com/document/product/436/13324)

//    key只要是能够作为一个唯一表示就可以了；这个可以研究一下
//        String key = "upload_single_demo_test.jpg";

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);

        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

        logger.info("腾讯云上传图片结果" + putObjectResult);

        Date expiration = new Date(new Date().getTime() + 5 * 60 * 10000);
        URL url = cosClient.generatePresignedUrl(bucketName, key, expiration);


        logger.info("生成的地址是" + url);

        return url;
    }


    public void downloadFile() {
        // 指定要下载到的本地路径
        File downFile = new File("src/test/resources/mydown.txt");
//        这个key是新加的
        String key = "upload_single_demo.txt";
// 指定要下载的文件所在的 bucket 和对象键
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
        ObjectMetadata downObjectMeta = cosClient.getObject(getObjectRequest, downFile);
    }


    public void deleteFile() {
        // 指定要删除的 bucket 和对象键
        String key = "upload_single_demo.txt";
        cosClient.deleteObject(bucketName, key);
    }

//  获取文件列表并将文件下载到本地文件夹中
    public List<COSObjectSummary> getLIst(){
        ObjectListing objectListing = cosClient.listObjects(bucketName);
        List<COSObjectSummary> list = objectListing.getObjectSummaries();

        for (COSObjectSummary s : list) {
            String key = s.getKey();
//            将存储的文件下载在本地
            cosClient.getObject(new GetObjectRequest(bucketName, key), new File("F:\\picture\\" + key));
        }
        cosClient.shutdown();
        return list;
    }


}
