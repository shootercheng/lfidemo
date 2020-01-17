package utilstest;

import com.example.utils.FileParseCommonUtil;
import com.example.utils.ReflectUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utilstest.models.BeanTest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class FileParseCommonUtilTest {
    private Map<String, String> fieldColumnMap;

    @Before
    public void before() {
        fieldColumnMap = new HashMap<>();
        fieldColumnMap.put("A", "code");
        fieldColumnMap.put("B", "name");
        fieldColumnMap.put("C", "status");
    }

    @Test
    public void testFieldMethod() {
        Map<String, Method> methodMap = FileParseCommonUtil.convertToColumnMethodMap(BeanTest.class, fieldColumnMap);
        Assert.assertEquals(3, methodMap.size());
        Assert.assertEquals(methodMap.get("A"), ReflectUtil.findMethod(BeanTest.class, "setCode", new Class[]{String.class}));
        Assert.assertEquals(methodMap.get("B"), ReflectUtil.findMethod(BeanTest.class, "setName", new Class[]{String.class}));
        Assert.assertEquals(methodMap.get("C"), ReflectUtil.findMethod(BeanTest.class, "setStatus", new Class[]{Boolean.class}));
    }
}
