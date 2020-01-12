package utilstest;

import com.example.utils.BatchInsertUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class BatchUtilTest {

    @Test
    public void testBatch() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        TestService testService = new TestService();
        int sum = BatchInsertUtil.batchInsert(list, testService::insertList, 3);
        Assert.assertEquals(10, sum);
    }


    private static class TestService {

        public int insertList(List<Integer> list) {
            return list.size();
        }
    }
}
