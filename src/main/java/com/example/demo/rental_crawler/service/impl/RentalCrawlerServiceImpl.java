package com.example.demo.rental_crawler.service.impl;

import java.time.Duration;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.rental_crawler.service.RentalCrawlerService;

import io.github.bonigarcia.wdm.WebDriverManager;

@Service
public class RentalCrawlerServiceImpl implements RentalCrawlerService {
    
    Logger log = LoggerFactory.getLogger(RentalCrawlerService.class);
    
    @Override
    public void fetchRentalData() {
        log.info("Starting to fetch rental data...");
        
        WebDriverManager.chromedriver().setup(); // 自动选择驱动
        System.out.println("ChromeDriver version: " + WebDriverManager.chromedriver().getDownloadedDriverVersion());

        // 设置 ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.6668.59 Safari/537.36");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
         //options.addArguments("--headless"); // 根据需要决定是否启用无头模式

        WebDriver driver = new ChromeDriver(options);
        
        try {
            String urlString = "https://www.google.com"; // 使用 Google 的网址
            driver.get(urlString);
            System.out.println("Page title is: " + driver.getTitle());
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.titleContains("Google"));

            String pageSource = driver.getPageSource();
            Document doc = Jsoup.parse(pageSource);
            parseHTML(doc);
            
            log.info("Successfully fetched rental data");
            
        } catch (Exception e) {
            log.error("Error while fetching rental data", e);
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private void parseHTML(Document doc) {
        log.info("Document is: {}", doc);
        // 解析 HTML 逻辑
    }
}