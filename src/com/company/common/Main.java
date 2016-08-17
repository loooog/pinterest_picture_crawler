package com.company.common;

import com.company.util.FileUtil;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ma on 2016/08/15.
 */
public class Main {
  public static final String CONFIG_FILE_PATH = "./config/others/";
  public static final String CONFIG_FILE_TYPE = ".txt";

  public static void main(String[] args) {
    FileUtil util = new FileUtil(CONFIG_FILE_PATH);
    ArrayList<String> configFiles = util.getFiles(CONFIG_FILE_PATH);
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(8);
    for (final String configFile : configFiles) {
      if (configFile.indexOf(CONFIG_FILE_TYPE) > 0) {
        fixedThreadPool.execute(new Task(configFile));
      }
    }
  }
}
