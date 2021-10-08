package com.buran.firstproject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class HelloController {
	
	//route for localHost: 8080
		@RequestMapping("/")
		public String sayHello() {
			return "Hello Everybody, welcome to spring";
		}
		@RequestMapping("/summer")
		public String welcomeToSummer() {
			return "Welcome to Summer, but still coding in spring";
		}
		@RequestMapping(value="/fall",method=RequestMethod.GET)
		public String welcomeToFall() {
			return "Welcome to Fall, but we rise when we follow the 20 minutes and put work in";
		}

}
