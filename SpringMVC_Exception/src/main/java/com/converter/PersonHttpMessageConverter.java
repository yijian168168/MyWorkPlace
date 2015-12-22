package com.converter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2015/11/15 0015.
 */
public class PersonHttpMessageConverter implements HttpMessageConverter {

    public boolean canRead(Class aClass, MediaType mediaType) {
        return true;
    }

    public boolean canWrite(Class aClass, MediaType mediaType) {
        return true;
    }

    public List<MediaType> getSupportedMediaTypes() {
        List<MediaType> mediaTypes = Arrays.asList(MediaType.APPLICATION_JSON,MediaType.TEXT_HTML);
        return mediaTypes;
    }

    public Object read(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        System.out.println(httpInputMessage);
        return null;
    }

    public void write(Object o, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

        System.out.println(httpOutputMessage);
    }
}
