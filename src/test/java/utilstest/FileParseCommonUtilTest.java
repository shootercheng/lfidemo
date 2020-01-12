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
    private Map<Integer, String> fieldOrderMap;

    @Before
    public void before() {
        fieldOrderMap = new HashMap<>();
        fieldOrderMap.put(0, "code");
        fieldOrderMap.put(1, "name");
        fieldOrderMap.put(2, "status");
    }

    @Test
    public void testFieldMethod() {
        Map<Integer, Method> methodMap = FileParseCommonUtil.convertToColumnMethodMap(BeanTest.class, fieldOrderMap);
        Assert.assertEquals(3, methodMap.size());
        Assert.assertEquals(methodMap.get(0), ReflectUtil.findMethod(BeanTest.class, "setCode", new Class[]{String.class}));
        Assert.assertEquals(methodMap.get(1), ReflectUtil.findMethod(BeanTest.class, "setName", new Class[]{String.class}));
        Assert.assertEquals(methodMap.get(2), ReflectUtil.findMethod(BeanTest.class, "setStatus", new Class[]{Boolean.class}));
    }
}
