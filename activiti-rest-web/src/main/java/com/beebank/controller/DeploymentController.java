package com.beebank.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.beebank.constants.ReturnCode;
import com.beebank.controller.base.AbstractController;
import com.beebank.model.ProcessDefinitionDTO;
import com.beebank.model.RspDTO;
import com.beebank.utils.convert.ActivitiDtoConvertUtil;

/**
 * @author yzw
 * 部署控制类
 */
@Controller
@RequestMapping(value="/deployment")
public class DeploymentController extends AbstractController{
	
	
    /**
     * 流程定义列表
     */
    @RequestMapping(value = "/process-list/{tenantId}")
    public @ResponseBody  List<ProcessDefinitionDTO>  processList(@PathVariable("tenantId") String tenantId) {

    	List<ProcessDefinitionDTO> list = new ArrayList<ProcessDefinitionDTO>();
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(tenantId).list();
        for(ProcessDefinition def :processDefinitionList){
        	ProcessDefinitionDTO vo = ActivitiDtoConvertUtil.convert(def);
        	
        	list.add(vo);
        }
        return list;
        
    }
	
	
	  /**
     * 部署流程资源
     */
    @RequestMapping(value = "/deploy/{tenantId}")
    public @ResponseBody RspDTO deploy(
    		@PathVariable("tenantId") String tenantId,
    		@RequestParam(value = "file", required = true) MultipartFile file) {

        // 获取上传的文件名
        String fileName = file.getOriginalFilename();

        try {
            // 得到输入流（字节流）对象
            InputStream fileInputStream = file.getInputStream();

            // 文件的扩展名
            String extension = FilenameUtils.getExtension(fileName);

            // zip或者bar类型的文件用ZipInputStream方式部署
            DeploymentBuilder deployment = repositoryService.createDeployment().tenantId(tenantId);
            if (extension.equals("zip") || extension.equals("bar")) {
                ZipInputStream zip = new ZipInputStream(fileInputStream);
                deployment.addZipInputStream(zip);
            } else {
                // 其他类型的文件直接部署
                deployment.addInputStream(fileName, fileInputStream);
            }
            deployment.deploy();
            return new RspDTO(ReturnCode.SUCCESS);
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("部署失败",e);
            return new RspDTO(ReturnCode.FAIL,e.getMessage());
        }

       
    }

    
    /**
     * 读取流程资源
     *
     * @param processDefinitionId 流程定义ID
     * @param resourceName        资源名称
     */
    @RequestMapping(value = "/read-resource")
    public void readResource(@RequestParam("pdid") String processDefinitionId, @RequestParam("resourceName") String resourceName, HttpServletResponse response)
            throws Exception {
        ProcessDefinitionQuery pdq = repositoryService.createProcessDefinitionQuery();
        ProcessDefinition pd = pdq.processDefinitionId(processDefinitionId).singleResult();

        // 通过接口读取
        InputStream resourceAsStream = repositoryService.getResourceAsStream(pd.getDeploymentId(), resourceName);

        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }
    

    /**
     * 删除部署的流程，是否级联删除流程实例
     * @param deploymentId
     * @param cascade
     * @return
     */
    @RequestMapping(value = "/delete-deployment")
    public @ResponseBody RspDTO deleteProcessDefinition(@RequestParam("deploymentId") String deploymentId ,
    		@RequestParam(value = "cascade",required=false,defaultValue="false") boolean cascade) {
    	
    	try {
            repositoryService.deleteDeployment(deploymentId, cascade);
            return new RspDTO(ReturnCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除部署流程失败,部署ID-->"+deploymentId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}

    }
    

    /**
     * 激活流程定义
     * @param processDefinitionKey 
     * @param activateProcessInstances 是否级联激活流程实例
     * @param activationDate  将要激活的日期
     * @param tenantId 租户ID
     * @return
     */
    @RequestMapping(value = "/activate-processDefinition")
    public @ResponseBody RspDTO activateProcessDefinition(@RequestParam(value="processDefinitionKey") String processDefinitionKey,
    		@RequestParam(value="activateProcessInstances",defaultValue="false") boolean activateProcessInstances,
    		@RequestParam(value="activationDate",required=false) Date activationDate,
    		@RequestParam(value= "tenantId") String tenantId){
    	
    		try {
        		repositoryService.activateProcessDefinitionByKey(processDefinitionKey, activateProcessInstances, activationDate, tenantId);
        		return new RspDTO(ReturnCode.SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("激活流程定义失败,processDefinitionKey-->"+processDefinitionKey, e);
				return new RspDTO(ReturnCode.FAIL,e.getMessage());
			}

    }
    

    /**
     * 挂起流程定义
     * @param processDefinitionKey
     * @param suspendProcessInstances
     * @param suspensionDate
     * @param tenantId
     * @return
     */
    @RequestMapping(value = "/suspend-processDefinition")
    public @ResponseBody RspDTO suspendProcessDefinition(@RequestParam(value="processDefinitionKey") String processDefinitionKey,
    		@RequestParam(value="suspendProcessInstances",defaultValue="false") boolean suspendProcessInstances,
    		@RequestParam(value="suspensionDate",required=false) Date suspensionDate,
    		@RequestParam(value= "tenantId") String tenantId){
    	
    		try {
        		repositoryService.suspendProcessDefinitionByKey(processDefinitionKey, suspendProcessInstances, suspensionDate, tenantId);
        		return new RspDTO(ReturnCode.SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("挂起流程定义失败,processDefinitionKey-->"+processDefinitionKey, e);
				return new RspDTO(ReturnCode.FAIL,e.getMessage());
			}

    }
}
