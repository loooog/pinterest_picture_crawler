package com.company;

/**
 * Created by ma on 2016/08/23.
 */
public class ImgInfo {
  private String rawUrl;
  private String site;
  private String context;
  private String md5;
  private String keyword;

  public ImgInfo(String rawUrl, String site, String context, String md5, String keyword) {
    this.rawUrl = rawUrl;
    this.site = site;
    this.context = context;
    this.md5 = md5;
    this.keyword = keyword;
  }

  public String getRawUrl() {
    return rawUrl;
  }

  public void setRawUrl(String rawUrl) {
    this.rawUrl = rawUrl;
  }

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getMd5() {
    return md5;
  }

  public void setMd5(String md5) {
    this.md5 = md5;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
}
