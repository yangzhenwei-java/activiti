package com.beebank.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.alibaba.fastjson.JSONObject;

public class TaskControllerTest extends BaseTest{
	
	static String PATH ="/task/";
	
	
	public  static Map<String,String> CONSTANT = new HashMap<String,String>()
	{
		{
			put("processDefinitionId", "userAndGroupInUserTask:1:4");  
	        put("userId", "zhansan"); 
	        put("tenantId", "3");
	        put("userId1", "lisi"); 
	        put("taskId", "5005"); 
		}
	};
	
	@Test
	public void listTest() throws UnsupportedEncodingException, Exception{
		 mockMvc.perform(
				MockMvcRequestBuilders.get(PATH +"list")
				.param("userId", CONSTANT.get("userId1"))
				.param("tenantId", CONSTANT.get("tenantId")))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
	}

	
	@Test
	public void claimTest() throws UnsupportedEncodingException, Exception{
		 mockMvc.perform(
				MockMvcRequestBuilders.get(PATH +"claim/"+CONSTANT.get("taskId")+"/"+CONSTANT.get("userId1")))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
	}
	
	@Test
	public void unclaimTest() throws UnsupportedEncodingException, Exception{
		mockMvc.perform(
				MockMvcRequestBuilders.get(PATH +"unclaim/"+CONSTANT.get("taskId")+"/"+CONSTANT.get("taskId")))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
	}
	
	@Test
	public void readTaskForm() throws UnsupportedEncodingException, Exception{
		mockMvc.perform(
				MockMvcRequestBuilders.get(PATH +"/getform/"+CONSTANT.get("taskId")))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print()).andReturn();
	}
	
	@Test 
	public void completeTaskTest() throws Exception{
		JSONObject json = new JSONObject();
		json.put("zhongqiujie", "8-15");
		json.put("newyear", "1-1");
		mockMvc.perform(
				MockMvcRequestBuilders.get(PATH +"complete")
				.param("userId", CONSTANT.get("userId1"))
				.param("taskId", CONSTANT.get("taskId"))
				.param("jsonData", json.toJSONString()))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print()).andReturn();
	}
}
