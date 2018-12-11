package com.ptteng.util.API;

import com.aliyun.oss.model.OSSObjectSummary;
import com.qcloud.cos.model.COSObjectSummary;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class PictureMoveUtil {

    static Logger logger = Logger.getLogger(PictureMoveUtil.class);

    @Autowired
    private COSClientUtil cosClientUtil;
    @Autowired
    private OSSClientUtil ossClientUtil;

    //将腾讯云的图片迁移到阿里云
    public void moveFromCOSToOSS() {

        List<COSObjectSummary> list = cosClientUtil.getLIst();
//    遍历所有的腾讯云图片文件，并将下载在本地的图片文件上传到阿里云
        for (COSObjectSummary s : list) {
            String key = s.getKey();
            File file = new File("F:\\picture\\" + key);
            ossClientUtil.uploadPicture(key, file);
        }
    }


    //将阿里云存储的数据迁移至腾讯云存储
    public void moveFromOSSToCOS() {
        List<OSSObjectSummary> list = ossClientUtil.getList();
        //遍历数组
        for (OSSObjectSummary c : list) {
            String key = c.getKey();
            File file = new File("F:\\picture\\" + key);
            //上传文件
            cosClientUtil.uploadFile(key, file);
        }
    }
}
