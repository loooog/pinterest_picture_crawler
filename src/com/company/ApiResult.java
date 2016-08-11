package com.company;

import java.util.ArrayList;

/**
 * Created by ma on 2016/8/11.
 */
public class ApiResult {
  private ArrayList<String> imgUrls;
  private String nextPageUrl;

  public ApiResult(ArrayList<String> imgUrls,String nextPageUrl){
    this.imgUrls = imgUrls;
    this.nextPageUrl = nextPageUrl;
  }
  public ArrayList<String> getImgUrls() {
    return imgUrls;
  }

  public void setImgUrls(ArrayList<String> imgUrls) {
    this.imgUrls = imgUrls;
  }

  public String getNextPageUrl() {
    return nextPageUrl;
  }

  public void setNextPageUrl(String nextPageUrl) {
    this.nextPageUrl = nextPageUrl;
  }

  public boolean hasNextPage(){
    return nextPageUrl != null && nextPageUrl.indexOf("http") != -1;
  }
}
