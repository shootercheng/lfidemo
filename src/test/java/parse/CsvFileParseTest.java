package parse;

import com.example.constant.FileType;
import com.example.model.vo.ReflectVo;
import com.example.parse.FileParse;
import com.example.parse.FileParseFactory;
import com.example.utils.FileParseCommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class CsvFileParseTest extends ParseCommonTest {

    @Test
    public void testCsvParse() {
        String filePath = "file/test.csv";
        FileParse fileParse = FileParseFactory.createFileParse(FileParseCommonUtil.getFileType(filePath));
        List<ReflectVo> reflectVoList = fileParse.parseFile(filePath, ReflectVo.class, createReflectParam());
        Assert.assertEquals(6, reflectVoList.size());
    }
}
