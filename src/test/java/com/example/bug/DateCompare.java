package com.example.bug;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author James
 */
public class DateCompare {
    // 日期格式
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Test
    public void testSortSameDay() throws ParseException {
        List<DatePO> datePOList = new ArrayList<>();
        Date date1 = DateUtils.parseDate("2021-04-09 20:36:00", DATE_PATTERN);
        Date date2 = DateUtils.parseDate("2021-04-09 20:37:00", DATE_PATTERN);
        Date date3 = DateUtils.parseDate("2021-04-09 20:38:00", DATE_PATTERN);
        DatePO datePO1 = new DatePO();
        datePO1.setId(1);
        datePO1.setDate(date1);
        datePOList.add(datePO1);
        DatePO datePO2 = new DatePO();
        datePO2.setId(2);
        datePO2.setDate(date2);
        datePOList.add(datePO2);
        DatePO datePO3 = new DatePO();
        datePO3.setId(3);
        datePO3.setDate(date3);
        datePOList.add(datePO3);
        // 时间升序排序
        datePOList.sort(new Comparator<DatePO>() {
            @Override
            public int compare(DatePO o1, DatePO o2) {
                return (int) (o1.getDate().getTime() - o2.getDate().getTime());
            }
        });
        System.out.println(datePOList);
        datePOList = datePOList.stream().sorted(Comparator.comparingLong(po -> po.getDate().getTime()))
                .collect(Collectors.toList());
        System.out.println(datePOList);
    }

    @Test
    public void testSortSameMonth() throws ParseException {
        List<DatePO> datePOList = new ArrayList<>();
        Date date1 = DateUtils.parseDate("2021-04-07 20:36:00", DATE_PATTERN);
        Date date2 = DateUtils.parseDate("2021-04-08 20:37:00", DATE_PATTERN);
        Date date3 = DateUtils.parseDate("2021-04-09 20:38:00", DATE_PATTERN);
        DatePO datePO1 = new DatePO();
        datePO1.setId(1);
        datePO1.setDate(date1);
        datePOList.add(datePO1);
        DatePO datePO2 = new DatePO();
        datePO2.setId(2);
        datePO2.setDate(date2);
        datePOList.add(datePO2);
        DatePO datePO3 = new DatePO();
        datePO3.setId(3);
        datePO3.setDate(date3);
        datePOList.add(datePO3);
        // 时间升序排序
        datePOList.sort(new Comparator<DatePO>() {
            @Override
            public int compare(DatePO o1, DatePO o2) {
                return (int) (o1.getDate().getTime() - o2.getDate().getTime());
            }
        });
        System.out.println(datePOList);
        datePOList = datePOList.stream().sorted(Comparator.comparingLong(po -> po.getDate().getTime()))
                .collect(Collectors.toList());
        System.out.println(datePOList);
    }

    /**
     * diff month may produce bug
     * @throws ParseException
     */
    @Test
    public void testSortDiffMonth() throws ParseException {
        List<DatePO> datePOList = new ArrayList<>();
        Date date1 = DateUtils.parseDate("2021-02-09 20:36:00", DATE_PATTERN);
        Date date2 = DateUtils.parseDate("2021-03-09 20:37:00", DATE_PATTERN);
        Date date3 = DateUtils.parseDate("2021-04-09 20:38:00", DATE_PATTERN);
        DatePO datePO1 = new DatePO();
        datePO1.setId(1);
        datePO1.setDate(date1);
        datePOList.add(datePO1);
        DatePO datePO2 = new DatePO();
        datePO2.setId(2);
        datePO2.setDate(date2);
        datePOList.add(datePO2);
        DatePO datePO3 = new DatePO();
        datePO3.setId(3);
        datePO3.setDate(date3);
        datePOList.add(datePO3);
        // 时间升序排序
        datePOList.sort(new Comparator<DatePO>() {
            @Override
            public int compare(DatePO o1, DatePO o2) {
                return (int) (o1.getDate().getTime() - o2.getDate().getTime());
            }
        });
        System.out.println(datePOList);
        datePOList = datePOList.stream().sorted(Comparator.comparingLong(po -> po.getDate().getTime()))
                .collect(Collectors.toList());
        System.out.println(datePOList);
    }

    @Test
    public void testSortDiffYear() throws ParseException {
        List<DatePO> datePOList = new ArrayList<>();
        Date date1 = DateUtils.parseDate("2019-04-09 20:36:00", DATE_PATTERN);
        Date date2 = DateUtils.parseDate("2020-04-09 20:37:00", DATE_PATTERN);
        Date date3 = DateUtils.parseDate("2021-04-09 20:38:00", DATE_PATTERN);
        DatePO datePO1 = new DatePO();
        datePO1.setId(1);
        datePO1.setDate(date1);
        datePOList.add(datePO1);
        DatePO datePO2 = new DatePO();
        datePO2.setId(2);
        datePO2.setDate(date2);
        datePOList.add(datePO2);
        DatePO datePO3 = new DatePO();
        datePO3.setId(3);
        datePO3.setDate(date3);
        datePOList.add(datePO3);
        // 时间升序排序
        datePOList.sort(new Comparator<DatePO>() {
            @Override
            public int compare(DatePO o1, DatePO o2) {
                return (int) (o1.getDate().getTime() - o2.getDate().getTime());
            }
        });
        System.out.println(datePOList);
        datePOList = datePOList.stream().sorted(Comparator.comparingLong(po -> po.getDate().getTime()))
                .collect(Collectors.toList());
        System.out.println(datePOList);
    }
}
