package com.example.demo.hashcode;

import com.example.demo.vo.TestVo;
import com.example.demo.vo.TestVoWithEHCode;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chengdu
 * @date 2019/8/3.
 */
public class CollectionTest {

    @Test
    public void testNoHashCode(){
        TestVo testVo1 = new TestVo(1, "http://cd", "cd");
        TestVo testVo2 = new TestVo(1, "http://cd", "cd");
        TestVo testVo3 = testVo1;
        // testVo1 testVo2 在栈中地址不同
        Assert.assertFalse(testVo1.equals(testVo2));
        // 地址相同
        Assert.assertTrue(testVo1.equals(testVo3));
        Integer v1code = testVo1.hashCode();
        Integer v2code = testVo2.hashCode();
        Integer v3code = testVo3.hashCode();
        Assert.assertFalse(v1code.equals(v2code));
        Assert.assertTrue(v1code.equals(v3code));
        Set<TestVo> testVoSet = new HashSet<TestVo>(3);
        testVoSet.add(testVo1);
        testVoSet.add(testVo2);
        testVoSet.add(testVo3);
        Assert.assertEquals(testVoSet.size(), 2);
    }

    @Test
    public void testWithHashCode(){
        TestVoWithEHCode testVo1 = new TestVoWithEHCode(1, "http://cd", "cd");
        TestVoWithEHCode testVo2 = new TestVoWithEHCode(1, "http://cd", "cd");
        TestVoWithEHCode testVo3 = testVo1;
        // override equals
        Assert.assertTrue(testVo1.equals(testVo2));
        Assert.assertTrue(testVo1.equals(testVo3));
        Integer v1code = testVo1.hashCode();
        Integer v2code = testVo2.hashCode();
        Integer v3code = testVo3.hashCode();
        Assert.assertTrue(v1code.equals(v2code));
        Assert.assertTrue(v1code.equals(v3code));
        Set<TestVoWithEHCode> testVoSet = new HashSet<TestVoWithEHCode>(3);
        testVoSet.add(testVo1);
        testVoSet.add(testVo2);
        testVoSet.add(testVo3);
        Assert.assertEquals(testVoSet.size(), 1);
    }
}
