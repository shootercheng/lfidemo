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
public interface BatchLabelMapper {
    Label getById(@Param("tableName")String tableName, @Param("id") long id);

    int existTable(String tableName);

    int dropTable(@Param("tableName")String tableName);

    int createNewTable(@Param("tableName")String tableName);

    int insert(@Param("tableName")String tableName,@Param("label") Label label);

    void insertBatch(@Param("tableName")String tableName, List<Label> labels);

    int truncateTable(@Param("tableName")String tableName);

    List<String> selectPage(Map<String, Object> map);

    int countTbSum(Map<String, Object> map);

    Map<String, List<Object>> selectMap(Map<String, Object> map, List<Map<String, Integer>> listMap);
    List<String> selectList(@Param(value = "map") Map<String, Object> map,
                            @Param(value = "listMap") List<Map<String, Integer>> listMap);
}
