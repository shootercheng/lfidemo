import com.example.utils.DateUtil;
import org.junit.Test;

/**
 * @author chengdu
 * @date 2019/8/31.
 */
public class DateUtilTest {

    @Test
    public void test() {
        System.out.println(DateUtil.parseStrToDate("2019-8-30"));
        System.out.println(DateUtil.parseStrToDate("2019/8/30"));
        System.out.println(DateUtil.parseStrToDate("2019.8.30"));
        System.out.println(DateUtil.parseStrToDate("2019/8/30 23:20:23"));
    }
}
