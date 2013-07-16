/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.factory;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.ClassUtils;

import com.renren.x2.feed.storage.IFeedReaderStorage;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-4 下午6:15:24
 */
public class FeedReaderStorageFactoryBean extends FeedReaderStorageInterceptor implements FactoryBean, InitializingBean,
        BeanClassLoaderAware {

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    private Class<?> businessInterface = IFeedReaderStorage.class;

    private Object proxy;

    @Override
    public Object getObject() throws Exception { 
        return this.proxy;
    }

    @Override
    public Class<?> getObjectType() {
        return businessInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.proxy = new ProxyFactory(businessInterface, this).getProxy(this.beanClassLoader);
    }

    @Override
    public void setBeanClassLoader(ClassLoader arg0) {
        this.beanClassLoader = arg0;
    }

}
