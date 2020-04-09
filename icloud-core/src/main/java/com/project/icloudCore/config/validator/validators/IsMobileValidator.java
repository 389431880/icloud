package com.project.icloudCore.config.validator.validators;

import com.younuo.iwork.app.api.config.validator.interfaces.IsMobile;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  hzb
 * @ CreateDate    :  2020/4/9 17:13
 * @ Version       :  1.0
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile isMobile) {

        required = isMobile.required();
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        String s2 = "^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$";
        if(StringUtils.isNotBlank(str)){
            p = Pattern.compile(s2);
            m = p.matcher(str);
            b = m.matches();
        }

        return b;
    }

}
