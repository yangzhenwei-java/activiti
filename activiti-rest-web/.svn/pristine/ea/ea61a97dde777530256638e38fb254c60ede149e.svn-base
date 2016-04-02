package com.beebank.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

//@RunWith(Parameterized.class)
public class ProcessDefinitionControllerTest extends BaseTest {
	
	static String PATH ="/definition/";
	
	
	public  static Map<String,String> CONSTANT = new HashMap<String,String>()
	{
		{
			put("processDefinitionId", "userAndGroupInUserTask:1:4");  
	        put("userId", "zhansan"); 
		}
	};

	
	
//
//	@Parameters
//	public static Collection<Object[]> t(){
//		return Arrays.asList(new Object[][]{
//			{"userAndGroupInUserTask:2:2504","zhangsan"}
//		});
//	}
	

	
//	public ProcessDefinitionControllerTest(String processDefinitionId,String userId ){
//		this.processDefinitionId=processDefinitionId;
//		this.userId = userId;
//	}
	@Test
	public void readStartFormTest() throws UnsupportedEncodingException, Exception{
		String ss = mockMvc.perform(
				MockMvcRequestBuilders.get(PATH +"getform/"+CONSTANT.get("processDefinitionId")))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
		System.err.println(ss);
	}
	
	
	@Test
	public void startProcessInstance() throws UnsupportedEncodingException, Exception{
		String ss = mockMvc.perform(
				
				MockMvcRequestBuilders.get(PATH +"start/"+CONSTANT.get("processDefinitionId")
				+"/"+CONSTANT.get("userId")))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
		System.err.println(ss);
	}
	
	
	

}
