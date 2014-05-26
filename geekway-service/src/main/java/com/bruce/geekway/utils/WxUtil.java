package com.bruce.geekway.utils;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

//import org.apache.http.Consts;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpRequestBase;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;


public class WxUtil {
	
	
	/**
	 * Httpclient get
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static final String sendGetRequest(String url, Map<String, String> params) {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		NameValuePair[] pairs = null;
		if (params != null) {
			pairs = new NameValuePair[params.size()];
			int i=0;
			for (Map.Entry<String, String> entry : params.entrySet()) {
		        pairs[i] = new NameValuePair(entry.getKey(), entry.getValue());
		        i++;
			}
			getMethod.setQueryString(pairs);
		}
		getMethod.getParams().setContentCharset("utf-8");
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				String response = getMethod.getResponseBodyAsString();
				return response;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * Httpclient Post
	 * @param url
	 * @param params
	 * @param requestEntity
	 * @return
	 */
	public static final String sendPostRequest(String url, Map<String, String> params, String data) {
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		NameValuePair[] pairs = null;
		if (params != null) {
			pairs = new NameValuePair[params.size()];
			int i=0;
			for (Map.Entry<String, String> entry : params.entrySet()) {
		        pairs[i] = new NameValuePair(entry.getKey(), entry.getValue());
		        i++;
			}
			postMethod.setQueryString(pairs);
		}
		postMethod.getParams().setContentCharset("utf-8");
		if(data!=null){
			postMethod.setRequestEntity(new StringRequestEntity(data));
		}
		try {
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				String response = postMethod.getResponseBodyAsString();
				return response;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	
	//for httpclient 4.0
//	/**
//	 * Httpclient get
//	 * 
//	 * @param url
//	 * @param params
//	 * @return
//	 */
//	public static final String sendGetRequest(String url, Map<String, String> params) {
//		HttpClient client = HttpClientBuilder.create().build();
//		HttpRequestBase request = new HttpGet();
//
//		URIBuilder builder;
//		try {
//			builder = new URIBuilder(url);
//
//			if (params != null) {
//				for (Map.Entry<String, String> entry : params.entrySet()) {
//					builder.addParameter(entry.getKey(), entry.getValue());
//				}
//			}
//			request.setURI(builder.build());
//
//			HttpResponse response = client.execute(request);
//			HttpEntity entity = response.getEntity();
//			String respBody = EntityUtils.toString(entity, Consts.UTF_8);
//			if (entity != null) {
//				EntityUtils.consume(entity);
//			}
//			return respBody;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	//for httpclient 4.0
//	/**
//	 * Httpclient Post
//	 * @param url
//	 * @param params
//	 * @param requestEntity
//	 * @return
//	 */
//	public static final String sendPostRequest(String url, Map<String, String> params, HttpEntity requestEntity) {
//		HttpClient client = HttpClientBuilder.create().build();
//		HttpRequestBase request = new HttpPost();
//		try{
//			if (requestEntity != null) {
//				((HttpPost) request).setEntity(requestEntity);
//			}
//			URIBuilder builder = new URIBuilder(url);
//			if (params != null) {
//				for (Map.Entry<String, String> entry : params.entrySet()) {
//					builder.addParameter(entry.getKey(), entry.getValue());
//				}
//			}
//			request.setURI(builder.build());
//	
//			HttpResponse response = client.execute(request);
//			HttpEntity entity = response.getEntity();
//			String respBody = EntityUtils.toString(entity, Consts.UTF_8);
//			if (entity != null) {
//			EntityUtils.consume(entity);
//			}
//			return respBody;
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	
	public static final long currentTimeInSec() {
		return System.currentTimeMillis() / 1000;
	}
}
