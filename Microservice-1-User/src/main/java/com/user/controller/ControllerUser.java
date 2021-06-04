package com.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.exception.UserNotFoundException;
import com.user.model.ModelUser;
import com.user.service.ServiceUser;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class ControllerUser {

	@Autowired
	ServiceUser userService;
	
	@Bean
    private BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
	@PostMapping("/signup")
    public ModelUser registerUser(@RequestBody ModelUser user) {
        System.out.println(user);
        user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        userService.createUser(user);
//        roleService.createRole(user.getRole());
        return user;
    }
	
	@PutMapping("/update/{id}")
    public ResponseEntity<ModelUser> updateUser(@PathVariable String id, @RequestBody ModelUser user) {
        System.out.println(user);
        Optional<ModelUser> userData = userService.updateUser(id, user);
        if (userData.isPresent()) {
            return ResponseEntity.ok(userData.get());
        } else throw new UserNotFoundException("User with id " + id + " was not found.");
    }
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<ModelUser> deleteUser(@PathVariable String id) {
        Optional<ModelUser> user = userService.deleteUser(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else throw new UserNotFoundException("User with id " + id + " was not found.");
    }

    @GetMapping("/get-by-email/{email}")
    public ResponseEntity<ModelUser> getUserByEmail(@PathVariable String email) {
        System.out.println("user");
        Optional<ModelUser> user = userService.findUserByEmail(email);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }else throw new UserNotFoundException("User with email " + email + " was not found.");
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ModelUser> getUserById(@PathVariable String id) {
        Optional<ModelUser> user = userService.findUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }else throw new UserNotFoundException("User with id " + id + " was not found.");
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<ModelUser>> getAllStation(){
    	Optional<List<ModelUser>> allStation = userService.getAll();
    	if(allStation.isPresent()) {
    		return ResponseEntity.ok(allStation.get());
    	}
    	else {
    		throw new UserNotFoundException("Stations are not found");
    	}
    }
}
