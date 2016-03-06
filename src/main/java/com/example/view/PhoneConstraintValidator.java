package com.example.view;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Class that describe custom validation annotation @Phone * 
 * @author 41X
 *
 */
public class PhoneConstraintValidator implements ConstraintValidator<Phone, String> {
    
    @Override
    public void initialize(Phone phone){
        
    }
    
    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext cxt) {
        
        if(phoneField == null) {
            return false;
        }
        
        return phoneField.matches("[0-9()-]*");
    }

}
