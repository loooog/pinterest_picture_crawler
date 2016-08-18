package com.company.flickr;

import com.company.util.FileUtil;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ma on 2016/08/17.
 */
public class Main {
  public static final String CONFIG_SEARCH_FILE = "./config/flickr/keywords.txt";

  public static void main(String[] args) throws InterruptedException {
    FileUtil util = new FileUtil(CONFIG_SEARCH_FILE);
    ArrayList<String> keywords = util.getLinesFromFile();

    ExecutorService service = Executors.newFixedThreadPool(8);
    for (String keyword : keywords){
      ApiRequest apiRequest = new ApiRequest(keyword);
      service.execute(apiRequest);
    }
  }
}
