package com.company;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.company.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ma on 2016/08/17.
 */
public class Main {
  public static final String BACKUP_PACKEGE = "./URL_Backup/";
  public static final String TARGET_PACKAGE = "./output/";
  public static final String BRAND = "brand_result.txt";
  public static final String NEXT_RESULT = "next_result.txt";
  public static final String BOARD_RESULT = "board_result.txt";

  private static String END_POINT = "http://oss-cn-shanghai.aliyuncs.com";
  private static String ACCESS_KEY_ID = "2etDIbRXKNFw2uZM";
  private static String ACCESS_KEY_SECRET = "7QluQAZwtcUnMdsQ3i6u7mKO7Bd2ej";

  public static void main(String[] args){
    if (args.length != 1){
      System.out.print("input a valid path");
    }else {
      OssPusher ossPusher = new OssPusher(args[0]);
      ossPusher.push();
    }
  }

}
