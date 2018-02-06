package com.example.mapper;

import com.example.domain.Difference;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DifferenceMapper {
    @Results(id = "differenceMapping", value = {@Result(property = "id", column = "id", id = true), @Result(property = "tableName", column = "table_name"), @Result(property = "field", column = "field"), @Result(property = "fieldName", column = "field_name"), @Result(property = "differenceName", column = "difference_name"), @Result(property = "isEnable", column = "enable_")})
    @Select("select * from difference where table_name=#{tableName} and enable_='Y'")
    List<Difference> findByTableName(String tableName);

    @ResultMap("differenceMapping")
    @Select("select * from difference where DIFFERENCE_NAME=#{differenceName} and enable_='Y'")
    Difference findByDifferenceName(String differeceName);
}
