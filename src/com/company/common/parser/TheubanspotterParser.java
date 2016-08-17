package com.company.common.parser;

import com.company.common.CommonParser;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ma on 2016/08/15.
 */
public class TheubanspotterParser extends CommonParser {
  public TheubanspotterParser(String cssLocator, String attrName) {
    super(cssLocator, attrName);
  }

  @Override
  public ArrayList<String> getImgUrls(String pageUrl) throws IOException {
    ArrayList<String> result = super.getImgUrls(pageUrl);
    for (int i = 0;i < result.size();i++){
      result.set(i,result.get(i));
    }
    return result;
  }
}
