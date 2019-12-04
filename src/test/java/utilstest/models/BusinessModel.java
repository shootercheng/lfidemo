package utilstest.models;

import lombok.Data;

/**
 * @author chengdu
 * @date 2019/12/4
 */
public class BusinessModel extends BaseModel {
    private String businessType;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
