package com.mingdi.validation.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.mingdi.validation.main.enums.Color;
import com.mingdi.validation.main.model.Apple;
import com.mingdi.validation.main.model.Ginger;
import com.mingdi.validation.main.model.Plant;

@Service
public class PlantService implements CommandLineRunner{

	public void plant() {
		Integer randomSeed = new Random().nextInt(20);
		List<Plant> plants = new ArrayList<>();
		IntStream.range(0, randomSeed+1).forEach(index->{
			Integer dice = new Random().nextInt(4);
			if(dice%2==0) {
				plants.add(new Apple(Color.values()[dice%3]));
			} else {
				plants.add(new Ginger(Color.values()[dice%3]));
			}
		});
		plants.stream().collect(Collectors.groupingBy(Plant::getClass))
		.forEach((k,v)->{
			System.out.println("Class "+ k +" : " + v.size());
		});
		plants.stream().collect(Collectors.groupingBy(Plant::getTaste))
		.forEach((k,v)->{
			System.out.println("Taste "+ k +" : " + v.size());
		});
	}

	@Override
	public void run(String... args) throws Exception {
		plant();
		
	}
}
