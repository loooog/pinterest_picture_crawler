package com.company.tommyton;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ma on 2016/08/13.
 */
public class TommytonImgCrawler {
  public static final String BASE_URL_SEARCH_PRE = "http://www.tommyton.com/archive/index.html?aj=es&tags=";
  public static final String BASE_URL_SEARCH_TAIL = "&page=";
  public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36";
  private String url;

  public TommytonImgCrawler(){

  }

  public static void main(String[] args){
    String url = "http://www.tommyton.com/archive/index.html?aj=es&tags=119&page=3";
    Connection connection = Jsoup.connect(url);
    connection.header("User-Agent",USER_AGENT);

    Document document = null;
    try {
      document = connection.get();
      Elements elements = document.select("div.js-masonry-grid__item div.media-module__image-wrapper span[data-class=media-module__image] span:last-of-type");
      for (Element element : elements){
        System.out.println(element.attr("data-src"));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public ArrayList<String> getImgUrls(){
    ArrayList<String> results = new ArrayList<String>();
    return results;
  }
}
