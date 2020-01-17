package parse;

import com.example.constant.CommonConstant;
import com.example.model.vo.ParseParam;
import com.example.model.vo.ReflectVo;
import com.example.parse.FileParse;
import com.example.parse.FileParseCreator;
import com.example.utils.FileParseCommonUtil;
import org.junit.Assert;
import org.junit.Test;
import parse.define.InfoFormat;
import parse.define.ReflectVoDefineParse;

import java.util.List;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class CsvFileParseTest extends ParseCommonTest {

    @Test
    public void testCsvParse() {
        String filePath = "file/test.csv";
        FileParse fileParse = FileParseCreator.createFileParse(FileParseCommonUtil.getFileType(filePath));
        List<ReflectVo> reflectVoList = fileParse.parseFile(filePath, ReflectVo.class, createReflectParam());
        Assert.assertEquals(6, reflectVoList.size());
    }

    @Test
    public void testDefineParser() {
        String filePath = "file/test.csv";
        ParseParam parseParam = super.createReflectParam()
                .setBusinessDefineParse(new ReflectVoDefineParse())
                .setEncode(CommonConstant.GBK);
        FileParse fileParse = FileParseCreator.createFileParse(FileParseCommonUtil.getFileType(filePath));
        List<ReflectVo> reflectVoList = fileParse.parseFile(filePath, ReflectVo.class, parseParam);
        Assert.assertEquals(6, reflectVoList.size());
    }

    @Test
    public void testDefineFormat() {
        String filePath = "file/test.csv";
        ParseParam parseParam = super.createReflectParam()
                .setBusinessDefineParse(new ReflectVoDefineParse())
                .setEncode(CommonConstant.GBK).setCellFormat(new InfoFormat());
        FileParse fileParse = FileParseCreator.createFileParse(FileParseCommonUtil.getFileType(filePath));
        List<ReflectVo> reflectVoList = fileParse.parseFile(filePath, ReflectVo.class, parseParam);
        Assert.assertEquals(6, reflectVoList.size());
        Assert.assertEquals("chengdu", reflectVoList.get(0).getUserName());
    }
}
