package com.example.performance.code3.reference.test;

import com.example.performance.code3.reference.model.TestBean;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author James
 */
public class RefTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RefTest.class);

    private TestBean testBean = new TestBean(1L, "james");

    @Test
    public void testFinalize() {
        testMethod();
        System.gc();
        LOGGER.info("testBean {}", testBean);
        LOGGER.info("test method run over");
    }

    private void testMethod() {
        TestBean testBean = new TestBean(1L, "method");
        System.out.println(testBean.toString());
    }
}
