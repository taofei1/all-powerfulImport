package com.example.demo;

import com.example.domain.Course;
import com.example.domain.Student;
import com.example.mapper.CourseMapper;
import com.example.mapper.MarkMapper;
import com.example.mapper.StudentMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "com.example.mapper")
public class DemoApplicationTests {
	@Autowired
    private StudentMapper studentMapper;
	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private MarkMapper markMapper;
	@Test
	public void contextLoads() {
		Student s=studentMapper.findBySid("B13040932");
		System.out.println(s);
	}
	@Test
	public void addStudent() throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Student stu=new Student("123","张三",sdf.parse("1996-10-12"),20);
		studentMapper.insertStudent(stu);
	}
	@Test
	public void updateStudent(){
		Student student=new Student();
		student.setSid("12");
	//	student.setSname("李四");
		studentMapper.updateStudent(student);
	}
	@Test
	public void deleteStudent(){
         studentMapper.deleteStudent("12");
	}
	@Test
	public void addCourse(){
		Course course=new Course("c1","高数");
		courseMapper.insertCourse(course);
	}
	@Test
	public void selectCourse(){
		System.out.println(courseMapper.findByCid("c1"));
	}
	@Test
	public void updateCourse(){
		Course course=new Course();
		course.setCname("线代");
		courseMapper.updateCourse(course);

	}
	@Test
	public void deleteCourse(){
		courseMapper.deleteCourse("c1");
	}
	@Test
	public void testMark(){
		Assert.assertEquals(20,markMapper.findMarkBySidAndCid("B13040932","1"));
	}
	@Test
	public void testUpdateMark(){
		Student s=new Student();
		s.setSid("B13040932");
		Course c=new Course();
		c.setCid("1");
		markMapper.updateMarkBySidAndCid(s,c,2);
	}
    @Test
	public void selectBySid(){
		System.out.println(courseMapper.selectBySid("B13040932"));
	}
}
