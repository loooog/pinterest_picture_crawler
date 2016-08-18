package com.company.flickr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ma on 2016/08/17.
 */
public class ApiSearchResult {
  public static final String XML_TAG_NAME_PHOTOS = "photos";
  public static final String XML_TAG_NAME_PHOTO = "photo";
  public static final String ATTR_PHOTOS_TOTAL = "total";
  public static final String ATTR_PHOTOS_PAGES = "pages";
  public static final String ATTR_PHOTO_ID = "id";
  public static final String ATTR_PHOTO_SECRET = "secret";
  public static final String ATTR_PHOTOS_SERVER = "server";
  public static final String ATTR_PHOTOS_FARM = "farm";

  private int totalImgs;
  private int totalPages;
  private ArrayList<String> imgUrls;

  public int getTotalImgs() {
    return totalImgs;
  }

  public void setTotalImgs(int totalImgs) {
    this.totalImgs = totalImgs;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public ArrayList<String> getImgUrls() {
    return imgUrls;
  }

  public void setImgUrls(ArrayList<String> imgUrls) {
    this.imgUrls = imgUrls;
  }
}
