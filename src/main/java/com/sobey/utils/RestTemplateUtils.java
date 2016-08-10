/**
 * Copyright © 2016 Sobey. All rights reserved.
 *
 * @Title: RestTemplateUtils.java
 * @Prject: 2015maven-web
 * @Package: com.sobey.httpClient
 * @Description: TODO
 * @author: sobeyLrl
 * @date: 2016年8月5日 上午10:15:20
 * @version: V1.0
 */
package com.sobey.utils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: RestTemplateUtils
 * @Description: RestTemplate服务端调用客服端代码
 * @author: sobeyLrl
 * @date: 2016年8月5日 上午10:15:20
 */
public final class RestTemplateUtils {

    private final static Logger LOGGER = Logger.getLogger(RestTemplateUtils.class);

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * 连接时间(毫秒)
     */
    private int connectTimeout = -1;

    /**
     * 读取时间(毫秒)
     */
    private int readTimeout = -1;

    /**
     * 请求参数类型
     */
    private String contentType = "application/json";

    private RestTemplateUtils() {}

    private static class HolderObject {
        private final static RestTemplateUtils instance = new RestTemplateUtils();
    }

    public static RestTemplateUtils getInstance() {
        return HolderObject.instance;
    }

    /**
     * 获取RestTemplate
     * 
     * @author: sobeyLrl
     * @Title: getRestTemplate
     * @return RestTemplate
     */
    public RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }

    /**
     * Headers
     * 
     * @author: sobeyLrl
     * @Title: getHttpHeaders
     * @return contentType
     */
    public HttpHeaders getHttpHeaders() {
        // 设置HTTP请求头信息，实现编码等
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Accept", "application/json");
        requestHeaders.set("Accept-Charset", "utf-8");
        requestHeaders.set("Content-type", "" + contentType + "; charset=utf-8");// 设置编码
        return requestHeaders;
    }



    /**
     * GET方法请求<br/>
     * Map<String, String> vars = Collections.singletonMap("hotel", "42"); <br/>
     * String result = restTemplate.getForObject("http://example.com/hotels/{hotel}/rooms/{hotel}",
     * String.class, vars);
     * 
     * @author: sobeyLrl
     * @Title: getMethod
     * @param url 请求URL
     * @param urlVariables 不能为空
     * @return String
     */
    public String getMethod(String url, Map<String, ?> urlVariables) {
        String resultContent = getRestTemplate().getForObject(url, String.class, urlVariables);
        return resultContent;
    }

    /**
     * POST方法请求
     * 
     * @author: sobeyLrl
     * @Title: postByMap
     * @param url
     * @param params 请求参数键值对
     * @return 返回内容
     */
    public String postByMap(String url, Map<String, ?> params) {
        return postObject(url, params);
    }

    /**
     * POST方法请求
     * 
     * @author: sobeyLrl
     * @Title: postMethod
     * @param url
     * @param params jsonObject
     * @return 返回内容
     */
    public String postMethod(String url, JSONObject params) {
        return postObject(url, params);
    }

    /**
     * POST方法请求
     * 
     * @author: sobeyLrl
     * @Title: postMethod
     * @param url
     * @param params 请求参数键值对集合
     * @return 返回内容
     */
    public String postMethod(String url, JSONArray params) {
        return postObject(url, params);
    }

    /**
     * POST方法请求
     * 
     * @author: sobeyLrl
     * @Title: postMethod
     * @param url
     * @param params 请求参数键值对集合
     * @return 返回内容
     */
    private String postObject(String url, Object params) {
        // 利用容器实现数据封装，发送
        HttpEntity<Object> entity = new HttpEntity<Object>(params, getHttpHeaders());
        String resultContent = restTemplate.postForObject(url, entity, String.class);
        try {
            if (resultContent == null) {
                resultContent = "";
            }
            resultContent = new String(resultContent.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("", e);
        }
        return resultContent;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
