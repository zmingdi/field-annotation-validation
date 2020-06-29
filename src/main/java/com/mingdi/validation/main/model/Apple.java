package com.mingdi.validation.main.model;

import com.mingdi.validation.main.enums.Color;
import com.mingdi.validation.main.enums.PlantType;

public class Apple implements Plant {

	
	private Color color = Color.GREEN;
	public Apple(Color c) {
		this.color = c;
	}
	public void setColor(Color c) {
		this.color = c;
	}
	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public PlantType getType() {
		return PlantType.FRUIT;
	}

	@Override
	public String getTaste() {
		return color==Color.GREEN?"Yummy":"Bitter";
	}

}
