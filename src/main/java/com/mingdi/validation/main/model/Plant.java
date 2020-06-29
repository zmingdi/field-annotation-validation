package com.mingdi.validation.main.model;

import com.mingdi.validation.main.enums.Color;
import com.mingdi.validation.main.enums.PlantType;

public interface Plant {

	public Color getColor();
	
	public PlantType getType();
	
	public String getTaste();
}
