package com.cc.blog.interfaces;

import java.lang.annotation.*;

/**
 * @author cc
 * @date 18-3-26 上午11:57
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authority {
    String[] role() default {"CUSTOMER"};
}
