package com.company;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.ObjectMetadata;
import com.company.util.FileUtil;
import com.company.util.MD5Util;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ma on 2016/8/11.
 */
public class OssPusher {
  private String path;
  private static String END_POINT = "http://oss-cn-shanghai.aliyuncs.com";
  private static String ACCESS_KEY_ID = "2etDIbRXKNFw2uZM";
  private static String ACCESS_KEY_SECRET = "7QluQAZwtcUnMdsQ3i6u7mKO7Bd2ej";
  private static String BURKET = "codingma-picture-test";

  private static int coreNumber = Runtime.getRuntime().availableProcessors();
  public OssPusher(String path){
    this.path = path;
  }

  public static void main(String[] args){
    String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    String accessKeyId = "znnq7uoLz3UDxiNN";
    String accessKeySecret = "AOfy4XyBuY8WOfz9hZd3c1lSGaXMw6";

// 创建OSSClient实例
    OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//    OSSClient ossClient = new OSSClient(END_POINT,ACCESS_KEY_ID,ACCESS_KEY_SECRET);

    boolean exists = ossClient.doesBucketExist("lookimg");
    List<Bucket> buckets = ossClient.listBuckets();
    for (Bucket bucket : buckets) {
      System.out.println(" - " + bucket.getName());
    }

// 关闭client
    ossClient.shutdown();
  }

  public void push(){
    FileUtil util;
    ArrayList<String> filelist = new ArrayList<String>();
    try {
      util = new FileUtil(path);
      filelist = util.getFiles(path);
    }catch (Exception e){
      System.out.print("filepath is invalid");
      return;
    }

    ExecutorService service = Executors.newFixedThreadPool(coreNumber * 2);

    for (int i = 0;i < filelist.size();i++){
      System.out.println("processed file:" + filelist.get(i));
      FileUtil fileUtil = new FileUtil(filelist.get(i));
      ArrayList<String> imgUrls = fileUtil.getLinesFromFile();
      File tempFile = new File(filelist.get(i));
      String fileName = tempFile.getName();

      for (String imgUrl : imgUrls){
        PushTask task = new PushTask(imgUrl,fileName.split(".txt")[0]);
        service.execute(task);
      }
    }
  }

  class PushTask implements Runnable{
    private String imgUrlWithContext;
    private String site;

    public PushTask( String imgUrlWithContext,String site) {
      this.imgUrlWithContext = imgUrlWithContext;
      this.site = site;
    }

    @Override
    public void run() {
      OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
      InputStream inputStream = null;
      try {
        String context = imgUrlWithContext.split("/")[1] +"_" + imgUrlWithContext.split("/")[2];
        String rawUrl = imgUrlWithContext.substring(imgUrlWithContext.indexOf(":") + 1);

        inputStream = new URL(rawUrl).openStream();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int bytesRead = 0;
        byte[] buffer = new byte[1024];
        while ((bytesRead = inputStream.read(buffer)) > 0) {
          out.write(buffer, 0, bytesRead);
        }
        out.close();
        byte[] imgbytes = out.toByteArray();
        String md5 = MD5Util.getMD5(imgbytes);
        ImgInfo imgInfo = new ImgInfo(rawUrl,site,context,md5,null);
        String jsonString = JSON.toJSONString(imgInfo);
        InputStream transferStream = new ByteArrayInputStream(imgbytes);

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentType("image/jpeg");
        objectMeta.setContentDisposition(rawUrl);

        ossClient.putObject(BURKET, "streetSnap/" + md5, transferStream,objectMeta);

        System.out.println(jsonString);
      } catch (IOException e) {
        System.out.print("error:" + imgUrlWithContext);
        e.printStackTrace();
      }

      ossClient.shutdown();
    }
  }
}
