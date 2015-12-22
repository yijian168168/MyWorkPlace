package com.package1;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2015/12/3 0003.
 */
@Service
public class ActionContextDemo {

    public String excute(){

        /**
         *  ActionContext 是 Action 执行的上下文对象,
         *  在 ActionContext 中保存了 Action 执行所需要的所有对象,
         *  包括 parameters, request, session, application 等.
         */
        ActionContext actionContext = ActionContext.getContext();


        /**
         * 获取 applicationContext 对应的 Map 对象:
         * 通过get方法获取值，通过set方法添加值
         * */
        Map<String,Object> servletContextMap = actionContext.getApplication();


        /**
         * 获取 HttpSession 对应的 Map 对象:
         * 通过get方法获取值，通过set方法添加值
         * */
        Map<String,Object> httpSessionMap = actionContext.getSession();

        if (httpSessionMap instanceof SessionMap){
            //通过此种方式可以使session失效
            SessionMap sessionMap = (SessionMap)httpSessionMap;
            sessionMap.invalidate();
        }

        /**
         * 获取 HttpServletRequest 对应的 Map 对象:
         * 通过get方法获取值，通过set方法添加值
         * */
        Map<String,Object> httpServletRequestMap = (Map<String, Object>) actionContext.get("request");


        /**
         * 获取请求参数对应的 Map 对象:
         * 通过get方法获取值，不能添加值
         * */
        Map<String,Object> requestParametersMap = actionContext.getParameters();

        return "success";
    }
}
