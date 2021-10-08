package com.buran.NinjaAndDojo.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.buran.NinjaAndDojo.models.Dojo;
import com.buran.NinjaAndDojo.models.Ninja;
import com.buran.NinjaAndDojo.repositories.DojoRepository;
import com.buran.NinjaAndDojo.repositories.NinjaRepository;

@Service
public class DojoAndNinjaService {
	
	

	  private final DojoRepository dojoRepo;
	
	  private final NinjaRepository ninjaRepo;
	  
	 public DojoAndNinjaService(DojoRepository dojoRepo, NinjaRepository ninjaRepo) {
		 this.dojoRepo=dojoRepo;
		 this.ninjaRepo=ninjaRepo;
	 }
	    
	    public List<Dojo>allDojos(){
	        return (List<Dojo>) this.dojoRepo.findAll();
	    } public Dojo createDojo(Dojo d) {
	        return this.dojoRepo.save(d);
	    }
	    public Dojo getOneDojo(Long id) {
	    	return this.dojoRepo.findById(id).orElse(null);
	    }
	    public Dojo update(Dojo d) {
	        return this.dojoRepo.save(d);
	    }
	    public void deleteOne1(Long id) {
	        this.dojoRepo.deleteById(id);
	    }

	    
	  
	    
	  
	    public List<Ninja>allNinjas(){
	        return (List<Ninja>) this.ninjaRepo.findAll();
	    } public Ninja createNinja(Ninja n) {
	        return this.ninjaRepo.save(n);
	    }
	    public Ninja getOneNinja(Long id) {
	        return ((DojoAndNinjaService) this.ninjaRepo).getOneNinja(id);    
	    }
	    public Ninja update(Ninja n) {
	        return this.ninjaRepo.save(n);
	    }
	    public void deleteOne(Long id) {
	        this.ninjaRepo.deleteById(id);
	    }

}
