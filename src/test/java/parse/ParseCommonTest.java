package parse;

import com.example.model.vo.ParseParam;
import com.example.model.vo.ReflectVo;
import com.example.utils.FileParseCommonUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class ParseCommonTest {

    public ParseParam createReflectParam() {
        Map<Integer, String> fieldColumnMap = new HashMap<>(16);
        fieldColumnMap.put(0, "id");
        fieldColumnMap.put(1, "userName");
        fieldColumnMap.put(2, "score");
        fieldColumnMap.put(3, "date");
        Map<Integer, Method> columnMethodMap = FileParseCommonUtil.convertToColumnMethodMap(ReflectVo.class, fieldColumnMap);
        ParseParam parseParam = new ParseParam().setStartLine(1)
                .setFieldSetterMap(columnMethodMap);
        return parseParam;

    }
}
