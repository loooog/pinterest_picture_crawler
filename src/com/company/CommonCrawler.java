package com.company;

import com.company.le21eme.Le21emeCrawler;
import com.company.util.FileUtil;
import org.jsoup.HttpStatusException;

import java.util.ArrayList;

/**
 * Created by ma on 2016/08/15.
 */
public class CommonCrawler {
  private CommonParser parser;
  private SiteRule siteRule;

  public CommonCrawler(CommonParser parser,SiteRule siteRule){
    this.parser = parser;
    this.siteRule = siteRule;
  }

  public static void main(String[] args){
    String cssLocator = "figure.cap-bot div.thumbnailbig img";
    String attrName = "src";
    String rawUrl = "http://theurbanspotter.com/page/%1$d/";
    String site = "theurbanspotter";
    SiteRule siteRule = new SiteRule(cssLocator,attrName,rawUrl,site);
    TheubanspotterParser parser = new TheubanspotterParser(siteRule.getCssLocator(),siteRule.getAttrName());
    CommonCrawler commonCrawler = new CommonCrawler(parser,siteRule);
    commonCrawler.getAll();
  }
  protected void getFromSearch(){
  }
  protected void getAll(){
    int index = 1;
    while (true) {
      try {
        String url = String.format(siteRule.getRawUrl(),index);
        System.out.println("crawling: " + url);
        ArrayList<String> results = parser.getImgUrls(url);
        if (results == null || results.size() == 0)
          break;
        for (int i = 0;i < results.size();i++){
          results.set(i,"/none/none/:" + results.get(i));
        }
        FileUtil fileUtil1 = new FileUtil(siteRule.getOutputFile());
        fileUtil1.writeLinesIntoFile(results);
        index++;
        Thread.sleep(1000L);
      } catch (HttpStatusException e){
        if (e.getStatusCode() == 404 ){
          System.out.println("end");
          break;
        }
      } catch (Exception e) {
        e.printStackTrace();
        continue;
      }
    }
  }
  protected void updateAll(){

  }
  protected void updateFromSearch(){

  }
}
