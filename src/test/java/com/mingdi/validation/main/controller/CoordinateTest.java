package com.mingdi.validation.main.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mingdi.validation.main.CommandLineRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CommandLineRunner.class)
public class CoordinateTest {
	private MockHttpServletRequest request;
	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();// 建议使用这种
		request = new MockHttpServletRequest();

		request.setCharacterEncoding("UTF-8");
	}
	public MvcResult callGetApi(String paramName,String paramValue, String path) throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/test/"+path)
						.param(paramName, paramValue)
//				.content(mapper.writeValueAsBytes(param))
						)
				.andDo(MockMvcResultHandlers.print()).andReturn();

		return mvcResult;
	}
	@Test
	public void testLatitude() {
		String path = "testLatitude";
		MvcResult result;
		try {
			result = callGetApi("lat","1212.12121",path);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertTrue(e.getMessage().contains("Invalid latitude format."));
		}
		
		
	}
	
	@Test
	public void testLongitude(){
		String path = "testLongitude";
		MvcResult result;
		try {
			result = callGetApi("lon","1212.12121",path);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertTrue(e.getMessage().contains("Invalid longitude format."));
		}
	}

}
