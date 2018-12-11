package com.ptteng.util.API;


import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.GenericResult;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ProcessObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Formatter;

//阿里云的图片处理，处理后储存在本地(是可以处理后替换云上存储的图片的，就是再次上传一次)
@Component
public class ImageSample {

    @Autowired
    private OSSClient ossClient;

    String bucketName = "pttengguojing";

    String key = "upload_single_demo_test.jpg";

    public void photoShop() {

        try {
            // resize   图片缩放
            String style1 = "image/resize,m_fixed,w_100,h_100";
            GetObjectRequest request1 = new GetObjectRequest(bucketName, key);
            request1.setProcess(style1);

            ossClient.getObject(request1, new File("F:\\picture\\example-resize.jpg"));

            // crop   图片裁剪
            String style2 = "image/crop,w_100,h_100,x_100,y_100,r_1";
            GetObjectRequest request2 = new GetObjectRequest(bucketName, key);
            request2.setProcess(style2);

            ossClient.getObject(request2, new File("F:\\picture\\example-crop.jpg"));

            //   rotate    图片旋转
            String style3 = "image/rotate,90";
            GetObjectRequest request3 = new GetObjectRequest(bucketName, key);
            request3.setProcess(style3);

            ossClient.getObject(request3, new File("F:\\picture\\example-rotate.jpg"));

            // sharpen   锐化
            String style4 = "image/sharpen,100";
            GetObjectRequest request4 = new GetObjectRequest(bucketName, key);
            request4.setProcess(style4);

            ossClient.getObject(request4, new File("F:\\picture\\example-sharpen.jpg"));

            // add watermark into the image    添加水印
            String style5 = "image/watermark,appcss";
            GetObjectRequest request5 = new GetObjectRequest(bucketName, key);
            request5.setProcess(style5);

            ossClient.getObject(request5, new File("F:\\picture\\example-watermark.jpg"));

            // convert format    格式转化
            String style6 = "image/format,png";
            GetObjectRequest request6 = new GetObjectRequest(bucketName, key);
            request6.setProcess(style6);

            ossClient.getObject(request6, new File("F:\\picture\\example-format.png"));

            // image information
            String style7 = "image/info";
            GetObjectRequest request7 = new GetObjectRequest(bucketName, key);
            request7.setProcess(style7);

            ossClient.getObject(request7, new File("F:\\picture\\example-info.txt"));

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }




//            // 图片处理持久化 : 缩放
//            StringBuilder sbStyle = new StringBuilder();
//            Formatter styleFormatter = new Formatter(sbStyle);
//            String styleType = "image/resize,m_fixed,w_100,h_100";
//            String targetImage = "clipboard.png";
//            styleFormatter.format("%s|sys/saveas,o_%s,b_%s", styleType,
//                    BinaryUtil.toBase64String(targetImage.getBytes()),
//                    BinaryUtil.toBase64String(bucketName.getBytes()));
//            System.out.println(sbStyle.toString());
//            ProcessObjectRequest request = new ProcessObjectRequest(bucketName, sourceImage, sbStyle.toString());
//            GenericResult processResult = ossClient.processObject(request);
//            String json = IOUtils.readStreamAsString(processResult.getResponse().getContent(), "UTF-8");
//            processResult.getResponse().getContent().close();
//            System.out.println(json);
//
//            // 图片处理持久化 : 裁剪
//            sbStyle.delete(0, sbStyle.length());
//            styleType = "image/crop,w_100,h_100,x_100,y_100,r_1";
//            targetImage = "example-crop.png";
//            styleFormatter.format("%s|sys/saveas,o_%s,b_%s", styleType,
//                    BinaryUtil.toBase64String(targetImage.getBytes()),
//                    BinaryUtil.toBase64String(bucketName.getBytes()));
//            System.out.println(sbStyle.toString());
//            request = new ProcessObjectRequest(bucketName, sourceImage, sbStyle.toString());
//            processResult = ossClient.processObject(request);
//            json = IOUtils.readStreamAsString(processResult.getResponse().getContent(), "UTF-8");
//            processResult.getResponse().getContent().close();
//            System.out.println(json);
//
//            // 图片处理持久化 : 旋转
//            sbStyle.delete(0, sbStyle.length());
//            styleType = "image/rotate,90";
//            targetImage = "example-rotate.png";
//            styleFormatter.format("%s|sys/saveas,o_%s,b_%s", styleType,
//                    BinaryUtil.toBase64String(targetImage.getBytes()),
//                    BinaryUtil.toBase64String(bucketName.getBytes()));
//            request = new ProcessObjectRequest(bucketName, sourceImage, sbStyle.toString());
//            processResult = ossClient.processObject(request);
//            json = IOUtils.readStreamAsString(processResult.getResponse().getContent(), "UTF-8");
//            processResult.getResponse().getContent().close();
//            System.out.println(json);
//
//            // 图片处理持久化 : 锐化
//            sbStyle.delete(0, sbStyle.length());
//            styleType = "image/sharpen,100";
//            targetImage = "example-sharpen.png";
//            styleFormatter.format("%s|sys/saveas,o_%s,b_%s", styleType,
//                    BinaryUtil.toBase64String(targetImage.getBytes()),
//                    BinaryUtil.toBase64String(bucketName.getBytes()));
//            request = new ProcessObjectRequest(bucketName, sourceImage, sbStyle.toString());
//            processResult = ossClient.processObject(request);
//            json = IOUtils.readStreamAsString(processResult.getResponse().getContent(), "UTF-8");
//            processResult.getResponse().getContent().close();
//            System.out.println(json);
//
//            // 图片处理持久化 : 水印
//            sbStyle.delete(0, sbStyle.length());
//            styleType = "image/watermark,text_SGVsbG8g5Zu-54mH5pyN5YqhIQ";
//            targetImage = "example-watermark.png";
//            styleFormatter.format("%s|sys/saveas,o_%s,b_%s", styleType,
//                    BinaryUtil.toBase64String(targetImage.getBytes()),
//                    BinaryUtil.toBase64String(bucketName.getBytes()));
//            request = new ProcessObjectRequest(bucketName, sourceImage, sbStyle.toString());
//            processResult = ossClient.processObject(request);
//            json = IOUtils.readStreamAsString(processResult.getResponse().getContent(), "UTF-8");
//            processResult.getResponse().getContent().close();
//            System.out.println(json);
//
//            // 图片处理持久化 : 格式转换
//            sbStyle.delete(0, sbStyle.length());
//            styleType = "image/format,jpg";
//            targetImage = "example-formatconvert.jpg";
//            styleFormatter.format("%s|sys/saveas,o_%s,b_%s", styleType,
//                    BinaryUtil.toBase64String(targetImage.getBytes()),
//                    BinaryUtil.toBase64String(bucketName.getBytes()));
//            request = new ProcessObjectRequest(bucketName, sourceImage, sbStyle.toString());
//            processResult = ossClient.processObject(request);
//            json = IOUtils.readStreamAsString(processResult.getResponse().getContent(), "UTF-8");
//            processResult.getResponse().getContent().close();
//            System.out.println(json);
//
//        } catch (
//                Exception e) {
//            e.printStackTrace();
//        }




    }



}







