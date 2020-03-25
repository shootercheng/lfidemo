package com.single.batch.mapper;

import com.single.batch.entity.Label;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author:0xOO
 * @Date: 2018/9/26 0026
 * @Time: 15:20
 */
@Repository(value = "batchlabel")
    /**
     * getById
     * @param tableName tableName
     * @param id id
     * @return Label
     */public interface BatchLabelMapper {
    Label getById(@Param("tableName")String tableName, @Param("id") long id);

    /**
     * existTable
     * @param tableName tableName
     * @return int
     */
    int existTable(String tableName);

    /**
     * dropTable
     * @param tableName tableName
     * @return int
     */
    int dropTable(@Param("tableName")String tableName);

    /**
     * createNewTable
     * @param tableName tableName
     * @return int
     */
    int createNewTable(@Param("tableName")String tableName);

    /**
     * insert
     * @param tableName tableName
     * @param label label
     * @return int
     */
    int insert(@Param("tableName")String tableName,@Param("label") Label label);

    /**
     * insertBatch
     * @param tableName tableName
     * @param labels labels
     * @return void
     */
    void insertBatch(@Param("tableName")String tableName, List<Label> labels);

    /**
     * truncateTable
     * @param tableName tableName
     * @return int
     */
    int truncateTable(@Param("tableName")String tableName);

    /**
     * selectPage
     * @param map map
     * @return List<String>
     */
    List<String> selectPage(Map<String, Object> map);

    /**
     * countTbSum
     * @param map map
     * @return int
     */
    int countTbSum(Map<String, Object> map);

    /**
     * selectMap
     * @param map map
     * @param listMap listMap
     * @return Map<String, List<Object>>
     */
    Map<String, List<Object>> selectMap(Map<String, Object> map, List<Map<String, Integer>> listMap);
}