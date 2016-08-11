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
        results.add(line);
        line = br.readLine();
      }
      return results;
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }finally {

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

  public static void main(String args[]) {
    FileUtil fileUtil1 = new FileUtil("./output");
    System.out.print(fileUtil1.getFiles("./output").size());
  }

  public ArrayList<String> getFiles(String absolutePath) {
    File root = new File(fileUrl);
    File[] files = root.listFiles();
    ArrayList<String> filelist = new ArrayList<String>();
    for (File file : files) {
      if (file.isDirectory()) {
        getFiles(file.getAbsolutePath());
        filelist.add(file.getAbsolutePath());
      } else {
        filelist.add(file.getAbsolutePath());
      }
    }
    return filelist;
  }
}
