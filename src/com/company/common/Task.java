package com.company.common;

import com.alibaba.fastjson.JSON;
import com.company.util.FileUtil;

import java.lang.reflect.Constructor;

/**
 * Created by ma on 2016/08/16.
 */
public class Task implements Runnable {

  public static final String PACKAGE_NAME = "com.company.";

  private String configFile;

  public Task(String configFile) {
    this.configFile = configFile;
  }

  @Override
  public void run() {
    try {
      FileUtil fileUtil = new FileUtil(configFile);
      String json = fileUtil.getAllFromFile();
      SiteRule siteRule = JSON.parseObject(json.toString(), SiteRule.class);
      System.out.println(siteRule.getOutputFile());
      Class<?> parserClass = null;
      CommonParser parser = null;

      parserClass = Class.forName(PACKAGE_NAME + siteRule.getParser());
      Constructor<?>[] constructors = parserClass.getConstructors();
      parser = (CommonParser) constructors[0].newInstance(siteRule.getCssLocator(), siteRule.getAttrName());

      CommonCrawler commonCrawler = new CommonCrawler(parser, siteRule);
      commonCrawler.getAll();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
