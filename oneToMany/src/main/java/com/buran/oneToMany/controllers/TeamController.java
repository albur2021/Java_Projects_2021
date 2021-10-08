package com.buran.oneToMany.controllers;

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


import com.buran.oneToMany.models.Player;
import com.buran.oneToMany.models.Team;
import com.buran.oneToMany.services.AppService;

@Controller
public class TeamController {
	
	//connecting w/Services
	private final AppService appService;
	//Injection in Constructor
	public TeamController(AppService appService) {
		this.appService=appService;
	}
	
	
	@RequestMapping("/")
	public String WelcomeToTeam(Model model, @ModelAttribute("team") Team team){
		List<Team> allTeams = this.appService.findAllTeams();
		model.addAttribute("allTeams",allTeams);
		return "index.jsp";
	}
	
	@PostMapping("/teams/create")
	public String createNewTeam(@Valid @ModelAttribute("team") Team team, BindingResult result, Model model ) {
		if(result.hasErrors()){
			List<Team> allTeams = this.appService.findAllTeams();
			model.addAttribute("allTeams",allTeams);
			return "index.jsp";
		}else {
			this.appService.createTeam(team);
			return "redirect:/";
		}
	}
	
	@GetMapping("/players/new")
	public String newPlayer(@ModelAttribute Player player, Model model ) {
		List<Team> allTeams = this.appService.findAllTeams();
		model.addAttribute("allTeams",allTeams);
		return "newPlayer.jsp";
	}
	
	@PostMapping("/players/create")
	public String createPlayer(@Valid @ModelAttribute("player") Player player, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "newPlayer.jsp";
		}else {
			this.appService.createPlayer(player);
			return "redirect:/";
		}
		
		
	}
	
	@GetMapping("/teams/{id}/info")
	public String teamInfo(@PathVariable("id") Long id, Model model) {
		Team t = this.appService.getTeam(id);
		
		model.addAttribute("teamtoshow",t);
		return "teaminfo.jsp";
	}
	
	@GetMapping("/teams/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		Team t = this.appService.getTeam(id);
		model.addAttribute("AllTeams",t);
		return "edit.jsp";
	}
	
	@PostMapping("/teams/{id}/update")
	public String updateTeam(@Valid @ModelAttribute("team") Team team, BindingResult result) {
		if(result.hasErrors()) {
			return "edit.jsp";
		}else {
			this.appService.updateTeam(team);
			return "redirect:/";
		}
	}
	
	@GetMapping("/teams/{id}/delete")
	public String deleteTeam(@PathVariable("id") Long id) {
		this.appService.deleteTeam(id);
		return "redirect:/";
	}

	
	

}
