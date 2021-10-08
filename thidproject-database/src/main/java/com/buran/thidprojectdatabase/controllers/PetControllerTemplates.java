package com.buran.thidprojectdatabase.controllers;

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

import com.buran.thidprojectdatabase.models.Pet;
import com.buran.thidprojectdatabase.services.PetService;



@Controller

public class PetControllerTemplates {
	
private final PetService petService;
	
	public PetControllerTemplates(PetService petService) {
		this.petService=petService;
	}
	@RequestMapping("/pets")
	public String index(Model model, @ModelAttribute("pet") Pet pet){
		List<Pet> allPets = this.petService.allPets();
		model.addAttribute("allPets",allPets);
		return "home.jsp";
	}
	
	@PostMapping("/pets/create")
	public String create(Model model, @Valid @ModelAttribute("pet") Pet pet, BindingResult result) {
		//create a pet object
//		Pet p = new Pet(name, age, ownerName);
//		return this.petService.createPet(p);
		
		if(result.hasErrors()) {
			List<Pet> allPets = this.petService.allPets();
			model.addAttribute("allPets",allPets);
			return "home.jsp";
		}else {
			this.petService.createPet(pet);
			return "redirect:/pets";
		}
		
	}
	
	@GetMapping("/pets/{id}")
	public String getOnePet(@PathVariable("id") Long id, Model model) {
		Pet p = this.petService.getOnePet(id);
		model.addAttribute("petToShow",p);
		return "petinfo.jsp";
		
		
	}
	
	@GetMapping("/pets/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		Pet p = this.petService.getOnePet(id);
		model.addAttribute("pet",p);
		return "edit.jsp";
	}
	
	@PostMapping("/pets/{id}/update")
	public String update(@Valid @ModelAttribute("pet") Pet pet, BindingResult result) {
		if(result.hasErrors()) {
			return "edit.jsp";
		}else {
			this.petService.updatePet(pet);
			return "redirect:/pets";
		}
	}
	
	@GetMapping("/pets/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		this.petService.deletePet(id);
		return "redirect:/pets";
	}

}
