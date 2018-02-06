package com.example.mapper;

import com.example.domain.Synonym;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SynonymMapper {

    @Results(id = "synonymMapping", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "table", column = "table_"),
            @Result(property = "column", column = "column_"),
            @Result(property = "columnName", column = "column_name"),
            @Result(property = "synonymName", column = "synonym_name"),
            @Result(property = "enable", column = "enable_")
    })
    @Select("select * from synonym")
    List<Synonym> findAll();

    @Select("select * from synonym where table_name=#{tableName} and enable_='Y'")
    List<Synonym> findByTableName(String tableName);
    @ResultMap("synonymMapping")
    @Select("select * from synonym where id=#{id} and enable_='Y'")
    Synonym findById(Integer id);

    @Update("update synonym set table_=#{table},column_=#{column},column_name=#{columnName},synonym_name=#{synonymName},enable_=#{enable} where id=#{id} ")
    int updateSynonym(Synonym synonym);

    @Insert("insert into synonym(table_,column_,column_name,synonym_name,enable_) values(#{table},#{column},#{columnName},#{synonymName},#{enable})")
    int insertSynonym(Synonym synonym);

    @Delete("delete from synonym where id=#{id}")
    int deleteSynonym(Integer id);
}
