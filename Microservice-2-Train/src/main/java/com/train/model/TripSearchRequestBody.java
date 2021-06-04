package com.train.model;

public class TripSearchRequestBody {

	private String srcStationCode;
    private String destStationCode;
    
    public TripSearchRequestBody() {
    	
    }
    
	public String getSrcStationCode() {
		return srcStationCode;
	}
	public void setSrcStationCode(String srcStationCode) {
		this.srcStationCode = srcStationCode;
	}
	public String getDestStationCode() {
		return destStationCode;
	}
	public void setDestStationCode(String destStationCode) {
		this.destStationCode = destStationCode;
	}
	public TripSearchRequestBody(String srcStationCode, String destStationCode) {
		super();
		this.srcStationCode = srcStationCode;
		this.destStationCode = destStationCode;
	}
    
    
}
