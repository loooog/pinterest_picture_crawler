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

    ArrayList<String> back_files = new FileUtil(BACKUP_PACKEGE).getFilesWithRecursion(BACKUP_PACKEGE);

    for (String file  : back_files){
      if (file.indexOf(BRAND) > 0){
        append(file, TARGET_PACKAGE + BRAND);
      }else if (file.indexOf(NEXT_RESULT) > 0){
        append(file, TARGET_PACKAGE + NEXT_RESULT);
      }else if (file.indexOf(BOARD_RESULT) > 0){
        append(file, TARGET_PACKAGE + BOARD_RESULT);
      }else {
        System.out.println("none");
      }

    }
  }

  public static void append(String origin,String target){
    FileUtil util = new FileUtil(origin);
    ArrayList<String> results = util.getLinesFromFile();
    util = new FileUtil(target);
    util.writeLinesIntoFile(results);
  }
}
