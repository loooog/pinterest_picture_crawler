package com.company.le21eme;

import com.company.util.FileUtil;
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
public class Le21emeCrawler {

  public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.3";
  private String url;

  public Le21emeCrawler(String url) {
    this.url = url;
  }

  public static void main(String[] agrs) throws IOException {
  }

  public static ArrayList<String> getImgUrls(String url) throws IOException {
    Connection connection = Jsoup.connect(url);
    connection.header("User-Agent", USER_AGENT);
    //获取节点
    Document document = null;
    document = connection.get();
    Elements elements = document.select("div.ms-item div.ms-thumbnail div");
    System.out.println("結果数为" + elements.size());
    ArrayList<String> results = new ArrayList<String>(elements.size());
    //background-image:url('https://le21eme.com/wp-content/uploads/2015/11/Le-21eme-Adam-Katz-Sinding-Juliana-Salazar-
    // Paris-Fashion-Week-Fall-Winter-2015_AKS3028-900x599.jpg');
    for (Element element : elements) {
      String raw = element.attr("style");
      if (raw.indexOf("'") < raw.lastIndexOf("'")) {
        results.add(raw.substring(raw.indexOf("'") + 1, raw.lastIndexOf("'")));
      }
    }
    return results;

  }
}
