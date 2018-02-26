package com.example.mapper;

import com.example.domain.FieldMapping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FieldMappingMapper {
    @Results({@Result(id = true, property = "id", column = "id"), @Result(property = "tableName", column = "table_name"), @Result(property = "field", column = "field"), @Result(property = "fieldName", column = "field_name"), @Result(property = "fieldType", column = "field_type"), @Result(property = "defaultValue", column = "default_value"), @Result(property = "isEnable", column = "enable_")})
    @Select("select * from fieldmapping where table_name=#{tableName} and enable_='Y'")
    List<FieldMapping> findByTableName(String tableName);


}
