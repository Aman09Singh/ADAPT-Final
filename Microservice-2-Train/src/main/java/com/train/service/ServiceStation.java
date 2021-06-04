package com.train.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.train.model.ModelStation;
import com.train.repository.RepositoryStation;

@Service
public class ServiceStation {

	@Autowired
	RepositoryStation stationRepo;
	
	public Optional<ModelStation> createStation(ModelStation station){
        return Optional.of(stationRepo.save(station));
    }

    public Optional<ModelStation> deleteStation(String code){
        Optional<ModelStation> station = stationRepo.findByCode(code);
        	if(station.isPresent()){
            stationRepo.deleteByCode(code);
            return station;
        }
        return Optional.empty();
    }

    public Optional<ModelStation> updateStation(String id, ModelStation station) {
        Optional<ModelStation> stationData = stationRepo.findById(id);
        if(stationData.isPresent()){
            station.setId(id);
            return Optional.of(stationRepo.save(station));
        }
        return Optional.empty();
    }

    public Optional<ModelStation> getStationById(String id){
        return stationRepo.findById(id);
    }

    public Optional<ModelStation> getStationByCode(String stationCode){
        Optional<ModelStation> station = stationRepo.findByCode(stationCode);
        return station;
    }
    
    public Optional<List<ModelStation>> getAll(){
    	
    	return Optional.ofNullable(stationRepo.findAll());
    }
}
