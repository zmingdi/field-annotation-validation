package com.mingdi.validation.main.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.mingdi.validation.main.enums.CoordinateType;

@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Constraint(validatedBy = CoordinateValidator.class)
public @interface Coordinate {
    String message() default "Invalid Coordinate pair.";
    CoordinateType type() default CoordinateType.Pair;
    String matcher() default "(\\\\+|-)?(?:90(?:(?:\\\\.0{6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\\\.[0-9]{6})?))\\s(\\\\+|-)?(?:180(?:(?:\\\\.0{6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\\\.[0-9]{6})?))";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
