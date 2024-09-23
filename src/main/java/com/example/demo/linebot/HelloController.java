package com.example.demo.linebot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	
	@GetMapping("/Test/HelloWorld")
	public String Hello() {
		return "Hello!!!";
	}
	
}	
