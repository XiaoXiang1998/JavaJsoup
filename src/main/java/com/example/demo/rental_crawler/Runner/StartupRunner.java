package com.example.demo.rental_crawler.Runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.rental_crawler.service.RentalCrawlerService;
@Component
public class StartupRunner implements ApplicationRunner {
	
	@Autowired
	RentalCrawlerService rentalCrawlerService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		 System.out.println("Starting to fetch rental data...");
	        rentalCrawlerService.fetchRentalData();
	        System.out.println("Successfully fetched rental data");
		
	}

}
