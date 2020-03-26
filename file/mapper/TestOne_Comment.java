package com.single.batch.mapper;

@Repository(value = "batchlabel")
public interface BatchLabelMapper {
    /**
     * getId
     * @return Label
     */
    Label getId( );
    /**
     * getById
     * @param tableName tableName
     * @param id id
     * @return Label
     */
    Label getById(@Param("tableName")String tableName, @Param("id") long id);

    /**
     * selectList
     * @param map map
     * @param listMap listMap
     * @return List<String>
     */
    List<String> selectList(@Param(value = "map") Map<String, Object> map,
                            @Param(value = "listMap") List<Map<String, Integer>> listMap);
}
