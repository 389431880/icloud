package com.project.icloudCore.config.validator.interfaces;

import com.younuo.iwork.app.api.config.validator.validators.IsMobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  hzb
 * @ CreateDate    :  2020/4/9 17:12
 * @ Version       :  1.0
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidator.class})
public @interface IsMobile {

    boolean required() default true;

    String message() default "请输入正确的手机格式";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
