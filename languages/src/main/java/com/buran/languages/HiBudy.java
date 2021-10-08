package com.buran.languages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public final class HiBudy {
	@RequestMapping("/")
	public String greet() {
		return "home.jsp";
	}

}
