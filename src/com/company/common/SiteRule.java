package com.company.common;

import java.util.ArrayList;
import java.util.List;

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
  private List<String> keyWords;
  private String parser;

  private static final String path = "./output/";
  public SiteRule(){

  }
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
    return path + site + ".txt";
  }

  public void setOutputFile(String outputFile) {
    this.outputFile = outputFile;
  }

  public int getUpdatePages() {
    return updatePages;
  }

  public void setUpdatePages(int updatePages) {
    this.updatePages = updatePages;
  }

  public String getParser() {
    return parser;
  }

  public void setParser(String parser) {
    this.parser = parser;
  }

  public List<String> getKeyWords() {
    return keyWords;
  }

  public void setKeyWords(List<String> keyWords) {
    this.keyWords = keyWords;
  }
}
