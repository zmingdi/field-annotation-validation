package com.mingdi.validation.main.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mingdi.validation.main.annotations.Coordinate;
import com.mingdi.validation.main.enums.CoordinateType;

@RestController
@RequestMapping("/test")
@Validated
public class CoordinateTestController {

	@GetMapping("/testLatitude")	
	public Integer testLat(@Coordinate(type=CoordinateType.Latitude) @RequestParam("lat") String lat) {
		return 1;
	}
	
	@GetMapping("/testLongitude")
	public Integer testlon(@Coordinate(type=CoordinateType.Longitude) @RequestParam("lon") String lat) {
		return 1;
	}
}
