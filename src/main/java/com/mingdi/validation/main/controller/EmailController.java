package com.mingdi.validation.main.controller;

import java.util.List;

import javax.validation.constraints.Email;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mingdi.validation.main.annotations.First;
import com.mingdi.validation.main.annotations.Second;
import com.mingdi.validation.main.model.EmailTestVo;

@RestController
@RequestMapping("/test")
@Validated
public class EmailController {

	
	/**
	 * To validate both groups
	 * @param vo
	 * @return
	 */
	@PostMapping("/emailTest") 
	public Integer test(@Validated({First.class,Second.class}) @RequestBody EmailTestVo vo) {
		return 1;
	}
	
	/**
	 * email group only
	 * @param vo
	 * @return
	 */
	@PostMapping("/validateEmailGroup") 
	public Integer testFirst(@Validated({First.class}) @RequestBody EmailTestVo vo) {
		return 1;
	}
	
	/**
	 * @NotBlank validation
	 * @param vo
	 * @return
	 */
	@PostMapping("/validateNotBlankGroup") 
	public Integer testSecond(@Validated({Second.class}) @RequestBody EmailTestVo vo) {
		return 1;
	}
	
	@PostMapping("/validateNotBlankGroupLogError") 
	public Integer testSecondLoggingErrors(@Validated({Second.class}) @RequestBody EmailTestVo vo
			, Errors errors) {
		List<FieldError> fieldErrors = errors.getFieldErrors();
	    for (FieldError e : fieldErrors) {
	        System.out.println(e.toString());
	        System.out.println("binding failures: "+e.isBindingFailure());
	    }
	    if(fieldErrors.size()>0) {
	    	throw new RuntimeException(fieldErrors.toString());
	    }
		return fieldErrors.size();
	}
	@GetMapping("/emailTest")
	public Integer testGet(@Email @RequestParam("email") String email) {
		return 1;
	}
}
