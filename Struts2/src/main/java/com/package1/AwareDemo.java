package com.package1;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2015/12/3 0003.
 */
@Service
public class AwareDemo implements ApplicationAware, SessionAware, RequestAware, ParameterAware {

    private Map<String, Object> application;

    private Map<String, Object> session;

    private Map<String, Object> request;

    private Map<String, String[]> parameters;

    /**
     * 若一个Action类中有多个action方法，则建议使用实现XXXAware接口的方式
     * */

    public String excute() {

        /**
         * Action 类通过可以实现某些特定的接口,
         * 让 Struts2 框架在运行时向 Action 实例注入 parameters, request, session 和 application 对应的 Map 对象:
         * */
        application.get("applicationKey1");
        application.put("applicationKey2","applicationValue2");

        return "success";
    }


    public void setApplication(Map<String, Object> map) {
        this.application = map;
    }

    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public void setRequest(Map<String, Object> map) {
        this.request = map;
    }

    public void setParameters(Map<String, String[]> map) {
        this.parameters = map;
    }

}
