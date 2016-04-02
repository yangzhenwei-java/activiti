package com.beebank.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.HashMap;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

public class IdentityControllerTest extends BaseTest{

	
static String PATH ="/identity/";
	
	
	public  static Map<String,String> CONSTANT = new HashMap<String,String>()
	{
		{
			put("groupId", "deptLeader");  
	        put("groupName", "deptLeader-name"); 
	        put("type", "test");
			put("groupId1", "deptLeader1");  
	        put("groupName1", "deptLeader-name1"); 
	        put("type1", "test1");
	        put("lastName", "san");
	        put("firstName", "zhang");
	        put("userId", "zhangsan");
	        put("lastName1", "si");
	        put("firstName1", "li");
	        put("userId1", "lisi");
		}
	};
	
	@Test
	public void groupList() throws UnsupportedEncodingException, Exception{
		 mockMvc.perform(
				MockMvcRequestBuilders.get(PATH +"/group/list"))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
	}

	
	@Test
	public void saveGroupTest() throws UnsupportedEncodingException, Exception{
		 mockMvc.perform(
					MockMvcRequestBuilders.post(PATH +"/group/save")
					.param("groupId", CONSTANT.get("groupId"))
					.param("groupName", CONSTANT.get("groupName"))
					.param("type", CONSTANT.get("groupName")))
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
	}
	
	@Test 
	public void saveUserTest() throws UnsupportedEncodingException, Exception{
		 mockMvc.perform(
					MockMvcRequestBuilders.post(PATH +"/user/save")
					.param("lastName", CONSTANT.get("lastName"))
					.param("firstName", CONSTANT.get("firstName"))
					.param("userId", CONSTANT.get("userId")))
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
	}
	
	@Test 
	public void saveUserTest1() throws UnsupportedEncodingException, Exception{
		 mockMvc.perform(
					MockMvcRequestBuilders.post(PATH +"/user/save")
					.param("lastName", CONSTANT.get("lastName1"))
					.param("firstName", CONSTANT.get("firstName1"))
					.param("userId", CONSTANT.get("userId1")))
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
	}
	
	@Test 
	public void groupForUserTest() throws UnsupportedEncodingException, Exception{
		 mockMvc.perform(
					MockMvcRequestBuilders.post(PATH +"/group/set")
					.param("group", CONSTANT.get("groupId"))
					.param("userId", CONSTANT.get("userId1")))
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
	}
	
}
