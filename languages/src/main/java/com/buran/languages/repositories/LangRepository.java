package com.buran.languages.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.buran.languages.models.Language;


									// Language has access to LangDB	
public interface LangRepository  extends CrudRepository<Language, Long>{
	
	   List<Language> findAll();

}
