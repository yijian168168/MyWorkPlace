package com.get;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Administrator on 2015/7/11 0011.
 */
public class SendGetRequest {

    public static void main(String[] args) {

        try {

            /*创建请求*/
            String url = "http://192.168.1.101:8086/controller2/testHttpClient";
            HttpGet request = new HttpGet(url);

            /*创建浏览器*/
            HttpClient httpClient = new DefaultHttpClient();

            /*浏览器发起请求*/
            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == 200) {

                String result = EntityUtils.toString(response.getEntity(), "utf-8");

                System.out.println("result:" + result);

            } else {
                System.out.println("++++" + response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
