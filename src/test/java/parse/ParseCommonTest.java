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
        Map<String, String> fieldColumnMap = new HashMap<>(16);
        fieldColumnMap.put("A", "id");
        fieldColumnMap.put("B", "userName");
        fieldColumnMap.put("C", "score");
        fieldColumnMap.put("D", "date");
        Map<String, Method> columnMethodMap = FileParseCommonUtil.convertToColumnMethodMap(ReflectVo.class, fieldColumnMap);
        ParseParam parseParam = new ParseParam().setStartLine(1)
                .setFieldSetterMap(columnMethodMap);
        return parseParam;

    }
}
