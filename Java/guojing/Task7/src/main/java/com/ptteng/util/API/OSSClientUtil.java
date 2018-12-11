package com.ptteng.util.API;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.List;


public class OSSClientUtil {

    private static Logger logger = Logger.getLogger(OSSClientUtil.class);
    private String bucketName;
    private OSSClient ossClient;

    public OSSClientUtil(String bucketName, OSSClient ossClient) {
        this.bucketName = bucketName;
        this.ossClient = ossClient;
    }

    public URL uploadPicture(String key, File file) {
        // 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
        ossClient.putObject(bucketName, key, file);
        Date expiration = new Date(new Date().getTime() + 3600 * 1000 * 24 * 365);// 生成 URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        return url;
    }

    public URL uploadInputStream(String key, byte[] file) throws FileNotFoundException {
        System.out.println("是否进入方法中");
        System.out.println("测试类加载" + ossClient);
        InputStream inputStream = new ByteArrayInputStream(file);
        ossClient.putObject(bucketName, key, inputStream);
        // 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
        Date expiration = new Date(new Date().getTime() + 3600 * 1000 * 24 * 365);// 生成 URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        return url;
    }

    private boolean isImage(InputStream imageFile) {
        Image img = null;
        try {
            img = ImageIO.read(imageFile);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            img = null;
        }
    }


    //    获取文件列表并将文件下载到本地文件夹中
    public List<OSSObjectSummary> getList() {
        ObjectListing objectListing = ossClient.listObjects(bucketName);
        List<OSSObjectSummary> list = objectListing.getObjectSummaries();
        for (OSSObjectSummary o : list) {
            String key = o.getKey();
            System.out.println("存储时名字" + key);
            String path = "F://picture//" + key;
            File file = new File(path);
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
            ossClient.getObject(getObjectRequest, file);
//    关闭ossClient
//        ossClient.shutdown();
        }
        return list;
    }


}
