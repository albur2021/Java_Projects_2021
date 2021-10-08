package com.buran.languages.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buran.languages.models.Language;
import com.buran.languages.services.LangService;


@RestController
public class LangApiController {
	
	
	private final LangService langService;
	
	public LangApiController(LangService langService) {
		this.langService=langService;
	}
	@RequestMapping("/api/lang")
	public List<Language> index(){
		return this.langService.allLang();
	}
	
	@PostMapping("/api/lang/create")
	public Language create(@RequestParam(value="name") String name, @RequestParam(value="creator") String creator, @RequestParam(value="version") Double version) {
		//create a Language object "l" to return, since we have return type Language
		Language l = new Language(name, creator, version);// "l" calls constructor
		return this.langService.createLang(l);
	}
	
	@GetMapping("/api/lang/{id}")
	public Language getOnePet(@PathVariable("id") Long id) {
		return this.langService.getOneLang(id);
	}

}
