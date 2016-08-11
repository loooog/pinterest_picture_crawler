package com.company;

import com.aliyun.oss.OSSClient;
import com.company.util.FileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ma on 2016/8/11.
 */
public class OssPusher {
  public static void main(String[] args){
    String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    String accessKeyId = "2etDIbRXKNFw2uZM";
    String accessKeySecret = "7QluQAZwtcUnMdsQ3i6u7mKO7Bd2ej";

    // 创建OSSClient实例
    OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    FileUtil util = new FileUtil("./output");
    ArrayList<String> filelist = util.getFiles("./output");
    InputStream inputStream = null;
    for (int i = 0;i < filelist.size();i++){
      System.out.println("正在处理" + filelist.get(i));
      FileUtil fileUtil = new FileUtil(filelist.get(i));
      ArrayList<String> imgUrls = fileUtil.getLinesFromFile();
      for (String imgUrl : imgUrls){
        try {
          System.out.println("正在传送" + imgUrl.substring(imgUrl.indexOf(":") + 1));
          inputStream = new URL(imgUrl.substring(imgUrl.indexOf(":") + 1)).openStream();
          ossClient.putObject("codingma-picture-test", "pinterest/" + imgUrl.split("/")[1] +"_" + imgUrl.split("/")[2] + "/" + imgUrl.substring(imgUrl.lastIndexOf("/") + 1), inputStream);
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("error");
          continue;
        }
      }
    }
    ossClient.shutdown();
    System.out.println("done");
  }
}
