package com.company;

import com.company.util.FileUtil;

import java.util.ArrayList;

/**
 * Created by ma on 2016/08/15.
 */
public class Main {
  public static final String CONFIG_FILE_PATH = "./config2/";
  public static final String CONFIG_FILE_TYPE = ".txt";
  public static void main(){
    FileUtil util = new FileUtil(CONFIG_FILE_PATH);
    ArrayList<String> configFiles = util.getFiles(CONFIG_FILE_PATH);
    for (String configFile :configFiles){
      if (configFile.indexOf(CONFIG_FILE_TYPE) > 0){
        
      }
    }
  }
}
