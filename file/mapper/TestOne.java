package com.single.batch.mapper;

@Repository(value = "batchlabel")
public interface BatchLabelMapper {
    Label getId( );
    Label getById(@Param("tableName")String tableName, @Param("id") long id);

    List<String> selectList(@Param(value = "map") Map<String, Object> map,
                            @Param(value = "listMap") List<Map<String, Integer>> listMap);
}