package com.example.mapper;

import com.example.domain.Course;
import com.example.domain.Mark;
import com.example.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface MarkMapper {
    @Insert("insert into mark(sid,cid,mark) values(#{sid},#{cid},#{mark})")
     int selectCourse(String sid,String cid,int mark);
    @Select("select mark from mark where sid=#{sid} and cid=#{cid}")
    int findMarkBySidAndCid(@Param("sid") String sid,@Param("cid") String cid);
    @Update("update mark set mark=#{mark} where sid=#{s.sid} and cid=#{c.cid}")
    void updateMarkBySidAndCid(@Param("s") Student student, @Param("c") Course course,@Param("mark") Integer mark);

    @Select("select m.*,s.*,c.* from mark m left join student s on s.sid=m.sid " +
            "left join course c on c.cid=m.cid")
    List<Mark> getAllMark();
}

