package com.package1;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

/**
 * Created by Administrator on 2015/12/3 0003.
 */
@Service
public class ServletActionContextDemo {

    public String excute(){

        /**
         * 获取Application对象
         * */
        ServletContext servletContext = ServletActionContext.getServletContext();

        /**
         * 获取Session对象
         * */
        HttpSession session = ServletActionContext.getRequest().getSession();

        /**
         * 获取Request对象
         * */
        HttpServletRequest request = ServletActionContext.getRequest();

        /**
         * 获取Response对象
         * */
        HttpServletResponse response = ServletActionContext.getResponse();
        Cookie cookie = new Cookie("cookieKey","cookieValue");
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);

        /**
         * 获取web应用的PageContext对象
         * */
        PageContext pageContext = ServletActionContext.getPageContext();

        return "success";
    }

}
