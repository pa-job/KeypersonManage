package cn.soa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.soa.KeypersonManageApplication;
import cn.soa.dao.MicroblogDynamicMapper;
import cn.soa.entity.MicroblogDynamic;
import cn.soa.service.inter.UserServiceInter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KeypersonManageApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BlogsTest {
	@Autowired	
	private MicroblogDynamicMapper microblogDynamicMapper;
	
	@Test
	public void getBlogS(){
		List<MicroblogDynamic> blogs = microblogDynamicMapper.selectAll();
		System.out.println(blogs);
	}
}
