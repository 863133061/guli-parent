package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    //上传文件到oss
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {

            InputStream inputStream = file.getInputStream();
            // 创建PutObject请求。
            //objectName 文件路径和文件名称
            //获取文件的真实值（如果真实镇 相同是 会覆盖掉）
            String fileName = file.getOriginalFilename();

            //使用uuid来获取随机的
            String uuid= UUID.randomUUID().toString().replaceAll("-","");
            fileName=uuid+fileName;

            //把文件按照 2019/01/01/01.jpg来存放
           /* SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/DD");
           fileName=sdf.format(new Date())+fileName;*/
            //使用工具类
            String dataPath = new DateTime().toString("yyyy/MM/dd");
            fileName=dataPath+"/"+fileName;

            ossClient.putObject(bucketName, fileName, inputStream);
            //关闭
            ossClient.shutdown();
            //把文件上传后文件路径返回
            String url="http://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
