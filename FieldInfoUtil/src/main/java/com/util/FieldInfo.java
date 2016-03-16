package com.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段注解
 *
 * Created by Administrator on 2016/3/16 0016.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldInfo {

    /**
     * 对齐方式 {左对齐，右对齐}
     * */
    public enum ALIGN{ left,right};

    /**
     * 字段序号
     * */
    String index() default "";

    /**
     * 字段长度
     * */
    int length() default 0;

    /**
     * 对齐方式
     * */
    ALIGN align();

    /**
     * 长度不足时，填充内容
     * */
    String pad() default " ";


}
