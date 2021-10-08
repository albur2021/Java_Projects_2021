package com.buran.thidprojectdatabase.services;

import java.util.List;



import org.springframework.stereotype.Service;

import com.buran.thidprojectdatabase.models.Pet;
import com.buran.thidprojectdatabase.repositories.PetRepository;

@Service
public class PetService {
	
	private final PetRepository petRepo;
	
	public PetService(PetRepository petRepo) {
		this.petRepo=petRepo;
	}
	
	//find all the pets
	public List<Pet> allPets(){
		return this.petRepo.findAll();
	}
	
	//create a pet
	public Pet createPet(Pet p) {
		return this.petRepo.save(p);
	}
	
	//get one pet
	public Pet getOnePet(Long id) {
		return this.petRepo.findById(id).orElse(null);
	}
	
	public Pet updatePet(Pet p) {
		return this.petRepo.save(p);
	}
	
	public void deletePet(Long id) {
		this.petRepo.deleteById(id);
	}

}
