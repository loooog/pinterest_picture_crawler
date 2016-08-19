package com.company;

import com.company.util.FileUtil;

import java.util.ArrayList;

/**
 * Created by ma on 2016/08/17.
 */
public class Main {
  public static final String BACKUP_PACKEGE = "./URL_Backup/";
  public static final String TARGET_PACKAGE = "./output/";
  public static final String BRAND = "brand_result.txt";
  public static final String NEXT_RESULT = "next_result.txt";
  public static final String BOARD_RESULT = "board_result.txt";
  public static void main(String[] args){
    String x = "./output/flickr/gucci.txt";
    FileUtil util = new FileUtil(x);
    ArrayList<String> results = util.getLinesFromFile();
    for (int i = 0;i < results.size();i++){
      results.set(i,"/none/none/:" + results.get(i));
    }
    FileUtil util1 = new FileUtil("./output/flickr/flickr.txt");
    util1.writeLinesIntoFile(results);


  }

}
