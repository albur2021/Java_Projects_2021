package com.buran.languages.controllers;

import java.util.List;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.buran.languages.models.Language;
import com.buran.languages.services.LangService;



@Controller
public class LangControllerTemplates {
	
private final LangService langService;
	
	public LangControllerTemplates(LangService langService) {
		this.langService=langService;
	}
	@RequestMapping("/lang")
	public String index(Model model, @ModelAttribute("lang") Language lang){
		List<Language> allLang = this.langService.allLang();
		model.addAttribute("allLang",allLang);
		return "home.jsp";
	}
	
	@PostMapping("/lang/create")
	public String create(Model model, @Valid @ModelAttribute("lang") Language lang, BindingResult result) {
		//create a pet object
//		Pet p = new Pet(name, age, ownerName);
//		return this.petService.createPet(p);
		
		if(result.hasErrors()) {
			List<Language> allLang = this.langService.allLang();
			model.addAttribute("allLang",allLang);
			return "home.jsp";
		}else {
			this.langService.createLang(lang);
			return "redirect:/lang";
		}
		
	}
	
	@GetMapping("/lang/{id}")
	public String getOneLang(@PathVariable("id") Long id, Model model) {
		Language l = this.langService.getOneLang(id);
		model.addAttribute("langToShow",l);
		return "langinfo.jsp";
		
		
	}
	
	@GetMapping("/lang/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		Language l = this.langService.getOneLang(id);
		model.addAttribute("lang",l);
		return "edit.jsp";
	}
	
	@PostMapping("/lang/{id}/update")
	public String update(@Valid @ModelAttribute("lang") Language lang, BindingResult result) {
		if(result.hasErrors()) {
			return "edit.jsp";
		}else {
			this.langService.updateLang(lang);
			return "redirect:/lang";
		}
	}
	
	@GetMapping("/lang/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		this.langService.deleteLang(id);
		return "redirect:/lang";
	}

}
