package utilstest;

import com.example.model.vo.ReflectVo;
import com.example.utils.MapToVoUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chengdu
 * @date 2019/12/4
 */
public class MapToVoUtilTest {

    @Test
    public void testToVo() {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("userName".toLowerCase(), "cd");
        map.put("score", "99.9");
        map.put("sort", "31234124234122354");
        map.put("date", "2019/8/31");
        ReflectVo reflectVo = MapToVoUtil.reflectByStrMap(ReflectVo.class, map);
        // cache setXX method
        ReflectVo reflectVo2 = MapToVoUtil.reflectByStrMap(ReflectVo.class, map);
        Assert.assertEquals("cd", reflectVo.getUserName());
    }
}
