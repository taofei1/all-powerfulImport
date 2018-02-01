package com.example.mapper;
import com.example.domain.Student;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StudentMapper {
     @Results(value = {
             @Result(property = "sid", column = "sid"),
             @Result(property = "sname", column = "sname"),
             @Result(property = "age", column = "age"),
             @Result(property = "birthday", column = "birthday"),
         //    @Result(property = "list", many = @Many(select="com.example.mapper.Course.findByCid",fetchType = FetchType.LAZY))

     })
     @Select("select * from student where sid=#{sid}")
    Student findBySid(@Param("sid") String id);
     @Insert("insert into student(sid,sname,age,birthday) values(#{sid},#{sname},#{age},#{birthday})")
    int insertStudent(Student student);
     @Update("<script>update student set <if test=\"sname!=null\">sname=#{sname}</if>" +
             "<if test=\"age!=null and age!=0\">age=#{age}</if>" +
             "<if test=\"birthday!=null\">birthday=#{birthday}</if> where sid=#{sid}</script>")
     void updateStudent(Student student);
     @Delete("delete from student where sid=#{sid}")
     void deleteStudent(String sid);
}
