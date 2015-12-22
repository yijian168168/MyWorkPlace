package com.https;


import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.*;
import org.apache.http.protocol.HTTP;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by Administrator on 2015/12/16 0016.
 */
public class HttpClientUtil {

    private DefaultHttpClient httpClient;

    /**设置请求超时时间 毫秒级*/
    private int requestTimeOut =  60 * 1000;

    /**设置等待数据超时时间 毫秒级*/
    private int soTimeOut = 60 * 1000;

    public synchronized DefaultHttpClient getHttpClient() {
        if (null == httpClient) {
            /**
             * 1、初始化HttpParams，设置组件参数
             * HttpParams接口代表一个不可改变值的集合，定义一个组件运行时行为。代表一个对象集合，该集合是一个键到值的映射。
             * HttpParams作用是定义其他组件的行为，一般每个复杂的组件都有它自己的HttpParams对象。
             * */
            // 设置组件参数, HTTP协议的版本,1.1/1.0/0.9
            HttpParams params = new BasicHttpParams();

            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
            HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
            HttpProtocolParams.setUseExpectContinue(params, true);

            //设置连接超时时间
            HttpConnectionParams.setConnectionTimeout(params, requestTimeOut);
            HttpConnectionParams.setSoTimeout(params, soTimeOut);
            /*params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, requestTimeOut);
            params.setParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeOut);*/

            //连接池相关属性
            ConnManagerParams.setMaxConnectionsPerRoute(params, new ConnPerRouteBean(20));
            ConnManagerParams.setMaxTotalConnections(params, 100);

            //转发重定向策略
            HttpClientParams.setRedirecting(params,false);

            //设置访问协议
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

            // 使用线程安全的连接管理来创建HttpClient
            ClientConnectionManager connectionManager = new ThreadSafeClientConnManager(params, schemeRegistry);
            httpClient = new DefaultHttpClient(connectionManager, params);

            /*
            //多连接的线程安全的管理器
            PoolingClientConnectionManager pccm = new PoolingClientConnectionManager(schemeRegistry);
            //每个主机的最大并行链接数
            pccm.setDefaultMaxPerRoute(20);
            //客户端总并行链接最大数
            pccm.setMaxTotal(100);
            */

        }
        return httpClient;
    }

    public int getRequestTimeOut() {
        return requestTimeOut;
    }

    public void setRequestTimeOut(int requestTimeOut) {
        this.requestTimeOut = requestTimeOut;
    }

    public int getSoTimeOut() {
        return soTimeOut;
    }

    public void setSoTimeOut(int soTimeOut) {
        this.soTimeOut = soTimeOut;
    }
}
