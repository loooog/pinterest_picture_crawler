package com.company.util;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by ma on 2016/8/10.
 */
public class FileUtil {
  private String fileUrl;
  public FileUtil(String fileUrl){
    this.fileUrl = fileUrl;
  }

  public ArrayList<String> getLinesFromFile(){
    try {
      String pathname = fileUrl;
      File filename = new File(pathname);
      InputStreamReader reader = new InputStreamReader(
              new FileInputStream(filename));
      BufferedReader br = new BufferedReader(reader);
      String line = "";
      line = br.readLine();
      ArrayList<String> results = new ArrayList<String>();
      while (line != null) {
        if (line != "")
          results.add(line);
        line = br.readLine();
      }
      return results;
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  public int writeLinesIntoFile(ArrayList<String> lines){
    try {
      String fileName = fileUrl;
      File writename = new File(fileName);
      if (!writename.exists())
        writename.createNewFile();
      BufferedWriter out = new BufferedWriter(new FileWriter(writename,true));
      for (String line : lines){
        out.append(line);
        out.newLine();
      }
      out.flush();
      out.close();
      return  lines.size();
    }catch (Exception e){
      e.printStackTrace();
      return  -1;
    }
  }

  public String getAllFromFile(){
    try {
      String pathname = fileUrl;
      File filename = new File(pathname);
      InputStreamReader reader = new InputStreamReader(
              new FileInputStream(filename));
      BufferedReader br = new BufferedReader(reader);
      String line = "";
      String result = "";
      line = br.readLine();
      while (line != null) {
        result = result + line;
        line = br.readLine();
      }
      return result;
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  public static void main(String args[]) {
    FileUtil fileUtil1 = new FileUtil("./output");
    System.out.print(fileUtil1.getFiles("./output").size());
  }
  public static ArrayList<String> fileResult = new ArrayList<String>(1000);

  public ArrayList<String> getFilesWithRecursion(String absolutePath) {
    File root = new File(absolutePath);
    File[] files = root.listFiles();
    for (File file : files) {
      if (file.isDirectory()) {
        getFilesWithRecursion(file.getAbsolutePath());
      } else {
        fileResult.add(file.getAbsolutePath());
      }
    }
    return fileResult;
  }

  public ArrayList<String> getFiles(String absolutePath) {
    File root = new File(fileUrl);
    File[] files = root.listFiles();
    ArrayList<String> filelist = new ArrayList<String>();
    for (File file : files) {
      if (!file.isDirectory()) {
        filelist.add(file.getAbsolutePath());
      }
    }
    return filelist;
  }
}
