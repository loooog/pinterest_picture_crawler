package com.company.common;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ma on 2016/08/15.
 */
public class CommonParser {
  public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.3";
  public static final String HEAD_USER_AGENT = "User-Agent";
  private String cssLocator;
  private String attrName;
  public CommonParser(String cssLocator,String attrName){
    this.cssLocator = cssLocator;
    this.attrName = attrName;
  }
  protected ArrayList<String> getImgUrls(final String pageUrl) throws IOException {
    return getRawUrls(pageUrl,cssLocator,attrName);
  }
  private ArrayList<String> getRawUrls(final String pageUrl,final String cssLocator,final String attrName) throws IOException {
    Connection connection = Jsoup.connect(pageUrl);
    connection.header(HEAD_USER_AGENT, USER_AGENT);
    //获取节点
    Document document = connection.get();
    Elements elements = document.select(cssLocator);
    System.out.println("The number of result is " + elements.size());
    ArrayList<String> results = new ArrayList<String>(elements.size());
    for (Element element : elements) {
      results.add(element.attr(attrName));
    }
    return results;
  }
}
