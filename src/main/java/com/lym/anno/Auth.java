package com.lym.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
    // 用户类型（int）做与位运算大于零就具有访问权限
    int flag() default 0;
    // 重定向到登录页,如果是false会返回json格式
    boolean isRedirect() default false;
}
