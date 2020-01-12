package utilstest.models;

/**
 * @author chengdu
 * @date 2019/12/4
 */
public class BeanTest extends BaseModel {
    private String name;

    private boolean status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
