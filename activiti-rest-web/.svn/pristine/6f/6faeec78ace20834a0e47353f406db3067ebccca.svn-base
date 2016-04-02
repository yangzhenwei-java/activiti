package com.beebank.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beebank.constants.ReturnCode;
import com.beebank.controller.base.AbstractController;
import com.beebank.model.RspDTO;
import com.beebank.model.StartFormDTO;
import com.beebank.utils.convert.ActivitiDtoConvertUtil;

/**
 * @author yzw 流程定义控制类
 */
@Controller
@RequestMapping(value = "/definition")
public class ProcessDefinitionController extends AbstractController {

	/**
	 * 读取启动流程的表单字段
	 */
	@RequestMapping(value = "/getform/{processDefinitionId}")
	public @ResponseBody StartFormDTO readStartForm(@PathVariable("processDefinitionId") String processDefinitionId)
			throws Exception {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		boolean hasStartFormKey = processDefinition.hasStartFormKey();

		StartFormDTO formVo = new StartFormDTO();
		formVo.setProcessDefinitionVo(ActivitiDtoConvertUtil.convert(processDefinition));
		formVo.setHasStartFormKey(hasStartFormKey);
		// 判断是否有formkey属性
		if (hasStartFormKey) {
			Object renderedStartForm = formService.getRenderedStartForm(processDefinitionId);
			formVo.setHtml((String)renderedStartForm);
		} else { // 动态表单字段
			StartFormData startFormData = formService.getStartFormData(processDefinitionId);
			formVo.setStartFormDataVo(ActivitiDtoConvertUtil.convert(startFormData));
		}
		return formVo;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/start/{processDefinitionId}/{userId}")
	public @ResponseBody RspDTO startProcessInstance(@PathVariable("processDefinitionId") String pdid,
			@PathVariable("userId") String userId, HttpServletRequest request) {

		try {

			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
					.processDefinitionId(pdid).singleResult();
			boolean hasStartFormKey = processDefinition.hasStartFormKey();

			Map<String, String> formValues = new HashMap<String, String>();

			if (hasStartFormKey) { // formkey表单
				Map<String, String[]> parameterMap = request.getParameterMap();
				Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
				for (Entry<String, String[]> entry : entrySet) {
					String key = entry.getKey();
					formValues.put(key, entry.getValue()[0]);
				}
			} else { // 动态表单
				// 先读取表单字段在根据表单字段的ID读取请求参数值
				StartFormData formData = formService.getStartFormData(pdid);

				// 从请求中获取表单字段的值
				List<FormProperty> formProperties = formData.getFormProperties();
				for (FormProperty formProperty : formProperties) {
					String value = request.getParameter(formProperty.getId());
					formValues.put(formProperty.getId(), value);
				}
			}

			//TODO: 验证当前用户是否具有权限

			identityService.setAuthenticatedUserId(userId);
						// 提交表单字段并启动一个新的流程实例
			ProcessInstance processInstance = formService.submitStartFormData(pdid, formValues);
			logger.debug("start a processinstance: {}", processInstance);
			RspDTO rsp = new RspDTO();
			rsp.setRet(ReturnCode.SUCCESS);
			return rsp;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("开始流程实例失败,流程定义Id-->" + pdid, e);
			return new RspDTO(ReturnCode.FAIL, e.getMessage());
		}
	}
	
	

}
