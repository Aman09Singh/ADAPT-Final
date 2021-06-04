package com.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.user.model.ModelUser;
import com.user.repository.RepositoryUser;

@Service
public class ServiceUser {

	@Autowired
	private RepositoryUser userRepository;
	
	public ModelUser createUser(ModelUser user) {
        return userRepository.save(user);
    }

    public Optional<ModelUser> updateUser(String id, ModelUser user) {
        Optional<ModelUser> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            user.setId(id);
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    public Optional<ModelUser> deleteUser(String id) {
        Optional<ModelUser> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.deleteById(id);
            return user;
        }
        return Optional.empty();
    }

    public Optional<ModelUser> findUserById(String id) {
        return userRepository.findById(id);
    }

    public Optional<ModelUser> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public Optional<List<ModelUser>> getAll(){
    	
    	return Optional.ofNullable(userRepository.findAll());
    }

}
