package com.company;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.util.HttpRequestUtil;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ma on 2016/8/10.
 */
public class ApiRequestUtil {
  public static String BASE_URL_PRE = "https://api.pinterest.com/v1/boards";
  public static String BASE_URL_TAIL = "pins/?access_token=Af8-wehGdRdjD_LZIJd3Y5wzOICvFGlga8qSttBDT9qB6iBASgAAAAA&fields=image";

  public ApiResult getApiResultWithBoard(String board){

    String url = BASE_URL_PRE + board +BASE_URL_TAIL;
    System.out.println("正在请求" +url + "的API");
    try {
      String result = HttpRequestUtil.getUrl(url);
      JSONObject resultJson = JSON.parseObject(result);
      JSONArray imageJsons = resultJson.getJSONArray("data");
      ArrayList<String> imgUrls = new ArrayList<String>();
      for (int i = 0;i < imageJsons.size();i++){
        imgUrls.add(board + ":" + imageJsons.getJSONObject(i).getJSONObject("image").getJSONObject("original").getString("url"));
//        System.out.println(imageJsons.getJSONObject(i).getJSONObject("image").getJSONObject("original").getString("url"));
      }
      return new ApiResult(imgUrls,resultJson.getJSONObject("page").getString("next"));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  public ApiResult getApiResultWithUrl(String url){
    try {
      String result = HttpRequestUtil.getUrl(url);
      JSONObject resultJson = JSON.parseObject(result);
      JSONArray imageJsons = resultJson.getJSONArray("data");
      ArrayList<String> imgUrls = new ArrayList<String>();
      String nextPageUrl = resultJson.getJSONObject("page").getString("next");
      String board = "/brandName/boardName/";
      board = url.substring(url.indexOf("boards") + 6,url.indexOf("pins"));

      for (int i = 0;i < imageJsons.size();i++){
        imgUrls.add(board + ":" + imageJsons.getJSONObject(i).getJSONObject("image").getJSONObject("original").getString("url"));
      }
      return new ApiResult(imgUrls,nextPageUrl);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  public static void main(String[] args){
    ApiRequestUtil util = new ApiRequestUtil();
    ApiResult result = util.getApiResultWithBoard("/gucci/the-alternative-view/");
//    ApiResult result = util.getApiResultWithUrl("https://api.pinterest.com/v1/boards/gucci/the-alternative-view/pins/?access_token=Af8-wehGdRdjD_LZIJd3Y5wzOICvFGlga8qSttBDT9qB6iBASgAAAAA&fields=image&cursor=LT4yMTc3MjA5NjMyMTM5NTUyMTg6NzN8OWI2NzIyYTQzMjc5ZTUwZGUwMTZjNmY1YmI4NDJjMDY3MTMwZmQyMTdlMGQ2Y2Q1ZGJhNzgyYmI2ZGFiN2UyMQ%3D%3D&board=gucci%2Fthe-alternative-view");
    System.out.print(result.getNextPageUrl());
    if (result.hasNextPage()){
      System.out.print(result.getNextPageUrl());
    }
    for (String x :result.getImgUrls()){
      System.out.println(x);
    }
  }
}
