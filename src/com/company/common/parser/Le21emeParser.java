package com.company.common.parser;

import com.company.common.CommonParser;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

/**
 * Created by ma on 2016/08/17.
 */
public class Le21emeParser extends CommonParser{
  public Le21emeParser(String cssLocator, String attrName) {
    super(cssLocator, attrName);
  }

  @Override
  protected ArrayList<String> getImgUrls(String pageUrl) throws IOException {
    ArrayList<String> raws =  super.getImgUrls(pageUrl);
    for (int i = 0;i < raws.size();i++){
      String raw = raws.get(i);
      if (raw.indexOf("'") < raw.lastIndexOf("'")) {
        raws.set(i,raw.substring(raw.indexOf("'") + 1, raw.lastIndexOf("'")));
      }
    }
    return raws;
  }
}
