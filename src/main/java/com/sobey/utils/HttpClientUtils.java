/**
 * Copyright © 2016 Sobey. All rights reserved.
 *
 * @Title: HttpClientUtils.java
 * @Prject: 2015maven-web
 * @Package: com.sobey.httpClient
 * @Description: 使用com.sobey.httpClient 请求RESTful客服端调用
 * @author: sobeyLrl
 * @date: 2016年8月4日 下午5:26:40
 * @version: V1.0
 */
package com.sobey.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * @ClassName: HttpClientUtils
 * @Description: 使用com.sobey.httpClient 请求RESTful客服端调用
 * @author: sobeyLrl
 * @date: 2016年8月4日 下午5:26:40
 */
public final class HttpClientUtils {

    private static final Logger LOGGER = Logger.getLogger(HttpClientUtils.class);

    private HttpClientUtils() {}

    private static class HolderObject {
        private final static HttpClientUtils instance = new HttpClientUtils();
    }

    public static HttpClientUtils getInstance() {
        return HolderObject.instance;
    }

    /**
     * 通过GET方式请求
     * 
     * @author: sobeyLrl
     * @Title: getMethod
     * @param url 请求的地址 (需要带参数时跟在URL后面 eg:http://www.baidu.com?username=*&password=*)
     * @return 请求返回的内容
     */
    public String getMethod(final String url) {

        String resultContent = ""; // 请求返回的内容
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httpget.
        HttpGet httpGet = new HttpGet(url);
        LOGGER.info("executing request " + httpGet.getURI());
        // 执行get请求.
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                resultContent = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (ClientProtocolException e) {
            LOGGER.error("", e);
        } catch (IOException e) {
            LOGGER.error("", e);
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }

        return resultContent;
    }

    /**
     * 通过POST方式请求
     * 
     * @author: sobeyLrl
     * @Title: postForm
     * @param url 请求的地址
     * @param requestParams 键值对请求参数
     * @return 请求返回的内容
     */
    public String postByMap(final String url, final Map<String, Object> requestParams) {
        String resultContent = ""; // 请求返回的内容

        // 创建默认的httpClient实例.
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建httppost
        HttpPost httpPost = new HttpPost(url);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Entry<String, Object> entry : requestParams.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }
        UrlEncodedFormEntity uefEntity;

        CloseableHttpResponse response = null;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httpPost.setEntity(uefEntity);

            response = client.execute(httpPost);
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                resultContent = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("", e);
        } catch (ClientProtocolException e) {
            LOGGER.error("", e);
        } catch (IOException e) {
            LOGGER.error("", e);
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }
        return resultContent;
    }

    /**
     * POST 请求
     * 
     * @author: sobeyLrl
     * @Title: postForm
     * @param url
     * @param requestParams json数据
     * @return
     */
    public String postForm(final String url, final JSONObject requestParams) {
        return postMethod(url, requestParams);
    }

    /**
     * POST 请求
     * 
     * @author: sobeyLrl
     * @Title: postForm
     * @param url
     * @param requestParams json数组
     * @return
     */
    public String postForm(final String url, final JSONArray requestParams) {
        return postMethod(url, requestParams);
    }

    /**
     * 通过POST方式请求
     * 
     * @author: sobeyLrl
     * @Title: postForm
     * @param url 请求的地址
     * @param requestParams 键值对请求参数
     * @return 请求返回的内容
     */
    private String postMethod(final String url, final Object requestParams) {

        String resultContent = ""; // 请求返回的内容

        // 创建默认的httpClient实例.
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建httppost
        HttpPost httpPost = new HttpPost(url);

        CloseableHttpResponse response = null;
        try {
            // 参数
            StringEntity strEntity = new StringEntity(requestParams.toString());
            strEntity.setContentEncoding("UTF-8");
            // strEntity.setContentType("application/json");// 发送json数据需要设置contentType
            httpPost.setEntity(strEntity);

            response = client.execute(httpPost);
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                resultContent = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("", e);
        } catch (ClientProtocolException e) {
            LOGGER.error("", e);
        } catch (IOException e) {
            LOGGER.error("", e);
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }
        return resultContent;
    }
}
