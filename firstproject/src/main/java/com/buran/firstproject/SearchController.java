package com.buran.firstproject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

	@RequestMapping("/search")
	public String searchForSomething(@RequestParam(value="q", required=false) String searchTerm) {
		
	
		System.out.println(searchTerm);
		
		if(searchTerm ==null){
			return "What do seek young jedi?";
		}else {
			return "You search for something " + searchTerm;
		}
	}
		@RequestMapping("/users/1")
		public String displayUserDetails() {
			return "Showing information about user # 1 here";
	
	}
		@RequestMapping("/users/{user_id}")
		public String displayUserDetails1(@PathVariable("user_id") String idOfUser) {
			return "Showing information about user # " + idOfUser + " here";
	
	}
	
}
 