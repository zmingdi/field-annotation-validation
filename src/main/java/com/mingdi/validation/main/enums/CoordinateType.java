package com.mingdi.validation.main.enums;

public enum CoordinateType {

	
	Latitude("lat")
	, Longitude("lon")
	,Pair("");

	protected static final String LATITUDE_PATTERN="(\\+|-)?(?:90(?:(?:\\.0{6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{6})?))";
    protected static final String LONGITUDE_PATTERN="(\\+|-)?(?:180(?:(?:\\.0{6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{6})?))";
    
	private String pattern = "";
	private String name = "";
	private String message = "";
	private CoordinateType(String name) {
		switch (name){
			case "lat":
				this.setName("latitude");
				this.pattern = LATITUDE_PATTERN;
				this.setMessage("Invalid latitude format.");
				break;
			case "lon": 
				this.setName("longitude");
				this.pattern = LONGITUDE_PATTERN;
				this.setMessage("Invalid longitude format.");
				break;
			default: 
				this.setName("pair");
				this.pattern = LATITUDE_PATTERN + "\\s" +LONGITUDE_PATTERN;
				this.setMessage("Invalid coordinate pair format.");
				break;
		}
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
