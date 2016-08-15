package com.company.tommyton;

import com.company.util.FileUtil;
import org.jsoup.HttpStatusException;

import java.util.ArrayList;

/**
 * Created by ma on 2016/08/13.
 */
public class Main {
  public static final String CONFIG_FILE_BRAND = "./config/" + "brand_for_tommyton.txt";
  public static final String OUTPUT_FILE = "./output/" +"tommyton.txt";
  public static final String OUTPUT_FILE_ARCHIVE = "./output/" +"tommyton_archive.txt";
  //http://www.tommyton.com/archive/index.html?aj=es&tags=119&page=3

  public static final String BASE_URL_SEARCH_PRE = "http://www.tommyton.com/archive/index.html?aj=es&tags=";
  public static final String BASE_URL_SEARCH_TAIL = "&page=";

  public static final String BASE_URL_ARCHIVE_PRE = "http://www.tommyton.com/archive/index.html?aj=es&page=";
  //http://www.tommyton.com/archive/index.html?aj=es&page=3

  public static void main(String[] args){
    getFromArchive();
  }

  private static void getFromBrandFile(){
    FileUtil fileUtil = new FileUtil(CONFIG_FILE_BRAND);
    ArrayList<String> brands = fileUtil.getLinesFromFile();
    for (String brand :brands){
      int index = 1;
      System.out.println("正在处理品牌：" + brand);
      while (true){
        try {
          String url = BASE_URL_SEARCH_PRE + brand + BASE_URL_SEARCH_TAIL + index;
          System.out.println("正在抓取" + url);
          ArrayList<String> results = TommytonImgCrawler.getImgUrls(url);
          if (results == null || results.size() == 0){
            break;
          }

          FileUtil util = new FileUtil(OUTPUT_FILE);
          util.writeLinesIntoFile(results);
          index++;
          Thread.sleep(1000L);
        }catch (Exception e){
          e.printStackTrace();
          break;
        }
      }
    }
  }

  private static void getFromArchive(){
  //http://www.tommyton.com/archive/index.html?aj=es&page=3
    int index = 1;
    while (true){
      try {
        String url = BASE_URL_ARCHIVE_PRE + index;
        System.out.println("正在抓取" + url);
        ArrayList<String> results = TommytonImgCrawler.getImgUrls(url);
        if (results == null || results.size() == 0){
          break;
        }
        for (int i = 0;i < results.size();i++){
          results.set(i,"/none/none/:" + results.get(i));
        }
        FileUtil util = new FileUtil(OUTPUT_FILE_ARCHIVE);
        util.writeLinesIntoFile(results);
        index++;
        Thread.sleep(1000L);
      }catch (HttpStatusException e){
        if (e.getStatusCode() == 404 ){
          System.out.println("已到结尾");
          break;
        }
      } catch (Exception e) {
        e.printStackTrace();
        continue;
      }
    }
  }
}
