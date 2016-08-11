package com.company.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ma on 2016/8/10.
 */
public class HttpRequestUtil {
  static public byte[] getUrlBytes(String urlSpec) throws IOException {
    URL url = new URL(urlSpec);
    HttpURLConnection connection = (HttpURLConnection)url.openConnection();

    try {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      InputStream in = connection.getInputStream();

      if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
        return null;
      }

      int bytesRead = 0;
      byte[] buffer = new byte[1024];
      while ((bytesRead = in.read(buffer)) > 0) {
        out.write(buffer, 0, bytesRead);
      }
      out.close();
      return out.toByteArray();
    } finally {
      connection.disconnect();
    }
  }

  public static String getUrl(String urlSpec) throws IOException {
    return new String(getUrlBytes(urlSpec));
  }
}
