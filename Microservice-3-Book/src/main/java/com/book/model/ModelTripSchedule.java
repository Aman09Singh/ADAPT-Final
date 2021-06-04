package com.book.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document("trip_schedule")
public class ModelTripSchedule {

	@Id
    @Indexed
    private String id;
    @DateTimeFormat(pattern = "yyyy-mm-dd", iso = DateTimeFormat.ISO.NONE)
    private LocalDate tripDate;
    private int firstAcAvailableSeats;
    private int secondAcAvailableSeats;
    private int thirdAcAvailableSeats;
    private int FirstClassAcAvailableSeats;
    private int chairCarAcAvailableSeats;
    private int SleeperAvailableSeats;
    private String tripId;
    private String status;
    private String trainNo;
    
   
    public ModelTripSchedule() {
    	
    }
    

	public ModelTripSchedule(String id, LocalDate tripDate, int firstAcAvailableSeats, int secondAcAvailableSeats,
			int thirdAcAvailableSeats, int firstClassAcAvailableSeats, int chairCarAcAvailableSeats,
			int sleeperAvailableSeats, String tripId, String status, String trainNo) {
		super();
		this.id = id;
		this.tripDate = tripDate;
		this.firstAcAvailableSeats = firstAcAvailableSeats;
		this.secondAcAvailableSeats = secondAcAvailableSeats;
		this.thirdAcAvailableSeats = thirdAcAvailableSeats;
		FirstClassAcAvailableSeats = firstClassAcAvailableSeats;
		this.chairCarAcAvailableSeats = chairCarAcAvailableSeats;
		SleeperAvailableSeats = sleeperAvailableSeats;
		this.tripId = tripId;
		this.status = status;
		this.trainNo = trainNo;
	}



	public String getTrainNo() {
		return trainNo;
	}



	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public LocalDate getTripDate() {
		return tripDate;
	}



	public void setTripDate(LocalDate tripDate) {
		this.tripDate = tripDate;
	}



	public int getFirstAcAvailableSeats() {
		return firstAcAvailableSeats;
	}



	public void setFirstAcAvailableSeats(int firstAcAvailableSeats) {
		this.firstAcAvailableSeats = firstAcAvailableSeats;
	}



	public int getSecondAcAvailableSeats() {
		return secondAcAvailableSeats;
	}



	public void setSecondAcAvailableSeats(int secondAcAvailableSeats) {
		this.secondAcAvailableSeats = secondAcAvailableSeats;
	}



	public int getThirdAcAvailableSeats() {
		return thirdAcAvailableSeats;
	}



	public void setThirdAcAvailableSeats(int thirdAcAvailableSeats) {
		this.thirdAcAvailableSeats = thirdAcAvailableSeats;
	}



	public int getFirstClassAcAvailableSeats() {
		return FirstClassAcAvailableSeats;
	}



	public void setFirstClassAcAvailableSeats(int firstClassAcAvailableSeats) {
		FirstClassAcAvailableSeats = firstClassAcAvailableSeats;
	}



	public int getChairCarAcAvailableSeats() {
		return chairCarAcAvailableSeats;
	}



	public void setChairCarAcAvailableSeats(int chairCarAcAvailableSeats) {
		this.chairCarAcAvailableSeats = chairCarAcAvailableSeats;
	}



	public int getSleeperAvailableSeats() {
		return SleeperAvailableSeats;
	}



	public void setSleeperAvailableSeats(int sleeperAvailableSeats) {
		SleeperAvailableSeats = sleeperAvailableSeats;
	}



	public String getTripId() {
		return tripId;
	}



	public void setTripId(String tripId) {
		this.tripId = tripId;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	@Override
    public String toString() {
        return "TripSchedule{" +
                "id='" + id + '\'' +
                ", tripDate=" + tripDate +
                ", firstAcAvailableSeats=" + firstAcAvailableSeats +
                ", secondAcAvailableSeats=" + secondAcAvailableSeats +
                ", thirdAcAvailableSeats=" + thirdAcAvailableSeats +
                ", FirstClassAcAvailableSeats=" + FirstClassAcAvailableSeats +
                ", chairCarAcAvailableSeats=" + chairCarAcAvailableSeats +
                ", SleeperAvailableSeats=" + SleeperAvailableSeats +
                ", tripId='" + tripId + '\'' +
                '}';
    }
	
}
