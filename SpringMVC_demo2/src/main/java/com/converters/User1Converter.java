package com.converters;

import com.model.User1;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2015/7/5 0005.
 */
@Component
public class User1Converter implements Converter<String, User1> {

    public User1 convert(String s) {

        System.out.println("++++" + s);

        User1 user = null;

        if (null != s) {

            String[] values = s.split("-");

            if (values.length == 3) {
                user = new User1(values[0], values[1], values[2]);
            }

            System.out.println(user);

        }
        return user;
    }

}
