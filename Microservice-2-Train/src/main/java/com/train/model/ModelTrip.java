package com.train.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("trip")
public class ModelTrip {
	@Id
    @Indexed
    private String tripId;
    private int baseFare;
    private int durationHrs;
    private int durationMns;
    private String sourceStationCode;
    private String destinationStationCode;
    private String trainNo;
    
    public ModelTrip() {
    	
    }
    
    public ModelTrip(String tripId, int baseFare, int durationHrs, int durationMns, String sourceStationCode,
			String destinationStationCode, String trainNo) {
		super();
		this.tripId = tripId;
		this.baseFare = baseFare;
		this.durationHrs = durationHrs;
		this.durationMns = durationMns;
		this.sourceStationCode = sourceStationCode;
		this.destinationStationCode = destinationStationCode;
		this.trainNo = trainNo;
	}



	public String getTripId() {
		return tripId;
	}



	public void setTripId(String tripId) {
		this.tripId = tripId;
	}



	public int getBaseFare() {
		return baseFare;
	}



	public void setBaseFare(int baseFare) {
		this.baseFare = baseFare;
	}



	public int getDurationHrs() {
		return durationHrs;
	}



	public void setDurationHrs(int durationHrs) {
		this.durationHrs = durationHrs;
	}



	public int getDurationMns() {
		return durationMns;
	}



	public void setDurationMns(int durationMns) {
		this.durationMns = durationMns;
	}



	public String getSourceStationCode() {
		return sourceStationCode;
	}



	public void setSourceStationCode(String sourceStationCode) {
		this.sourceStationCode = sourceStationCode;
	}



	public String getDestinationStationCode() {
		return destinationStationCode;
	}



	public void setDestinationStationCode(String destinationStationCode) {
		this.destinationStationCode = destinationStationCode;
	}



	public String getTrainNo() {
		return trainNo;
	}



	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}



	@Override
    public String toString() {
        return "Trip{" +
                "tripId='" + tripId + '\'' +
                ", baseFare=" + baseFare +
                ", durationHrs=" + durationHrs +
                ", durationMns=" + durationMns +
                ", sourceStationCode='" + sourceStationCode + '\'' +
                ", destinationStationCode='" + destinationStationCode + '\'' +
                ", trainNo='" + trainNo + '\'' +
                '}';
    }

    
}
