package com.buran.loginRegBeltReview.services;
import java.util.List;

import java.util.Optional;
    
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.buran.loginRegBeltReview.models.LoginUserObject;
import com.buran.loginRegBeltReview.models.Menu;
import com.buran.loginRegBeltReview.models.User;
import com.buran.loginRegBeltReview.repostiories.MenuRepository;
import com.buran.loginRegBeltReview.repostiories.UserRepository;

    

    
@Service
public class UserService {
	
//	private final UserRepository userRepo;
//	
//	public UserService(UserRepository userRepo) {
//		this.userRepo=userRepo;
//	}
//    
    @Autowired
    private UserRepository userRepo;// short way of binding Repo with Service
    
    
    @Autowired
    private MenuRepository menuRepo;
    
    public User register(User newUser, BindingResult result) {
    	//validate that the NewUser object's email doesn't exist in the DB already!
		//if it already exist add another validation error message
        if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
            result.rejectValue("email", "Unique", "This email is already in use!");
        }
        if(!newUser.getPassword().equals(newUser.getConfirm())) {
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
        }
        if(result.hasErrors()) {
            return null;
        } else {
            String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
            newUser.setPassword(hashed);
            return userRepo.save(newUser);
        }
    }
    
    public User login(LoginUserObject newLogin, BindingResult result) {
        if(result.hasErrors()) {
            return null;
        }
        //search the DB for a user who has the same email as the one typed in the login form (newLogin.getEmail)
        Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
        // if the email is not found, create a custom validation error for "unknown email"
        if(!potentialUser.isPresent()) {
            result.rejectValue("email", "Unique", "Unknown email!");
            return null;
        }
        // if object is found, that means 'email' is found
        User user = potentialUser.get();
        // using 'BCrypt' to check if the password for that account matches the PasSward stored in the DB
        if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
        	//if that password is matched, create an validated error message
            result.rejectValue("password", "Matches", "Invalid Password!");
        }
        if(result.hasErrors()) {
            return null;
        } else {
        	//at this point was found and password matches, successful login can occur
            return user;
        }
    }
    
    
    public User findOneUser(Long id) {
    	return this.userRepo.findById(id).orElse(null);
    }
    
    // method for the menu operations
    public List<Menu> findAllMenuItems(){
    	return (List<Menu>)this.menuRepo.findAll();
    }
    
    //create a menu item
    public Menu createMenuItem(Menu m) {
    	return this.menuRepo.save(m);
    }
    
    //find One Menu Item
    public Menu findOneMenuItem(Long id) {
    	return this.menuRepo.findById(id).orElse(null);
    }
    
    public Menu updateOneMenuItem(Menu m) {
    	return this.menuRepo.save(m);
    }
    
    
    //delete one item
    public void deleteMenuItem(Long id) {
    	this.menuRepo.deleteById(id);
    }
}