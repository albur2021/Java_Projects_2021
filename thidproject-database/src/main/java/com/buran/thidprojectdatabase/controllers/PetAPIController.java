package com.buran.thidprojectdatabase.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buran.thidprojectdatabase.models.Pet;
import com.buran.thidprojectdatabase.services.PetService;

@RestController
public class PetAPIController {
	
	private final PetService petService;
	
	public PetAPIController(PetService petService) {
		this.petService=petService;
	}
	@RequestMapping("/api/pets")
	public List<Pet> index(){
		return this.petService.allPets();
	}
	
	@PostMapping("/api/pets/create")
	public Pet create(@RequestParam(value="name") String name, @RequestParam(value="age") int age,@RequestParam(value="ownerName") String ownerName) {
		//create a pet object
		Pet p = new Pet(name, age, ownerName);
		return this.petService.createPet(p);
	}
	
	@GetMapping("/api/pet/{id}")
	public Pet getOnePet(@PathVariable("id") Long id) {
		return this.petService.getOnePet(id);
	}
	
	

}
