package com.ptteng.util.API;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.BucketReferer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class LinkProtect {

    static Logger logger=Logger.getLogger(LinkProtect.class);
    @Autowired
    private OSSClient ossClient;

    public void setHotlinkProtection(Boolean flag, List<String> list, String bucketName) {
// list为Referer白名单(里面放的是允许的ip或域名)。Referer参数支持通配符星号（*）和问号（？）。
// 设置存储空间Referer列表。设为true表示Referer字段允许为空。
        BucketReferer br = new BucketReferer(flag, list);
        ossClient.setBucketReferer(bucketName, br);
// 关闭OSSClient。
//        ossClient.shutdown();
    }

    public List<String> getHotlinkProtection(String bucketName) {
// 获取存储空间Referer白名单列表。
        BucketReferer br = ossClient.getBucketReferer(bucketName);
        List<String> refererList = br.getRefererList();
//       遍历打印出所有的referer
        for (String referer : refererList) {
            logger.info("白名单允许的域名为"+referer);
        }
// 关闭OSSClient。
//        ossClient.shutdown();
        return refererList;
    }

    public void deleteHotlinkProtection(String bucketName) {
// 防盗链不能直接清空，需要新建一个允许空Referer的规则来覆盖之前的规则。
        BucketReferer br = new BucketReferer();
        ossClient.setBucketReferer(bucketName, br);
// 关闭OSSClient。
//    ossClient.shutdown();
    }


}
