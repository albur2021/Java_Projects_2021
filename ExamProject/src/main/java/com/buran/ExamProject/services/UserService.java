package com.buran.ExamProject.services;


import java.util.List;

import java.util.Optional;
    
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.buran.ExamProject.models.Idea;
import com.buran.ExamProject.models.LoginUserObject;
import com.buran.ExamProject.models.User;
import com.buran.ExamProject.repositories.IdeaRepository;
import com.buran.ExamProject.repositories.UserRepository;



    

    
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
    private IdeaRepository ideaRepo;
    
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
    
    // method for the Idea operations
    public List<Idea> findAllIdeas(){
    	return (List<Idea>)this.ideaRepo.findAll();
    }
    
    //create a Idea item
    public Idea createIdea(Idea id) {
    	return this.ideaRepo.save(id);
    }
    
    //find One Idea Item
    public Idea findOneIdeaItem(Long id) {
    	return this.ideaRepo.findById(id).orElse(null);
    }
    
    public Idea updateOneIdeaItem(Idea id) {
    	return this.ideaRepo.save(id);
    }
    
    
    //delete one item
    public void deleteIdeaItem(Long id) {
    	this.ideaRepo.deleteById(id);
    }
}