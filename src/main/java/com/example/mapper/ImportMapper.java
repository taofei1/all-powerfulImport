package com.example.mapper;

import com.example.domain.PrimaryKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImportMapper {
    @Select("select b.* from importsettings a,primarykey_manage b where INSTR(a.primarykey,b.primarykey_name)>0 and " + "a.import_name=#{importName} AND a.enable_ = 'Y'")
    List<PrimaryKey> findByNameWithNoPrimaryKey(String importName);

    @Select("select b.* from importsettings a,primarykey_manage b where INSTR(a.primarykey,b.primarykey_name) > 0" + "AND a.import_name =#{importName} AND a.enable_ = 'Y' AND INSTR(#{primaryKeyName},b.primarykey_name) > 0")
    List<PrimaryKey> findByNameWithPrimaryKey(@Param("importName") String importName, @Param("primaryKeyName") String primaryKeyName);
}
