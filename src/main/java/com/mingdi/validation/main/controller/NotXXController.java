package com.mingdi.validation.main.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mingdi.validation.main.model.NotXXTestVo;

@RestController
@RequestMapping("/test")
public class NotXXController {

	
	@PostMapping("/notTest")
	public Integer notXXTest(@Valid @RequestBody NotXXTestVo vo) {
		return 1;
	}
}
