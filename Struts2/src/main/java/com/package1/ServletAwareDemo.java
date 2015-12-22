package com.package1;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2015/12/3 0003.
 */
@Service
public class ServletAwareDemo implements ServletContextAware,ServletRequestAware {

    private ServletContext servletContext;

    private HttpServletRequest httpServletRequest;

    /**
     * 通过httpServletRequest 我们可以获取Session
     * */
    public String excute(){

        HttpSession session = httpServletRequest.getSession();
        ActionSupport actionSupport;
        return "success";
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
}
