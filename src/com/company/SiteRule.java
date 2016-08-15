package com.company;

/**
 * Created by ma on 2016/08/15.
 */
public class SiteRule {
  private String cssLocator;
  private String attrName;
  private String rawUrl;
  private String site;
  private String outputFile;
  private int updatePages;
  private String[] keywords;

  private static final String path = "./output/";
  public SiteRule(String cssLocator, String attrName, String rawUrl, String site) {
    this.cssLocator = cssLocator;
    this.attrName = attrName;
    this.rawUrl = rawUrl;
    this.site = site;
    this.outputFile = path + site + ".txt";
  }

  public String getCssLocator() {
    return cssLocator;
  }

  public void setCssLocator(String cssLocator) {
    this.cssLocator = cssLocator;
  }

  public String getAttrName() {
    return attrName;
  }

  public void setAttrName(String attrName) {
    this.attrName = attrName;
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

  public String getOutputFile() {
    return outputFile;
  }

  public void setOutputFile(String outputFile) {
    this.outputFile = outputFile;
  }
}
