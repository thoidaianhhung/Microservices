package com.vti.blogapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Constraint(
        validatedBy = PostIdExistsValidator.class
)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)

public @interface PostIdExists {
    String message() default "Post id does not exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
