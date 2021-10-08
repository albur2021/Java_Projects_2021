package com.buran.ExamProject.controllers;



import java.util.List;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.buran.ExamProject.models.Idea;
import com.buran.ExamProject.models.LoginUserObject;
import com.buran.ExamProject.models.User;
import com.buran.ExamProject.services.UserService;




@Controller
public class UserController {
	// *** Had Errors because of keeping this same Route here with same Routes above,
	// *** Browser got confused which one is needed to return
//	@GetMapping("/")
//	public String home() {
//		return "index.jsp";
//	}
//	
	
	 
    @Autowired
    private UserService userServ;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUserObject());
        return "index.jsp";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
    		
        BindingResult result, Model model, HttpSession session) {
        userServ.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUserObject());
            return "index.jsp";
        }
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/home";
    }
    
    //Coming from 'LoginUerObject' model
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUserObject newLogin, 
         BindingResult result, Model model, HttpSession session) {
        User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        
        //if the form was filled out successfully, 
        //then create a cookie to keep track of this users id in session
        session.setAttribute("user_id", user.getId());
        return "redirect:/home";
    }
    // Creating a new Dashboard.jsp w/Logged in User
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
    	// make sure to have it here, otherwise, 'Logout' features will throw error
    	if(session.getAttribute("user_id") == null) {
    		return "redirect:/";
    	}
    	//use the session to retrieve the id of the logged in user or newly reg.user
    	Long loggedInId = (Long) session.getAttribute("user_id");
    	//use the retrieved Id to find a user from the DB who has that id
    	// so we can send that user's info to the template
    	User loggedInUserObject=this.userServ.findOneUser(loggedInId);
    	//passing object to jsp file by model
    	model.addAttribute("loggedInUser",loggedInUserObject);
    	
    	
    	//passing all the menu items to the template. First we need to get the menu item
    	//by using the Service
    	List<Idea> allIdeasItems = this.userServ.findAllIdeas();
    	model.addAttribute("allIdeasItems",allIdeasItems);
    	return "dashboarduser.jsp";
    }
    
    
    //Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("user_id");
    	return "redirect:/";
    }
    
 
//    //Creating menu Details
    @GetMapping("/ideas/{id}/info")
    public String showManyItem(@PathVariable("id") Long id, Model model) {
    	//retrieve a menu object using this id variable
    	Idea ideaObj=this.userServ.findOneIdeaItem(id);
    	model.addAttribute("ideaObj", ideaObj);
    	return "oneItem.jsp";
    }
    
    @GetMapping("/ideas/new")
    public String newMenuItem(@ModelAttribute("idea") Idea idea) {
    	return "uploadedMenuItem.jsp";
    }
    
   
    
    @PostMapping("/ideas/create")
    public String createMenuItem(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session) {
    	if(result.hasErrors()) {
    		return "uploadedMenuItem.jsp";
    	}else {
    		//get the logged user using session so that we can assign the logged in user
    		//as the uploader of the menu itme
    		Long idOfLoggedInUser =(Long)session.getAttribute("user_id");
    		User loggedInUserObj = this.userServ.findOneUser(idOfLoggedInUser);
    		//assign the menu's uploader to be the logged in user object
    		idea.setUploader(loggedInUserObj);
    		this.userServ.createIdea(idea);
    		return "redirect:/home";
    	 }
    	
    }
    
    
    @GetMapping("/ideas/{id}/edit")
    public String editMenuItem(@PathVariable("id") Long id, Model model) {
    	Idea ideaObj = this.userServ.findOneIdeaItem(id);
    	model.addAttribute("ideaObj",ideaObj);
    	return "edit.jsp";
    }
    
    
 
    @PostMapping("/ideas/{id}/udpate")
    public String updateMenuItem(@PathVariable("id") Long id, @Valid @ModelAttribute("menuObj") Idea idea, BindingResult result) {
    	if(result.hasErrors()) {
    		return "edit.jsp";
    	}else {
    		
    		//get the original men object from DB using the id from the PathVariable
    		Idea oIdea = this.userServ.findOneIdeaItem(id);
    		//set the menu object that came from the form's updloader to the original uploader from the original menu object from the 
    		//original menu object data base
    		idea.setUploader(oIdea.getUploader());
    		this.userServ.updateOneIdeaItem(idea);
    		return "redirect:/home";
    	}
    	
    	
    	
    }
    
    @GetMapping("/ideas/{id}/delete")
	public String deleteMenuItem(@PathVariable("id") Long id) {
		this.userServ.deleteIdeaItem(id);
		return "redirect:/home";
	}
    
    
    
}
