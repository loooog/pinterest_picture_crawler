package com.company.test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.util.HttpRequestUtil;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.Certificate;

/**
 * Created by ma on 2016/8/10.
 */
public class FastJsonTest {
  public static void main(String[] args){
    String url = "https://api.pinterest.com/v1/boards/gucci/how-they-wear-it/pins/?access_token=ARTbOnUDVZisP4KdzF_yooxgZPsVFGlQ2msV0MpDT9qB6iBASgAAAAA&fields=url%2Cimage";
    try {
      String result = HttpRequestUtil.getUrl(url);
      JSONObject resultJson = JSON.parseObject(result);
      JSONArray imageJsons = resultJson.getJSONArray("data");
      for (int i = 0;i < imageJsons.size();i++){
        System.out.println(imageJsons.getJSONObject(i).getJSONObject("image").getJSONObject("original").getString("url"));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
