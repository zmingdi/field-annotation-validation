# Field validation with Annotations
## Introduction
    In spring web/api development, parameter validation is a common function. Use provided annotations for basic validation like @NotNull, @NotBlank. 
    
    Customize Annotations for scenario oriented validation, like Latitude/Longitude validations. 
 
## Examples

- Use the predefined annotations: 

	NotXXTestVo: Define @NotNull,@NotEmpty and @NotBlank fields.
	Show difference in TestNotXXAnnotation.	
- Use customized annotation: 
    
    @Coordinate, customized annotation to validate input as latitude/longitude
	Use CoordinateValidator to process.
	Test in CoordinateTest.
    
- Introduce group concept in validation: 
    
	In EmailTestVo, define email field for validation.
	Define First.class and Second.class as group name. 
	Define controller in EmailController 
	Run with TestEmailAnnotation.
    
 - Difference in @Validated and @Valid:
 
	1. Scope. @Validated can use as class identifier. @Valid uses as parameter identifier.
	2. @Valid validates the fields in Object, @Validated can do too. When validating parameters as requestparam or pathvariable, use @Validated will be better. 
	3. When validation failed, @Valid will throw @MethodArgumentResolverException( something like this as a runtime exception, return response with 400 code.), @Validated throw javax.validation.ConnstraintValidation( Unhandled exception, 500 internal server exception). This to be fixed in Spring framework 5.3 M2(maybe),  [issue reported] (https://github.com/spring-projects/spring-framework/issues/23107) .
	4. **TODO** reproduce the Binding exception

## TODO

	1. Show execution path when @Validated and @Valid invoked.
	2. Try @Inherited annotation for **extends**.
	3. More validation annotation examples.
	
	
