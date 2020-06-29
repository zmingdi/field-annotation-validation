package com.mingdi.validation.main.annotations;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mingdi.validation.main.enums.CoordinateType;

public class CoordinateValidator implements ConstraintValidator<Coordinate,Object>{
    private String matcher = "";
    private String typename="";
    private String message = "";
    @Override
    public void initialize(Coordinate constraintAnnotation) {
    	matcher = constraintAnnotation.type().getPattern();
    	typename = constraintAnnotation.type().getName();
    	message = constraintAnnotation.type().getMessage();
    	System.out.println("type = " + typename);
    	System.out.println("matcher = " + matcher);
	}
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
    	
        if(value instanceof String) {
          String pair = (String)value;
          context.disableDefaultConstraintViolation();
//          context.
          context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
          
          return pair.matches(matcher);
        }
        return false;
    }
}
