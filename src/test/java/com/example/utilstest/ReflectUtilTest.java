package com.example.utilstest;

import com.example.utils.ReflectUtil;
import com.example.utilstest.models.BaseModel;
import com.example.utilstest.models.BeanTest;
import com.example.utilstest.models.BusinessModel;
import com.example.utilstest.models.TestEmptyClazz;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author chengdu
 * @date 2019/12/4
 */
public class ReflectUtilTest {

    @Test
    public void testGetField() {
        Assert.assertEquals(0, ReflectUtil.getClassField(TestEmptyClazz.class).length);
        Assert.assertEquals(1, ReflectUtil.getClassField(BusinessModel.class).length);
    }

    @Test
    public void testIllegalParam() {
        try {
            ReflectUtil.getClassField(null);
            Assert.fail("get bug-------------------");
        } catch (Exception e) {
            Assert.assertEquals("input param is null", e.getMessage());
        }
    }

    @Test
    public void testGetAllFields() {
        Assert.assertEquals(0, ReflectUtil.getAllFields(TestEmptyClazz.class).size());
        Assert.assertEquals(2, ReflectUtil.getAllFields(BusinessModel.class).size());
    }

    @Test
    public void findField() throws IllegalAccessException {
        Field field = ReflectUtil.findField(BaseModel.class, "id", String.class);
        Assert.assertEquals("id", field.getName());
        Assert.assertEquals(String.class, field.getType());
        BaseModel baseModel = new BaseModel();
        ReflectUtil.makeAccessible(field);
        field.set(baseModel, "1");
        Assert.assertEquals("1", baseModel.getId());
    }

    @Test
    public void testGetMethod() {
        Assert.assertEquals(4, ReflectUtil.getClassMethod(BaseModel.class).length);
        Assert.assertEquals(2, ReflectUtil.getClassMethod(BusinessModel.class).length);
    }

    @Test
    public void testGetAllMethod() {
        List<Method[]> methods = ReflectUtil.getAllMethods(BusinessModel.class);
        Assert.assertEquals(2, methods.size());
        Assert.assertEquals(6, methods.get(0).length + methods.get(1).length);
    }

    @Test
    public void testFindMethod() {
        Method method = ReflectUtil.findMethod(BaseModel.class, "setId", new Class<?>[]{String.class});
        Assert.assertEquals("setId",  method.getName());
        Method methodV1 = ReflectUtil.findMethod(BaseModel.class, "setValue", new Class[]{String.class, String.class});
        Method methodV2 = ReflectUtil.findMethod(BaseModel.class, "setValue", new Class[]{String.class, Integer.class});
        Method methodV3 = ReflectUtil.findMethod(BaseModel.class, "setValue", new Class[]{String.class, Integer.class});
        Method methodV4 = ReflectUtil.findMethod(BaseModel.class, "setValue", new Class[]{String.class, String.class, Integer.class});
        Assert.assertEquals(2, methodV1.getParameterTypes().length);
        Assert.assertEquals(2, methodV2.getParameterTypes().length);
        Assert.assertEquals(2, methodV3.getParameterTypes().length);
        Assert.assertEquals(3, methodV4.getParameterTypes().length);
        Assert.assertNotEquals(methodV1, methodV2);
    }

    @Test
    public void testAllBeanMethod() {
        List<Method> beanMethod = ReflectUtil.getBeanMethods(BeanTest.class);
        List<Method> beanMethod2 = ReflectUtil.getBeanMethods(BeanTest.class);
        Assert.assertTrue(beanMethod.size() == 4);
    }

    @Test
    public void testFieldMethod() {
        Map<String, Method> methodMap = ReflectUtil.getBeanSetterMap(BeanTest.class);
        Map<String, Method> methodMap2 = ReflectUtil.getBeanSetterMap(BeanTest.class);
        Assert.assertTrue(methodMap.size() == 4);
    }
}
