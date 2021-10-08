package com.buran.languages.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.buran.languages.models.Language;
import com.buran.languages.repositories.LangRepository;


@Service
public class LangService {
	
private final LangRepository langRepo;//needs to Communicate w/Repository 
	
	public LangService(LangRepository langRepo) {
		this.langRepo=langRepo;// needs to initialize Repo/object w/contructor's param
	}
	
	//find all the languages
	public List<Language> allLang(){
		return this.langRepo.findAll();//findAll comes from LangRepository, no needs to cascated
	}
	
	//create a language
	public Language createLang(Language l) {
		return this.langRepo.save(l);
	}
	
	//get one language
	public Language getOneLang(Long id) {
		return this.langRepo.findById(id).orElse(null);
	}
	
	public Language updateLang(Language l) {
		return this.langRepo.save(l);
	}
	
	public void deleteLang(Long id) {
		this.langRepo.deleteById(id);//deleteById comes from Repo/Crud
	}

}
