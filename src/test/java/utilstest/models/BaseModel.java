package utilstest.models;

import lombok.Data;

/**
 * @author chengdu
 * @date 2019/12/4
 */
public class BaseModel {
    private String id;
    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValue(String id, String code) {
        this.id = id;
        this.code =code;
    }

    public void setValue(String id, String code, Integer order) {
        this.id = id;
        this.code =code;
        System.out.println(order);
    }

    public void setValue(String id, Integer order) {
        this.id = id;
        System.out.println(order);
    }

    public void setValue(Integer id, String order) {
        System.out.println(order);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
