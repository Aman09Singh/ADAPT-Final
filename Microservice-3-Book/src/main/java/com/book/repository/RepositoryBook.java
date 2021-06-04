package com.book.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.book.model.ModelBook;

public interface RepositoryBook extends MongoRepository<ModelBook, String>{

	@SuppressWarnings("unchecked")
	public ModelBook save(ModelBook book);
}
