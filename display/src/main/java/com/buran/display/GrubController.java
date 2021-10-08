package com.buran.display;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GrubController {
	@RequestMapping("/")
	public String helloGrubHub(Model model) {
		String name="Roberto";
		
		
		model.addAttribute("nameVariable",name);
		
		model.addAttribute("numbBelts", 2);//make sure to pick 'name + object value
		
		return "grubHome.jsp";
	}
	
	@RequestMapping("/newOrder")
	public String newOrder(Model model) {
		//Dispaying a current Date, make sure to pick right package://Java .util
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		System.out.println(formatter.format(date));
		model.addAttribute("dateInfo",formatter.format(date));
		return "orderForm.jsp";
	}
	
	@RequestMapping(value="/submitOrder", method=RequestMethod.POST)
	public String completeOrder(@RequestParam(value="nameVariable") String orderName, @RequestParam(value="spiceLevel") String spiceLevel, @RequestParam(value="ccNum") String creditCardNum, Model model){
		System.out.println("***********");
		System.out.println(orderName +  ":" +  spiceLevel + ":" + creditCardNum);
		model.addAttribute("orderName", orderName);
		model.addAttribute("spiceLevel", spiceLevel);
		model.addAttribute("ccNumber", creditCardNum);
		return "orderDetails.jsp";
		
	}


}
