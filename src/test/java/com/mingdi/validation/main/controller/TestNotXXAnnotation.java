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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mingdi.validation.main.CommandLineRunner;
import com.mingdi.validation.main.model.NotXXTestVo;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CommandLineRunner.class)
public class TestNotXXAnnotation {
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

	public MvcResult callApi(NotXXTestVo vo) throws JsonProcessingException, Exception {
		
		ObjectMapper mapper = new ObjectMapper();

		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/test/notTest").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(vo).getBytes()))
				.andDo(MockMvcResultHandlers.print()).andReturn();

		return mvcResult;
	}

	/**
	 * Not null test, only the field is null, there will be validation exception.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testNotNull() throws Exception {
		NotXXTestVo vo = new NotXXTestVo();
		vo.setNotBlankStr("not blank"); //OK field
		vo.setNotEmptyStr("not empty"); //OK field
		vo.setNotNullStr(null); // issue field
		MvcResult result = callApi(vo);
		MethodArgumentNotValidException ex = (MethodArgumentNotValidException) result.getResolvedException();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
		Assert.assertTrue(ex.toString().contains("notNullStr"));
	}
	
	/**
	 * @NotEmpty validation happens when the input string contains null or "".
	 * @throws Exception
	 */
	@Test
	public void testNotEmpty() throws Exception {
		NotXXTestVo vo = new NotXXTestVo();
		vo.setNotBlankStr("not blank"); //OK field
		vo.setNotEmptyStr(""); // issue field
		vo.setNotNullStr("not null");//OK field
		MvcResult result = callApi(vo);
		MethodArgumentNotValidException ex = (MethodArgumentNotValidException) result.getResolvedException();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
		Assert.assertTrue(ex.toString().contains("notEmptyStr"));
		
		vo.setNotEmptyStr(null);// issue field
		result = callApi(vo);
		ex = (MethodArgumentNotValidException) result.getResolvedException();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
		Assert.assertTrue(ex.toString().contains("notEmptyStr"));
		
		vo.setNotEmptyStr("not empty");
		result = callApi(vo);
		ex = (MethodArgumentNotValidException) result.getResolvedException();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
		Assert.assertNull(ex);
	}
	/**
	 * @NotBlank validation happens when the input string contains null or whitespace only.
	 * @throws Exception
	 */
	@Test
	public void testNotBlank() throws Exception {
		NotXXTestVo vo = new NotXXTestVo();
		vo.setNotBlankStr(""); //issue field
		vo.setNotEmptyStr("not empty"); // OK field
		vo.setNotNullStr("not null");//OK field
		MvcResult result = callApi(vo);
		MethodArgumentNotValidException ex = (MethodArgumentNotValidException) result.getResolvedException();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
		Assert.assertTrue(ex.toString().contains("notBlankStr"));
		
		vo.setNotBlankStr(null);// issue field
		result = callApi(vo);
		ex = (MethodArgumentNotValidException) result.getResolvedException();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
		Assert.assertTrue(ex.toString().contains("notBlankStr"));
		
		vo.setNotBlankStr("      ");
		result = callApi(vo);
		ex = (MethodArgumentNotValidException) result.getResolvedException();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
		Assert.assertTrue(ex.toString().contains("notBlankStr"));
		
		
		vo.setNotBlankStr("   test not blank ok   ");
		result = callApi(vo);
		ex = (MethodArgumentNotValidException) result.getResolvedException();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
		Assert.assertNull(ex);
	}
}
