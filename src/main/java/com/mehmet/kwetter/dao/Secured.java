package com.mehmet.kwetter.dao;


import com.mehmet.kwetter.domain.RoleEnum;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * Created by mehmet on 3-5-17.
 */
@NameBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Secured {
    RoleEnum[] value() default {};
}
