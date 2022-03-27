package com.example.demo.mapper;

import com.example.demo.entity.RunoobTblOne;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fanrongchen
 * @since 2022-03-20
 */
public interface RunoobTblOneMapper extends BaseMapper<RunoobTblOne> {

    RunoobTblOne getOneByRunoobId(@Param("runoob_id") String id);

    /**
     * 使用information_schema检查表是否存在
     * @param tableSchema
     * @param tableName
     * @return
     */
    Integer checkTableExistsWithSchema(@Param("tableSchema")String tableSchema, @Param("tableName")String tableName);

    public void createNewTableAndInsertData(@Param("tableName")String tableName);
}
