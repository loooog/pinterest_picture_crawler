package com.company.pinterest;

import com.company.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    private static String BRAND_CONFIG_FILE = "./config/pinterest/brand.txt";
    private static String BOARD_CONFIG_FILE = "./config/pinterest/board.txt";
    private static String NEXT_PAGE_CONFIG_FILE = "./config/pinterest/next_page.txt";
    public static ArrayList<String> nextPageUrls = new ArrayList<String>(100000);
    public static void main(String[] args) throws IOException, InterruptedException {
	// write your code here
      getFromBrand();
//      getFromBoard();
//      getFromNextPage();
      handleNextPage();
    }

  private static void getFromNextPage(){
    FileUtil fileUtil = new FileUtil(NEXT_PAGE_CONFIG_FILE);
    ArrayList<String> rawNextPageUrls = fileUtil.getLinesFromFile();
    Random random = new Random();
    ApiRequestUtil util = new ApiRequestUtil();
    for (String rawNextPageUrl : rawNextPageUrls){
      ApiResult result = util.getApiResultWithUrl(rawNextPageUrl);
      if (result == null){
        ApiRequestUtil.currentToken = ApiRequestUtil.tokens[random.nextInt(ApiRequestUtil.tokens.length)];
        System.out.println("更换了token");
        continue;
      }
      if (result.hasNextPage()){
        nextPageUrls.add(result.getNextPageUrl());
      }
      FileUtil fileUtil1 = new FileUtil("./output/" +"next_result.txt");
      fileUtil1.writeLinesIntoFile(result.getImgUrls());
    }
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
      Thread.sleep(2000L);
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
        FileUtil fileUtil1 = new FileUtil("./output/" +"brand_result.txt");
        fileUtil1.writeLinesIntoFile(result.getImgUrls());
      }
    }
  }
}
