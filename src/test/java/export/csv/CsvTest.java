package export.csv;

import com.example.model.vo.ExportParam;
import com.example.utils.ExportFileUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;
import utilstest.TestService;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chengdu
 * @date 2020/2/19
 */
public class CsvTest {

    @Test
    public void testWrite() throws Exception {
        FileOutputStream fos = new FileOutputStream("file/export/abc.csv");
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF8");

        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("姓名", "年龄", "家乡");
        CSVPrinter csvPrinter = new CSVPrinter(osw, csvFormat);

//        csvPrinter = CSVFormat.DEFAULT.withHeader("姓名", "年龄", "家乡").print(osw);

        for (int i = 0; i < 10; i++) {
            csvPrinter.printRecord("张三", 20, "湖北");
        }
        csvPrinter.flush();
        csvPrinter.close();
    }

    @Test
    public void testExport() {
        String filePath = "file/export/2.csv";
        String filePathTxt = "file/export/2.txt";
        List<String> rowData = new ArrayList<>(100000);
        String header = "姓名,性别,手机号码";
        rowData.add(header);
        String row = "chengdu,man,11111111111";
        for (int i = 0; i < 100000; i++) {
            rowData.add(row);
        }
        ExportFileUtil.exportFile(filePath, rowData, Charset.forName("GBK"), false);
        ExportFileUtil.exportFile(filePathTxt, rowData, Charset.forName("GBK"), false);
    }

    @Test
    public void testExportAppend() {
        String filePath = "file/export/3.csv";
        String filePathTxt = "file/export/3.txt";
        List<String> rowData = new ArrayList<>(10000);
        String header = "姓名,性别,手机号码";
        rowData.add(header);
        String row = "chengdu,man,11111111111";
        for (int i = 0; i < 10; i++) {
            // 每次查询 1万条数据
            for (int j = 0; j < 10000; j++) {
                rowData.add(row);
            }
            ExportFileUtil.exportFile(filePath, rowData, StandardCharsets.UTF_8, true);
            ExportFileUtil.exportFile(filePathTxt, rowData, StandardCharsets.UTF_8, true);
            rowData.clear();
        }
    }

    @Test
    public void testDbQueryExport() {
        TestService testService = new TestService();
        String filePath = "file/export/5.csv";
        String filePathTxt = "file/export/5.txt";
        ExportParam exportParam = new ExportParam();
        exportParam.setHeaderData("姓名,性别,手机号码");
        exportParam.setSum(100);
        exportParam.setPageNum(10);
        exportParam.setCharset(StandardCharsets.UTF_8);
        exportParam.setSearchParam(new HashMap<>());
        ExportFileUtil.exportFilePageDb(filePath, testService::queryDbPage, exportParam);
        ExportFileUtil.exportFilePageDb(filePathTxt, testService::queryDbPage, exportParam);
    }

    @Test
    public void testRead() throws IOException {
        InputStream is = new FileInputStream("E:/cjsworkspace/cjs-excel-demo/target/abc.csv");
        InputStreamReader isr = new InputStreamReader(is, "GBK");
        Reader reader = new BufferedReader(isr);

        CSVParser parser = CSVFormat.EXCEL.withHeader("name", "age", "jia").parse(reader);
//        CSVParser csvParser = CSVParser.parse(reader, CSVFormat.DEFAULT.withHeader("name", "age", "jia"));
        List<CSVRecord> list = parser.getRecords();
        for (CSVRecord record : list) {
            System.out.println(record.getRecordNumber()
                    + ":" + record.get("name")
                    + ":" + record.get("age")
                    + ":" + record.get("jia"));
        }

        parser.close();
    }

    /**
     * Parsing an Excel CSV File
     */
    @Test
    public void testParse() throws Exception {
        Reader reader = new FileReader("C:/Users/Administrator/Desktop/abc.csv");
        CSVParser parser = CSVFormat.EXCEL.parse(reader);
        for (CSVRecord record : parser.getRecords()) {
            System.out.println(record);
        }
        parser.close();
    }

    /**
     * Defining a header manually
     */
    @Test
    public void testParseWithHeader() throws Exception {
        Reader reader = new FileReader("C:/Users/Administrator/Desktop/abc.csv");
        CSVParser parser = CSVFormat.EXCEL.withHeader("id", "name", "code").parse(reader);
        for (CSVRecord record : parser.getRecords()) {
            System.out.println(record.get("id") + ","
                    + record.get("name") + ","
                    + record.get("code"));
        }
        parser.close();
    }

    /**
     * Using an enum to define a header
     */
    enum MyHeaderEnum {
        ID, NAME, CODE;
    }

    @Test
    public void testParseWithEnum() throws Exception {
        Reader reader = new FileReader("C:/Users/Administrator/Desktop/abc.csv");
        CSVParser parser = CSVFormat.EXCEL.withHeader(MyHeaderEnum.class).parse(reader);
        for (CSVRecord record : parser.getRecords()) {
            System.out.println(record.get(MyHeaderEnum.ID) + ","
                    + record.get(MyHeaderEnum.NAME) + ","
                    + record.get(MyHeaderEnum.CODE));
        }
        parser.close();
    }


    private List<Map<String, String>> recordList = new ArrayList<>();

    @Before
    public void init() {
        for (int i = 0; i < 5; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("name", "zhangsan");
            map.put("code", "001");
            recordList.add(map);
        }
    }


}
