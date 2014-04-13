package com.bruce.geekway.utils;

import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


public class WxUtil {
	
	/**
	 * Httpclient get
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static final String sendGetRequest(String url, Map<String, String> params) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpRequestBase request = new HttpGet();

		URIBuilder builder;
		try {
			builder = new URIBuilder(url);

			if (params != null) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					builder.addParameter(entry.getKey(), entry.getValue());
				}
			}
			request.setURI(builder.build());

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			String respBody = EntityUtils.toString(entity, Consts.UTF_8);
			if (entity != null) {
				EntityUtils.consume(entity);
			}
			return respBody;
		} catch (Exception e) {
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
	public static final String sendPostRequest(String url, Map<String, String> params, HttpEntity requestEntity) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpRequestBase request = new HttpPost();
		try{
			if (requestEntity != null) {
				((HttpPost) request).setEntity(requestEntity);
			}
			URIBuilder builder = new URIBuilder(url);
			if (params != null) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					builder.addParameter(entry.getKey(), entry.getValue());
				}
			}
			request.setURI(builder.build());
	
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			String respBody = EntityUtils.toString(entity, Consts.UTF_8);
			if (entity != null) {
			EntityUtils.consume(entity);
			}
			return respBody;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
