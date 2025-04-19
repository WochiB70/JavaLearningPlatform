package xyz.wochib70.validator.test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.Test;
import xyz.wochib70.validator.example.enums.EnumModel;
import xyz.wochib70.validator.example.model.SimpleModel;

import java.util.Set;

public class SimpleExampleTest {



    @Test
    public void test(){
        SimpleModel model = new SimpleModel();
        model.setName("dnsajkn");
        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Set<ConstraintViolation<SimpleModel>> validated = factory.getValidator().validate(model, null);
            if (!validated.isEmpty()) {
                validated.forEach(constraintViolation -> {
                    System.out.println(constraintViolation.getMessage());
                });
            }
        }
    }


    @Test
    public void customSimpleTest(){
        SimpleModel model = new SimpleModel();
        model.setName("ok ok");
        model.setDesc("desc desc");
        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Set<ConstraintViolation<SimpleModel>> validated = factory.getValidator().validate(model);
            if (!validated.isEmpty()) {
                validated.forEach(constraintViolation -> {
                    System.out.println(constraintViolation.getMessage());
                });
            }
        }
    }


    @Test
    public void enumTest(){
        EnumModel model = new EnumModel();
        model.setOne(1);
        model.setTwo(4);
        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Set<ConstraintViolation<EnumModel>> validated = factory.getValidator().validate(model);
            if (!validated.isEmpty()) {
                validated.forEach(constraintViolation -> {
                    System.out.println(constraintViolation.getMessage());
                });
            }
        }
    }
}
