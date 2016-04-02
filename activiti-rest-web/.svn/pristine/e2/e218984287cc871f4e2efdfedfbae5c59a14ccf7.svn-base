package com.beebank.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers; 


public class DeploymentControllerTest extends BaseTest {
	
	static String PATH ="/deployment/";
	
	public  static Map<String,String> CONSTANT = new HashMap<String,String>()
	{
		{
			put("processDefinitionId", "userAndGroupInUserTask:1:4");  
	        put("userId", "zhansan"); 
	        put("tenantId", "3");
	        put("resourceName", "userAndGroupInUserTask.bpmn");
		}
	};
	
	@Test
	public void deployTest() throws Exception{
		File file = new File("/Users/yzw/Desktop/workspace/activiti-rest-web/src/test/resources/userAndGroupInUserTask.bpmn");
		
		System.err.println(file.getName());
//		byte[] buffer = new byte[1024*1024];
//		IOUtils.read(new FileInputStream(file), buffer );
		
		mockMvc.perform(MockMvcRequestBuilders.fileUpload(PATH+"deploy/"+CONSTANT.get("tenantId")).
				file(new MockMultipartFile ("file","userAndGroupInUserTask.bpmn",null,new FileInputStream(file))).contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
				)
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print()).andReturn();
		
	}
	
	@Test
	public void processList() throws Exception{
		String ss = mockMvc.perform(MockMvcRequestBuilders.get(PATH +"process-list/"+CONSTANT.get("tenantId")))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
		System.err.println(ss);
	}
	
	@Test 
	public void suspendProcessDefinitionTest() throws UnsupportedEncodingException, Exception{
		String ss = mockMvc.perform(
				MockMvcRequestBuilders.get(PATH +"suspend-processDefinition")
				.param("processDefinitionKey", "userAndGroupInUserTask")
				.param("tenantId", CONSTANT.get("tenantId")))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
		System.err.println(ss);
	}
	
	@Test 
	public void activateProcessDefinitionTest() throws UnsupportedEncodingException, Exception{
		String ss = mockMvc.perform(
				MockMvcRequestBuilders.get(PATH +"activate-processDefinition")
				.param("processDefinitionKey", "userAndGroupInUserTask")
				.param("tenantId", CONSTANT.get("tenantId")))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
		System.err.println(ss);
	}
	
	
	@Test 
	public void deleteDeploy() throws UnsupportedEncodingException, Exception{
		String ss = mockMvc.perform(
				MockMvcRequestBuilders.get(PATH +"delete-deployment")
				.param("deploymentId", "1"))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
		System.err.println(ss);
	}
	
	@Test
	public void readResourceTest() throws Exception{
		
		OutputStream out = new FileOutputStream(new File("/Users/yzw/Desktop/workspace/activiti-rest-web/src/test/resources/test1.xml"));
		 mockMvc.perform(MockMvcRequestBuilders.get(PATH+"read-resource")
				 .accept(MediaType.APPLICATION_XML_VALUE)
				.param("pdid", CONSTANT.get("processDefinitionId"))
				.param("resourceName", CONSTANT.get("resourceName")))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print(out))
				.andReturn();
		 out.flush();
		out.close();

		
		
	}
	
	
	
	/** 
     * 获得指定文件的byte数组 
     */  
    private byte[] getBytes(String filePath){  
        byte[] buffer = null;  
        try {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }
    
    /** 
     * 根据byte数组，生成文件 
     */  
    public static void getFile(byte[] bfile, String filePath,String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath);  
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath+"\\"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
    }  
}
