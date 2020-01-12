package parse.define;

import com.alibaba.fastjson.JSONObject;
import com.example.model.vo.ParseParam;
import com.example.model.vo.ReflectVo;
import com.example.parse.BusinessDefineParse;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;


/**
 * @author chengdu
 * @date 2020/1/12
 */
public class ReflectVoDefineParse implements BusinessDefineParse {

    @Override
    public <T> void defineParse(T t, Object rowData, ParseParam parseParam) {
        if (t instanceof ReflectVo) {
            ReflectVo reflectVo = (ReflectVo) t;
            String address = "";
            String email = "";
            if (rowData instanceof String[]) {
                String[] rowArr = (String[]) rowData;
                address = rowArr[4];
                email = rowArr[5];
            } else if (rowData instanceof Row) {
                Row row = (Row) rowData;
                row.getCell(4).setCellType(CellType.STRING);
                row.getCell(5).setCellType(CellType.STRING);
                address = row.getCell(4).getStringCellValue();
                email = row.getCell(5).getStringCellValue();
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("address", address);
            jsonObject.put("email", email);
            reflectVo.setOtherInfo(jsonObject.toJSONString());
        }
    }
}
