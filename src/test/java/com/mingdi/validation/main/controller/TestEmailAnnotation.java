package com.mingdi.validation.main.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mingdi.validation.main.CommandLineRunner;
import com.mingdi.validation.main.model.EmailTestVo;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CommandLineRunner.class)
public class TestEmailAnnotation {

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

	public MvcResult callPostApi(EmailTestVo param,String path) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/test/"+path)
						.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(param)))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		return mvcResult;
	}
	public MvcResult callGetApi(String param) throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/test/emailTest")
						.param("email", param)
						)
				.andDo(MockMvcResultHandlers.print()).andReturn();

		return mvcResult;
	}
	@Test
	public void testEmail() throws Exception {
		EmailTestVo param = new EmailTestVo();
		param.setEmail("test email vo");
		MvcResult result = callPostApi(param,"emailTest");
		MethodArgumentNotValidException ex = (MethodArgumentNotValidException) result.getResolvedException();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
		Assert.assertTrue(ex.toString().contains("must be a well-formed email address"));
	}
	
	@Test
	public void testEmailNotBlankWithLogging() throws Exception {
		EmailTestVo param = new EmailTestVo();
		param.setEmail("test email vo");
		MvcResult result = callPostApi(param,"validateNotBlankGroupLogError");
		Assert.assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
		Assert.assertEquals("0", result.getResponse().getContentAsString());
		
		param.setEmail("     ");
		
		result = callPostApi(param,"validateNotBlankGroupLogError");
		Exception ex = result.getResolvedException();
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(),result.getResponse().getStatus());
		Assert.assertNotNull(ex);
	}
	@Test
	public void testFirstGroup() throws Exception {
		EmailTestVo param = new EmailTestVo();
		param.setEmail("test email vo");
		String path = "validateEmailGroup";
		MvcResult result = callPostApi(param,path);
		MethodArgumentNotValidException ex = (MethodArgumentNotValidException) result.getResolvedException();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
		Assert.assertTrue(ex.toString().contains("must be a well-formed email address"));
	}
	
	@Test
	public void testSecondGroup() throws Exception {
		EmailTestVo param = new EmailTestVo();
		param.setEmail("test email vo");
		String path = "validateNotBlankGroup";
		MvcResult result = callPostApi(param,path);
		MethodArgumentNotValidException ex = (MethodArgumentNotValidException) result.getResolvedException();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
		Assert.assertNull(ex);
//		Assert.assertTrue(ex.toString().contains("must be a well-formed email address"));
	}
	
	@Test(expected=Exception.class)
	public void testRequestParam() throws Exception {
		String param = "test get param";
		MvcResult result = callGetApi(param);
		MethodArgumentNotValidException ex = (MethodArgumentNotValidException) result.getResolvedException();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
		Assert.assertTrue(ex.toString().contains("must be a well-formed email address"));
	}
}
