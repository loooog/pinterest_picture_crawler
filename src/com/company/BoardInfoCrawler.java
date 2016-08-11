package com.company;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ma on 2016/8/10.
 */
public class BoardInfoCrawler {
  private String url;
  private static String baseUrl = "https://www.pinterest.com/brand/boards/";

  public BoardInfoCrawler(String brand){
    this.url = baseUrl.replace("brand",brand);
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public ArrayList<String> getBoardInfos() throws IOException {
    //初始化参数
    String user_agent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.3";
    //构造连接
    Connection connection = Jsoup.connect(url);
    connection.header("User-Agent",user_agent);
    //获取节点
    Document document = connection.get();
    System.out.println("正在解析"+ url +"的board");
    Elements elements = document.select("div.item a");
    System.out.println(url + "解析结果个数:" +elements.size());
    //生成结果
//    System.out.println(document.outerHtml());
    ArrayList<String> results = new ArrayList<String>();
    for (int i = 0;i < elements.size();i++)
      results.add(elements.get(i).attr("href"));

    return results;
  }

  public static void main(String[] args) throws IOException {
    String url = "https://www.pinterest.com/gucci/boards/";
    BoardInfoCrawler crawler = new BoardInfoCrawler(url);
    ArrayList<String> results = crawler.getBoardInfos();
    for(String x:results){
      System.out.println(x);
    }
  }
}
