package com.bruce.designer.front.template;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Comments for FreemakerTemplateLoader.java
 * 
 * @author <a href="mailto:jun.liu1209@gmail.com">刘军</a>
 * @createTime 2013-3-21 上午11:17:08
 */
public class FreemakerTemplateLoader implements ITemplateLoader, InitializingBean {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FreemakerTemplateLoader.class);

	private FreeMarkerConfig freemarkerConfig;

	private Configuration configuration;

	private final static ObjectWrapper objectWrapper = new DefaultObjectWrapper();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xiaonei.wap.mcs.template.TemplateLoader#process(java.util.Map,
	 * java.lang.String)
	 */
	@Override
	public String process(Map<String, Object> data, String name) {

		Template template = getTemplate(name);

		if (template != null) {
			try {
				Writer out = new StringWriter();
				template.process(data, out);
				return out.toString();
			} catch (TemplateException e) {
				logger.error("process template(" + name + ") failure", e);
			} catch (IOException e) {
				logger.error("process template(" + name + ") failure", e);
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xiaonei.wap.pangu.template.TemplateLoader#process(javax.servlet.http
	 * .HttpServletRequest, java.lang.String)
	 */
	@Override
	public String process(HttpServletRequest request, String name) {

		Template template = getTemplate(name);

		HttpRequestHashModel httpRequestHashModel = new HttpRequestHashModel(request, objectWrapper);

		if (template != null) {
			try {
				Writer out = new StringWriter();
				template.process(httpRequestHashModel, out);
				return out.toString();
			} catch (TemplateException e) {
				logger.error("process template(" + name + ") failure", e);
				return "数据 错误！";
			} catch (IOException e) {
				logger.error("process template(" + name + ") failure", e);
				return "数据 错误！";
			}
		}

		return null;
	}

	private Template getTemplate(String name) {
		String templateName = name;
		if (!StringUtils.endsWith(name, ".ftl"))
			templateName = name + ".ftl";

		Template template = null;
		try {
			template = configuration.getTemplate(templateName);
		} catch (IOException e) {
			logger.error("get template (" + name + ") failure ", e);
			return null;
		}
		return template;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(freemarkerConfig, "freemarkerConfig is required!");
		configuration = freemarkerConfig.getConfiguration();

		Assert.notNull(configuration, "configuration load failure!");
	}

	/**
	 * @return the freemarkerConfig
	 */
	public FreeMarkerConfig getFreemarkerConfig() {
		return freemarkerConfig;
	}

	/**
	 * @param freemarkerConfig
	 *            the freemarkerConfig to set
	 */
	public void setFreemarkerConfig(FreeMarkerConfig freemarkerConfig) {
		this.freemarkerConfig = freemarkerConfig;
	}

}
