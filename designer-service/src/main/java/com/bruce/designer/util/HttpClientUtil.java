package com.bruce.designer.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpClientUtil {
	
	private static final int REQUEST_TIMEOUT = 6*1000;//设置请求超时时间  
	private static final int SO_TIMEOUT = 6*1000;  //设置等待数据超时时间 
	
	/**
	 * 获取网络资源（通常是头像图片）的bytes
	 * @param url
	 * @return
	 */
	public static final byte[] getBytesFromRemoteResource(String url) {
		HttpClient httpClient = new HttpClient();
		
		setConnectionParam(httpClient);

		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setContentCharset("utf-8");
		
		InputStream is = null;
		ByteArrayOutputStream bos = null;
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				is = getMethod.getResponseBodyAsStream();
				
				int i=0;
				byte[] buffer = new byte[1024];
				bos = new ByteArrayOutputStream();
				while((i=is.read(buffer))!=-1){
					bos.write(buffer, 0, i);
				}
				return bos.toByteArray();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(bos!=null){
						bos.close();
					bos = null;
				}
				if(is!=null){
					is.close();
					is = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			getMethod.releaseConnection();
		}
		return null;
	}

	
	private static void setConnectionParam(HttpClient httpClient) {
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(REQUEST_TIMEOUT);
	    //读数据超时
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(SO_TIMEOUT);
	}
	
	
	public static void main(String[] args) throws Exception {
		byte[] bytes = getBytesFromRemoteResource("http://avatar.csdn.net/3/6/6/1_funcreal.jpg");
		if(bytes!=null&&bytes.length>0){
			File file = new File("/home/liqian/Desktop/cc.jpg");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(bytes);
			fos.close();
		}
		
	}
}
