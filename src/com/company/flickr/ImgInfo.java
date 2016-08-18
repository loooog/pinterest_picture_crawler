package com.company.flickr;

/**
 * Created by ma on 2016/08/17.
 */
public class ImgInfo {
  /*
  https://farm1.staticflickr.com/2/1418878_1e92283336_m.jpg
  farm-id: 1
  server-id: 2
  photo-id: 1418878
  secret: 1e92283336
  size: m
   */
  private static final String IMG_URL_FORMAT = "https://farm%1$s.staticflickr.com/%2$s/%3$s_%4$s_%5$s.jpg";
  private int farmId;
  private int serverId;
  private int photoId;
  private int secretId;
  private static final String SIZE = "z";

  public static String getCompleteUrl(String farmId,String serverId,String photoId,String secretId){
    return String.format(IMG_URL_FORMAT,farmId,serverId,photoId,secretId,SIZE);
  }

  public ImgInfo(int farmId, int serverId, int photoId, int secretId) {
    this.farmId = farmId;
    this.serverId = serverId;
    this.photoId = photoId;
    this.secretId = secretId;
  }

  public int getFarmId() {
    return farmId;
  }

  public void setFarmId(int farmId) {
    this.farmId = farmId;
  }

  public int getSecretId() {
    return secretId;
  }

  public void setSecretId(int secretId) {
    this.secretId = secretId;
  }

  public int getPhotoId() {
    return photoId;
  }

  public void setPhotoId(int photoId) {
    this.photoId = photoId;
  }

  public int getServerId() {
    return serverId;
  }

  public void setServerId(int serverId) {
    this.serverId = serverId;
  }
}
