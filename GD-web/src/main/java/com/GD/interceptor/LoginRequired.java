package com.GD.interceptor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired  {

    
}
