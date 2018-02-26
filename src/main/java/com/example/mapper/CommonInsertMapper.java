package com.example.mapper;

import com.example.demo.HeaderField;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommonInsertMapper {
    @Insert("<script>insert into ${tableName} " + "<foreach collection=\"fieldList\" item=\"headerField\" open=\"(\" close=\")\" separator=\",\">" + "${headerField.field} </foreach> values <foreach collection=\"values\" item=\"value_\" open=\"(\" close=\")\" separator=\",\">#{value_}</foreach></script> ")
    int insertByCritear(@Param("tableName") String tableName, @Param("fieldList") List<HeaderField> fieldList, @Param("values") List<Object> values);
}
