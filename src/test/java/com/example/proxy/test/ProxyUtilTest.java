package com.example.proxy.test;

import com.example.proxy.ProxyUtil;
import com.example.proxy.service.CalService;
import com.example.proxy.service.CalServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/9/19.
 */
public class ProxyUtilTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyUtilTest.class);

    @Test
    public void testJdkProxy() {
        CalService calService = new CalServiceImpl();
        CalService calServiceProxy = (CalService) ProxyUtil.jdkProxyObject(calService, "ProxyUtilTest");
        System.out.println(calServiceProxy.add(1, 2));
    }

    @Test
    public void testJdkProxy2() {
        try {
            CalServiceImpl calService = new CalServiceImpl();
            CalService calServiceProxy = (CalServiceImpl) ProxyUtil.jdkProxyObject(calService, "ProxyUtilTest");
            Assert.fail("bug-------");
            System.out.println(calServiceProxy.add(1, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCglibProxy() {
        CalServiceImpl calService = new CalServiceImpl();
        CalService calServiceProxy = (CalServiceImpl) ProxyUtil.cglibProxyObject(calService, "ProxyUtilTest");
        System.out.println(calServiceProxy.add(1, 2));
    }

    @Test
    public void testCglibProxy2() {
        LOGGER.info("cglib proxy....");
        CalService calServiceProxy = (CalServiceImpl) ProxyUtil.cglibProxy(CalServiceImpl.class, "ProxyUtilTest");
        System.out.println(calServiceProxy.add(1, 2));
    }
}
