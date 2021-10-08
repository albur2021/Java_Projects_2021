package com.buran.NinjaAndDojo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.buran.NinjaAndDojo.models.Dojo;
import com.buran.NinjaAndDojo.models.Ninja;
import com.buran.NinjaAndDojo.services.DojoAndNinjaService;


@Controller
public class DojoController {
	
	  @Autowired
	  private DojoAndNinjaService dojoAndServer;
	  
	  //get all dojo
	@GetMapping("/")
	public String welcomeToDojo(Model model, @ModelAttribute("dojo") Dojo dojo) {
		 List<Dojo>getAllDojos = this.dojoAndServer.allDojos();
		 model.addAttribute("allDojos",getAllDojos);
		return "home.jsp";
	}
	
	@PostMapping("/dojos/create")
	public String createDojo(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result, Model model) {
		if(result.hasErrors()) {
			 List<Dojo>getAllDojos = this.dojoAndServer.allDojos();
			 model.addAttribute("allDojos",getAllDojos);
			return "home.jsp";
		}else {
			this.dojoAndServer.createDojo(dojo);
			return "redirect:/";
		}
	}
	
	@GetMapping("/create/newNinja")
	public String newPlayer(@ModelAttribute("ninja") Ninja ninja, Model model) {
		List<Dojo>getAllDojos = this.dojoAndServer.allDojos();
		 model.addAttribute("allDojos",getAllDojos);
		return "newNinja.jsp";
	}
	
	@PostMapping("/ninjas/create")
	public String createNinja(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "newNinja.jsp";
		}else {
			this.dojoAndServer.createNinja(ninja);
			return "redirect:/";
		}
	}
	
	@GetMapping("/dojos/{id}/info")
	public String dojoInfo(@PathVariable("id") Long id, Model model) {
		Dojo d = this.dojoAndServer.getOneDojo(id);
		model.addAttribute("dojoInfo",d);
		return "displayDojo.jsp";
	}
	
	
	
	
	
	
}
