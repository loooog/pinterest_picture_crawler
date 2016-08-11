package com.company;

import com.company.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static String BRAND_CONFIG_FILE = "./config/brand.txt";
    private static String BOARD_CONFIG_FILE = "./config/board.txt";
    public static ArrayList<String> nextPageUrls = new ArrayList<String>(100000);
    public static void main(String[] args) throws IOException, InterruptedException {
	// write your code here
      getFromBrand();
//      getFromBoard();
      handleNextPage();
    }

  private static void handleNextPage() throws InterruptedException {
    ApiRequestUtil util = new ApiRequestUtil();
    for (int i = 0;i < nextPageUrls.size();i ++){
      System.out.println("正在请求下一页" + nextPageUrls.get(i));
      ApiResult result = util.getApiResultWithUrl(nextPageUrls.get(i));
      if (result == null)
        continue;
      if (result.hasNextPage()){
        nextPageUrls.add(result.getNextPageUrl());
      }
      FileUtil fileUtil1 = new FileUtil("./output/" +"next_result.txt");
      fileUtil1.writeLinesIntoFile(result.getImgUrls());
      Thread.sleep(1000L);
    }
  }

  private static void getFromBoard(){
    FileUtil fileUtil = new FileUtil(BOARD_CONFIG_FILE);
    ArrayList<String> boards = fileUtil.getLinesFromFile();
    for (String board : boards){
      ApiRequestUtil util = new ApiRequestUtil();
      ApiResult result = util.getApiResultWithBoard(board);
      if (result.hasNextPage()){
        nextPageUrls.add(result.getNextPageUrl());
      }
      FileUtil fileUtil1 = new FileUtil("./output/" +"board_result.txt");
      fileUtil1.writeLinesIntoFile(result.getImgUrls());
    }
  }

  private static void getFromBrand() throws IOException, InterruptedException {
    //1.1.从配置文件中获取所有的品牌
    FileUtil fileUtil = new FileUtil(BRAND_CONFIG_FILE);
    ArrayList<String> brands = fileUtil.getLinesFromFile();
    ArrayList<BoardInfoCrawler> boardInfoCrawlers = new ArrayList<BoardInfoCrawler>();
    for (String brand: brands){
      boardInfoCrawlers.add(new BoardInfoCrawler(brand));
    }
    //1.2.获取品牌对应的所有board
    ArrayList<ArrayList<String>> boardsOfAllBrands = new ArrayList<ArrayList<String>>();
    for (BoardInfoCrawler crawler : boardInfoCrawlers){
      boardsOfAllBrands.add(crawler.getBoardInfos());
      Thread.sleep(10000L);
    }

    //1.3.对每个品牌的每个board进行一次请求,返回的结果保存到output中
    for (ArrayList<String> boardOfSingleBrand : boardsOfAllBrands){
      for (String board : boardOfSingleBrand){
        ApiRequestUtil util = new ApiRequestUtil();
        ApiResult result = util.getApiResultWithBoard(board);
        if (result.hasNextPage()){
          nextPageUrls.add(result.getNextPageUrl());
        }
//          FileUtil fileUtil1 = new FileUtil(".\\output\\" + board.split("/")[1] +"_" + board.split("/")[2]+".txt");
        FileUtil fileUtil1 = new FileUtil("./output/" +"brand_result.txt");
        fileUtil1.writeLinesIntoFile(result.getImgUrls());
      }
    }
  }
}
