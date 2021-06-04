package com.book.model;

import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("train")
public class ModelTrain {

	@Id
    private String id;
    @Indexed
    private String number;
    private String name;
    private String fromStationCode;
    private String toStationCode;
    private int firstAcSeats;
    private int secondAcSeats;
    private int thirdAcSeats;
    private int firstClassSeats;
    private int chairCarSeats;
    private int sleeperSeats;
    private int durationHrs;
    private int durationMns;
    private LocalTime departure;
    private LocalTime arrival;
    private int distance;
    
    public ModelTrain() {
    	
    }
    
	public ModelTrain(String id, String number, String name, String fromStationCode, String toStationCode,
			int firstAcSeats, int secondAcSeats, int thirdAcSeats, int firstClassSeats, int chairCarSeats,
			int sleeperSeats, int durationHrs, int durationMns, LocalTime departure, LocalTime arrival, int distance) {
		super();
		this.id = id;
		this.number = number;
		this.name = name;
		this.fromStationCode = fromStationCode;
		this.toStationCode = toStationCode;
		this.firstAcSeats = firstAcSeats;
		this.secondAcSeats = secondAcSeats;
		this.thirdAcSeats = thirdAcSeats;
		this.firstClassSeats = firstClassSeats;
		this.chairCarSeats = chairCarSeats;
		this.sleeperSeats = sleeperSeats;
		this.durationHrs = durationHrs;
		this.durationMns = durationMns;
		this.departure = departure;
		this.arrival = arrival;
		this.distance = distance;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFromStationCode() {
		return fromStationCode;
	}
	public void setFromStationCode(String fromStationCode) {
		this.fromStationCode = fromStationCode;
	}
	public String getToStationCode() {
		return toStationCode;
	}
	public void setToStationCode(String toStationCode) {
		this.toStationCode = toStationCode;
	}
	public int getFirstAcSeats() {
		return firstAcSeats;
	}
	public void setFirstAcSeats(int firstAcSeats) {
		this.firstAcSeats = firstAcSeats;
	}
	public int getSecondAcSeats() {
		return secondAcSeats;
	}
	public void setSecondAcSeats(int secondAcSeats) {
		this.secondAcSeats = secondAcSeats;
	}
	public int getThirdAcSeats() {
		return thirdAcSeats;
	}
	public void setThirdAcSeats(int thirdAcSeats) {
		this.thirdAcSeats = thirdAcSeats;
	}
	public int getFirstClassSeats() {
		return firstClassSeats;
	}
	public void setFirstClassSeats(int firstClassSeats) {
		this.firstClassSeats = firstClassSeats;
	}
	public int getChairCarSeats() {
		return chairCarSeats;
	}
	public void setChairCarSeats(int chairCarSeats) {
		this.chairCarSeats = chairCarSeats;
	}
	public int getSleeperSeats() {
		return sleeperSeats;
	}
	public void setSleeperSeats(int sleeperSeats) {
		this.sleeperSeats = sleeperSeats;
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
	public LocalTime getDeparture() {
		return departure;
	}
	public void setDeparture(LocalTime departure) {
		this.departure = departure;
	}
	public LocalTime getArrival() {
		return arrival;
	}
	public void setArrival(LocalTime arrival) {
		this.arrival = arrival;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
    
    
}
