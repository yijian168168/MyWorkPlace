package com.post;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/11 0011.
 */
public class SendPostRequest {

    public static void main(String[] args) {

        try {

            /*创建表单*/
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("userName", "xiaoqin"));
            nameValuePairs.add(new BasicNameValuePair("passWord", "654321"));
            HttpEntity entity = new UrlEncodedFormEntity(nameValuePairs);

            /*创建请求*/
            String url = "http://192.168.1.101:8086/controller2/testHttpClient";
            HttpPost httpPost = new HttpPost(url);

            /*将表单内容添加到请求中*/
            httpPost.setEntity(entity);

            /*创建浏览器*/
            HttpClient httpClient = new DefaultHttpClient();

            /*浏览器发起请求*/
            HttpResponse response = httpClient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() == 200) {

                String result = EntityUtils.toString(response.getEntity(), "utf-8");

                System.out.println("result:" + result);

            } else {
                System.out.println("++++" + response.getStatusLine().getStatusCode());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
