package proxy.test;

import com.example.proxy.ProxyUtil;
import org.junit.Assert;
import org.junit.Test;
import proxy.service.CalService;
import proxy.service.CalServiceImpl;

/**
 * @author chengdu
 * @date 2019/9/19.
 */
public class ProxyUtilTest {

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
        CalService calServiceProxy = (CalServiceImpl) ProxyUtil.cglibProxy(CalServiceImpl.class, "ProxyUtilTest");
        System.out.println(calServiceProxy.add(1, 2));
    }
}
