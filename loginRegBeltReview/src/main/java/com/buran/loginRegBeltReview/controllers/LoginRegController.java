package com.buran.loginRegBeltReview.controllers;

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


import com.buran.loginRegBeltReview.models.LoginUserObject;
import com.buran.loginRegBeltReview.models.Menu;
import com.buran.loginRegBeltReview.models.User;
import com.buran.loginRegBeltReview.services.UserService;

@Controller
public class LoginRegController {
	// *** Had Errors because of keeping this same Route here with same Routes above,
	// *** Browser cot confused wich one is needed to return
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
    	List<Menu> allMenuItems = this.userServ.findAllMenuItems();
    	model.addAttribute("allMenuItems",allMenuItems);
    	return "dashboarduser.jsp";
    }
    
    
    //Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("user_id");
    	return "redirect:/";
    }
    
 
//    //Creating menu Details
    @GetMapping("/menu/{id}/info")
    public String showManyItem(@PathVariable("id") Long id, Model model) {
    	//retrieve a menu object using this id variable
    	Menu menuObj=this.userServ.findOneMenuItem(id);
    	model.addAttribute("menuObj", menuObj);
    	return "oneItem.jsp";
    }
    
    @GetMapping("/menu/new")
    public String newMenuItem(@ModelAttribute("menu") Menu menu) {
    	return "uploadedMenuItem.jsp";
    }
    
   
    
    @PostMapping("/menu/create")
    public String createMenuItem(@Valid @ModelAttribute("menu") Menu menu, BindingResult result, HttpSession session) {
    	if(result.hasErrors()) {
    		return "uploadedMenuItem.jsp";
    	}else {
    		//get the logged user using session so that we can assign the logged in user
    		//as the uploader of the menu itme
    		Long idOfLoggedInUser =(Long)session.getAttribute("user_id");
    		User loggedInUserObj = this.userServ.findOneUser(idOfLoggedInUser);
    		//assign the menu's uploader to be the logged in user object
    		menu.setUploader(loggedInUserObj);
    		this.userServ.createMenuItem(menu);
    		return "redirect:/home";
    	 }
    	
    }
    
    
    @GetMapping("/menu/{id}/edit")
    public String editMenuItem(@PathVariable("id") Long id, Model model) {
    	Menu menuObj = this.userServ.findOneMenuItem(id);
    	model.addAttribute("menuObj",menuObj);
    	return "edit.jsp";
    }
    
    
 
    @PostMapping("/menu/{id}/udpate")
    public String updateMenuItem(@PathVariable("id") Long id, @Valid @ModelAttribute("menuObj") Menu menu, BindingResult result) {
    	if(result.hasErrors()) {
    		return "edit.jsp";
    	}else {
    		
    		//get the original men object from DB using the id from the PathVariable
    		Menu oGMObject = this.userServ.findOneMenuItem(id);
    		//set the menu object that came from the form's updloader to the original uploader from the original menu object from the 
    		//original menu object data base
    		menu.setUploader(oGMObject.getUploader());
    		this.userServ.updateOneMenuItem(menu);
    		return "redirect:/home";
    	}
    	
    	
    	
    }
    
    @GetMapping("/menu/{id}/delete")
	public String deleteMenuItem(@PathVariable("id") Long id) {
		this.userServ.deleteMenuItem(id);
		return "redirect:/home";
	}
    
    
    
}
