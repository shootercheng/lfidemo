package com.example.utilstest;

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
    private TestService testService = new TestService();

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

    @Test
    public void testBatch2() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        TestService testService = new TestService();
        int sum = BatchInsertUtil.batchInsert(list, testService::insertList, 2);
        Assert.assertEquals(10, sum);
    }

    @Test
    public void testBatchLoop() {
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        int batchNum = 3;
        for (int i = 0; i < 10; i++) {
            list.add(i);
            sum = sum + BatchInsertUtil.batchInsertInLoop(list, testService::insertList, batchNum);
        }
        sum = sum + BatchInsertUtil.batchInsert(list, testService::insertList, batchNum);
        Assert.assertEquals(10, sum);
    }

    @Test
    public void testBatchLoop2() {
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        int batchNum = 2;
        for (int i = 0; i < 10; i++) {
            list.add(i);
            sum = sum + BatchInsertUtil.batchInsertInLoop(list, testService::insertList, batchNum);
        }
        sum = sum + BatchInsertUtil.batchInsert(list, testService::insertList, batchNum);
        Assert.assertEquals(10, sum);
    }
}
