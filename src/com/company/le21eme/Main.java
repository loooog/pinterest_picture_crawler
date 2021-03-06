package com.company.le21eme;

import com.company.util.FileUtil;
import org.jsoup.HttpStatusException;

import java.util.ArrayList;

/**
 * Created by ma on 2016/08/13.
 */
public class Main {
  public static final String CONFIG_FILE_BRAND = "./config/" + "brand_for_le21eme.txt";
  public static final String OUTPUT_FILE = "./output/" +"le21eme.txt";
  public static final String OUTPUT_FILE_ARCHIVE = "./output/" +"le21eme_archive.txt";

  public static final String BASE_URL_SEARCH_PRE = "https://le21eme.com/page/";
  public static final String BASE_URL_SEARCH_TAIL = "/?s=";

  public static final String BASE_URL_ARCHIVE_PRE = "https://le21eme.com/archive/page/";
  public static void main(String[] args){
//    getFromBrandFile();
    getFromArchive();
  }

  private static void getFromBrandFile(){
    FileUtil util = new FileUtil(CONFIG_FILE_BRAND);
    ArrayList<String> brands = util.getLinesFromFile();
    for (String brand : brands){
      int index = 1;
      while (true){
        try {
          String url = BASE_URL_SEARCH_PRE + index + BASE_URL_SEARCH_TAIL + brand;
          System.out.println("正在抓取: " + url);
          ArrayList<String> results = Le21emeCrawler.getImgUrls(url);
          if (results == null || results.size() == 0)
            break;
          for (int i = 0;i < results.size();i++){
            results.set(i,"/" + brand + "/" + brand + "/:" + results.get(i));
          }
          FileUtil fileUtil1 = new FileUtil(OUTPUT_FILE);
          fileUtil1.writeLinesIntoFile(results);
          index++;
          Thread.sleep(1000L);
        } catch (HttpStatusException e){
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
    System.out.print("done");
  }

  private static void getFromArchive() {
    int index = 1;
    while (true) {
      try {
        String url = BASE_URL_ARCHIVE_PRE + index;
        System.out.println("正在抓取: " + url);
        ArrayList<String> results = Le21emeCrawler.getImgUrls(url);
        if (results == null || results.size() == 0)
          break;
        for (int i = 0;i < results.size();i++){
          results.set(i,"/none/none/:" + results.get(i));
        }
        FileUtil fileUtil1 = new FileUtil(OUTPUT_FILE_ARCHIVE);
        fileUtil1.writeLinesIntoFile(results);
        index++;
        Thread.sleep(1000L);
      } catch (HttpStatusException e){
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
