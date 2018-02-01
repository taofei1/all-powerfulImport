package com.example.mapper;

import com.example.domain.Course;
import com.example.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Results({
            @Result(property = "cid",column = "cid"),
            @Result(property = "cname",column = "cname")
    })
    @Select("select * from course where cid=#{cid}")
    Course findByCid(@Param("cid") String id);
    @Insert("insert into course(cid,cname) values(#{cid},#{cname})")
    int insertCourse(Course course);
    @Update("<script>update course set <if test=\"cid!=null\">cid=#{cid}</if>" +
            "<if test=\"cname!=null\">cname=#{cname}</if>"+
            "</script>")
    void updateCourse(Course course);
    @Delete("delete from course where cid=#{cid}")
    void deleteCourse(String cid);
    @Select("select * from course where cid in(select cid from mark m where m.sid=#{sid}) ")
    List<Course> selectBySid(String sid);
}
