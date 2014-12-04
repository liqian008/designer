package com.bruce.designer.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class WxHttpUtil {
	
	private static final int REQUEST_TIMEOUT = 6*1000;//设置请求超时时间  
	private static final int SO_TIMEOUT = 6*1000;  //设置等待数据超时时间 
	
	/**
	 * Httpclient get
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static final String getRequest(String url, Map<String, String> params) {
		HttpClient httpClient = new HttpClient();
		
		setConnectionParam(httpClient);
		
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
		}finally{
			getMethod.releaseConnection();
		}
		return null;
	}


	/**
	 * Httpclient Post
	 * @param url
	 * @param params
	 * @param data
	 * @return
	 */
	public static final String postRequest(String url, Map<String, String> params, String data) {
		HttpClient httpClient = new HttpClient();
		
		setConnectionParam(httpClient);
		
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
		}finally{
			postMethod.releaseConnection();
		}
		return null;
	}
	
	/**
	 * post multipart data
	 * @param url
	 * @param params
	 * @param bytes
	 * @return
	 */
	public static final String postMultipartRequest(String url, Map<String, String> params, byte[] bytes, String contentType) {
		HttpClient httpClient = new HttpClient();
		
		setConnectionParam(httpClient);
		PostMethod postMethod = new PostMethod(url);
		
		String fileName = "media";
		Part filePart = new FilePart(fileName, new ByteArrayPartSource(fileName, bytes));
		
		Part[] parts = new Part[params.size()+1];
		
		int i=0;
		HttpMethodParams methodParams = postMethod.getParams();
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				methodParams.setParameter(entry.getKey(), entry.getValue());
				parts[i++] = new StringPart(entry.getKey(), entry.getValue());
			}
		}
		parts[i++] = filePart;
		try {
			postMethod.addRequestHeader("Content-Type", contentType);
			postMethod.getParams().setContentCharset("utf-8");
			
			MultipartRequestEntity multipartEntity = new MultipartRequestEntity(parts, methodParams);
			
			postMethod.setRequestEntity(multipartEntity);
			
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				String response = postMethod.getResponseBodyAsString();
				return response;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			postMethod.releaseConnection();
		}
		return null;
	}
	

	
	
	
	/**
	 * post multipart data
	 * @param url
	 * @param params
	 * @param bytes
	 * @return
	 */
	public static final String postMultipartRequest(String url, Map<String, String> params, File file, String contentType) {
		HttpClient httpClient = new HttpClient();
		
		setConnectionParam(httpClient);
		PostMethod postMethod = new PostMethod(url);
		
		String fileName = "media";
		try {
			Part filePart = new FilePart(fileName, file);
			
			Part[] parts = new Part[params.size()+1];
			
			int i=0;
//			HttpMethodParams methodParams = postMethod.getParams();
			if (params != null) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					parts[i++] = new StringPart(key, value);
				}
			}
			parts[i++] = filePart;
		
			MultipartRequestEntity multipartEntity = new MultipartRequestEntity(parts, postMethod.getParams());
			postMethod.setRequestEntity(multipartEntity);
			
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				String response = postMethod.getResponseBodyAsString();
				return response;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			postMethod.releaseConnection();
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

	
	public static Map<String, String> buildAccessTokenParams(String accessToken) {
		Map<String, String> result = buildParams();
		result.put("access_token", accessToken);
		return result;
	}

	public static Map<String, String> buildParams() {
		Map<String, String> result = new HashMap<String, String>();
		return result;
	}
	
	private static void setConnectionParam(HttpClient httpClient) {
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(REQUEST_TIMEOUT);
	    //读数据超时
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(SO_TIMEOUT);
	}
	
}
