package com.mingdi.validation.main.model;

import com.mingdi.validation.main.enums.Color;
import com.mingdi.validation.main.enums.PlantType;

public class Ginger implements Plant{

	private Color color = Color.GREEN;
	
	public Ginger(Color c) {
		this.color = c;
	}
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public PlantType getType() {
		// TODO Auto-generated method stub
		return PlantType.VEGETABLE;
	}

	@Override
	public String getTaste() {
		return color == Color.YELLOW?"spicy":"Bitter";
	}

}
