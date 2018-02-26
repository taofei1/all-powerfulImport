package com.example.demo;

import com.example.domain.Course;
import com.example.domain.Student;
import com.example.mapper.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

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
	@Autowired
	private SynonymMapper synonymMapper;
	@Autowired
	private CommonInsertMapper commonInsertMapper;
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;

	//@Autowired
	//private MockHttpSession session;// 注入模拟的http session
	//
	//@Autowired
	//private MockHttpServletRequ1est request;// 注入模拟的http request\

	@Before // 在测试开始前初始化工作
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testPrimaryKeys() throws Exception {
		MvcResult result = mockMvc.perform(get("/importStudent/primaryKeys")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
				.andReturn();// 返回执行请求的结果
		System.out.println(result.getResponse().getContentAsString());

	}
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

	@Test
	public void testFindAll() {
		System.out.println(synonymMapper.findAll());
	}

	@Test
	public void findOne() {
		System.out.println(synonymMapper.findById(2));
	}

	@Test
	public void testCommonInsert() {
		List fieldList = Arrays.asList("sid", "sname", "age");
		List valueList = Arrays.asList("222", "xiaohua", 22);
		commonInsertMapper.insertByCritear("student", fieldList, valueList);
	}
}
