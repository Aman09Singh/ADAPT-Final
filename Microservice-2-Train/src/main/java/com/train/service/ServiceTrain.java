package com.train.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.train.model.ModelTrain;
import com.train.repository.RepositoryTrain;

@Service
public class ServiceTrain {

	@Autowired
	RepositoryTrain trainRepo;
	
	public Optional<ModelTrain> createTrain(ModelTrain train){
        return Optional.of(trainRepo.save(train));
    }

    public Optional<ModelTrain> updateTrain(String id, ModelTrain train){
        Optional<ModelTrain> trainData = trainRepo.findById(id);
        if(trainData.isPresent()){
            train.setId(id);
            return Optional.of(trainRepo.save(train));
        }
        return Optional.empty();
    }

    public Optional<ModelTrain> deleteTrain(String trainId){
        Optional<ModelTrain> train = trainRepo.findById(trainId);
        if(train.isPresent()){
            trainRepo.deleteById(trainId);
            return train;
        }
        return Optional.empty();
    }

    public Optional<ModelTrain> getTrainByNumber(String number){
        return trainRepo.findByNumber(number);
    }

    public Optional<ModelTrain> getTrainById(String trainId){
    	System.out.println(trainId);
        return trainRepo.findById(trainId);
    }

    public List<ModelTrain> searchTrainsByFromStationCodeAndToStationCode(String fromStationCode, String toStationCode) {
        return trainRepo.findTrainByFromStationCodeAndToStationCode(fromStationCode, toStationCode);
    }
    
    public Optional<List<ModelTrain>> getAll(){
    	return Optional.ofNullable(trainRepo.findAll());
    }
}
