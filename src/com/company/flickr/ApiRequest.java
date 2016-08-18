package com.company.flickr;

import com.company.util.FileUtil;
import com.company.util.HttpRequestUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ma on 2016/08/17.
 */
public class ApiRequest implements Runnable{
  private static final String SEARCH_TEXT = "street%20style%20";
  private static final String BASE_URL_FORMAT =
          "https://secure.flickr.com/services/rest/?method=flickr.photos.search&api_key=ca329d8cb32390ea248be0668c5f6291&safe_search=1&content_type=1&sort=interestingness-desc&per_page=500&page=%1$d&text=";
  private static final int BEGIN_PAGE_NUMBER = 1;
  private static final String OUTPUT_PACKAGE = "./output/flickr/";
  private static final String OUTPUT_FILE_TYPE = ".txt";

  private String requestUrl;
  private String keyword;
  private String outputFile;
  public ApiRequest(String keyword){
    this.keyword = keyword;
    this.requestUrl = String.format(BASE_URL_FORMAT,BEGIN_PAGE_NUMBER) + SEARCH_TEXT + keyword;
    this.outputFile = keyword + OUTPUT_FILE_TYPE;
  }

  @Override
  public void run() {
    try {
      String xmlString = HttpRequestUtil.getUrl(requestUrl);
      ApiSearchResult result = getResults(xmlString);
      FileUtil util1 = new FileUtil(OUTPUT_PACKAGE + outputFile);
      util1.writeLinesIntoFile(result.getImgUrls());

      for (int i = 2;i <= 2;i++){
        String currentRequestUrl = String.format(BASE_URL_FORMAT,i) + SEARCH_TEXT + keyword;
        System.out.println(currentRequestUrl);
        String currentXmlString = HttpRequestUtil.getUrl(requestUrl);
        ApiSearchResult currentResult = getResults(currentXmlString);
        FileUtil util = new FileUtil(OUTPUT_PACKAGE + outputFile);
        util.writeLinesIntoFile(currentResult.getImgUrls());
        Thread.sleep(1000L);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ApiSearchResult getResults(String xmlString) throws ParserConfigurationException, IOException, SAXException {
    org.jsoup.nodes.Document xmlDoc = Jsoup.parse(xmlString, "UTF-8", Parser.xmlParser());
    Element photoElement = xmlDoc.getElementsByTag(ApiSearchResult.XML_TAG_NAME_PHOTOS).first();
//    int totalPages = Integer.parseInt(photoElement.attr("pages"));
    int totalPages = 3;
    List<Element> photoElements  = photoElement.select(ApiSearchResult.XML_TAG_NAME_PHOTO);
    ArrayList<String> imgs = new ArrayList<String>(800);
    for (Element element : photoElements){
      String farm = element.attr(ApiSearchResult.ATTR_PHOTOS_FARM);
      String server = element.attr(ApiSearchResult.ATTR_PHOTOS_SERVER);
      String secret = element.attr(ApiSearchResult.ATTR_PHOTO_SECRET);
      String id = element.attr(ApiSearchResult.ATTR_PHOTO_ID);
      String imgCompletedUrl = ImgInfo.getCompleteUrl(farm,server,id,secret);
      imgs.add(imgCompletedUrl);
      System.out.println(imgCompletedUrl);
    }

    ApiSearchResult result = new ApiSearchResult();
    result.setTotalPages(totalPages);
    result.setImgUrls(imgs);
    return result;
  }
}
