package com.buran.oneToMany.services;

import java.util.List;


import org.springframework.stereotype.Service;


import com.buran.oneToMany.models.Player;
import com.buran.oneToMany.models.Team;
import com.buran.oneToMany.repositories.PlayerRepository;
import com.buran.oneToMany.repositories.TeamRepository;

@Service
public class AppService {
	
	//need to tell services about the repositories
	private final TeamRepository teamRepo;
	
	private final PlayerRepository playerRepo;
	
	//Constructor 
	public AppService(TeamRepository teamRepo, PlayerRepository playerRepo) {
		
		this.teamRepo = teamRepo;
		this.playerRepo = playerRepo;
	}
	
	//get all teams
	public List<Team> findAllTeams(){
		return (List<Team>)this.teamRepo.findAll();//(List<Team>) Casting
	}
	
	//create a team
	public Team createTeam(Team t) {
		return this.teamRepo.save(t);
	}
	
	//create a player
	public Player createPlayer(Player p) {
		return this.playerRepo.save(p);
	}
	
	//get one team info
	public Team getTeam(Long id) {
		return this.teamRepo.findById(id).orElse(null);
	}
	
	
	public Team updateTeam(Team t) {
		return this.teamRepo.save(t);
	}
	
	public void deleteTeam(Long id) {
		this.teamRepo.deleteById(id);//deleteById comes from Repo/Crud
	}
}
