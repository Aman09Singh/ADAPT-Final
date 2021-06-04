package com.user.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.user.model.ModelUser;

@Repository
public interface RepositoryUser extends MongoRepository<ModelUser, String>{

	public Optional<ModelUser> findByEmail(String email);
}
