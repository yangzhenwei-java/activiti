package com.beebank.utils.convert;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.form.FormData;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;

import com.beebank.model.AttachmentDTO;
import com.beebank.model.FormDataDTO;
import com.beebank.model.FormPropertyDTO;
import com.beebank.model.GroupDTO;
import com.beebank.model.HistoricTaskInstanceDTO;
import com.beebank.model.IdentityLinkDTO;
import com.beebank.model.ProcessDefinitionDTO;
import com.beebank.model.TaskDTO;
import com.beebank.model.UserDTO;

public class ActivitiDtoConvertUtil {
	
	public static ProcessDefinitionDTO  convert(ProcessDefinition def){
		
    	ProcessDefinitionDTO vo = new ProcessDefinitionDTO();
    	vo.setCategory(def.getCategory());
    	vo.setDeploymentId(def.getDeploymentId());
    	vo.setDescription(def.getDescription());
    	vo.setDiagramResourceName(def.getDiagramResourceName());
    	vo.setGraphicalNotation(def.hasGraphicalNotation());
    	vo.setId(def.getId());
    	vo.setKey(def.getKey());
    	vo.setName(def.getName());
    	vo.setResourceName(def.getResourceName());
    	vo.setStartFormKey(def.hasStartFormKey());
    	vo.setSuspended(def.isSuspended());
    	vo.setTenantId(def.getTenantId());
		return vo;
	}
	
	
	public static FormPropertyDTO convert(FormProperty form){
		
		FormPropertyDTO dto = new FormPropertyDTO();
	
		dto.setId(form.getId());
		dto.setName(form.getName());
		dto.setReadable(form.isReadable());
		dto.setRequired(form.isRequired());
		dto.setFormTypeName(form.getType()!=null?form.getType().getName():null);
		dto.setValue(form.getValue());
		dto.setWritable(form.isWritable());
		return dto;
	}
	
	

	public static FormDataDTO  convert(FormData formData){
		
		List<FormPropertyDTO> list = new ArrayList<FormPropertyDTO>();
		FormDataDTO vo = new FormDataDTO();
		vo.setDeploymentId(formData.getDeploymentId());
		vo.setFormKey(formData.getFormKey());
		for(FormProperty property :formData.getFormProperties()){
			list.add(convert(property));
		}
		vo.setFormProperties(list);
		return vo;
	}
	
	public static GroupDTO convert(Group group){
		GroupDTO dto = new GroupDTO();
		dto.setId(group.getId());
		dto.setName(group.getName());
		dto.setType(group.getType());
		return dto;
	}
	
	public static UserDTO convert(User user){
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		return dto;
	}
	
	
	public static TaskDTO convert(Task task){
		TaskDTO dto =  new TaskDTO();
		
		dto.setAssignee(task.getAssignee());
		dto.setCategory(task.getCategory());
		dto.setCreateTime(task.getCreateTime());
		dto.setDescription(task.getDescription());
		dto.setDueDate(task.getDueDate());
		dto.setExecutionId(task.getExecutionId());
		dto.setFormKey(task.getFormKey());
		dto.setId(task.getId());
		dto.setName(task.getName());
		dto.setOwner(task.getOwner());
		dto.setParentTaskId(task.getParentTaskId());
		dto.setPriority(task.getPriority());
		dto.setProcessDefinitionId(task.getProcessDefinitionId());
		dto.setProcessInstanceId(task.getProcessInstanceId());
		dto.setProcessVariables(task.getProcessVariables());
		dto.setTaskDefinitionKey(task.getTaskDefinitionKey());
		dto.setTaskLocalVariables(task.getTaskLocalVariables());
		dto.setTenantId(task.getTenantId());
		return dto;
	}
	
	
	public static IdentityLinkDTO convert(IdentityLink identityLink){
		
		IdentityLinkDTO dto = new IdentityLinkDTO();
		dto.setGroupId(identityLink.getGroupId());
		dto.setProcessDefinitionId(identityLink.getProcessDefinitionId());
		dto.setProcessInstanceId(identityLink.getProcessInstanceId());
		dto.setTaskId(identityLink.getTaskId());
		dto.setType(identityLink.getType());
		dto.setUserId(identityLink.getUserId());
		return dto;
	}

	
	public static AttachmentDTO  convert(Attachment attachment){
		AttachmentDTO dto = new AttachmentDTO();
		dto.setDescription(attachment.getDescription());
		dto.setId(attachment.getId());
		dto.setName(attachment.getName());
		
		dto.setProcessInstanceId(attachment.getProcessInstanceId());
		dto.setTaskId(attachment.getTaskId());
		dto.setTime(attachment.getTime());
		dto.setType(attachment.getType());
		dto.setUrl(attachment.getUrl());
		dto.setUserId(attachment.getUserId());
		return dto;
		
	}


	public static HistoricTaskInstanceDTO convert(HistoricTaskInstance historicTaskInstance) {
		
		HistoricTaskInstanceDTO dto = new HistoricTaskInstanceDTO();
		
		dto.setAssignee(historicTaskInstance.getAssignee());
		dto.setClaimTime(historicTaskInstance.getClaimTime());
		dto.setDeleteReason(historicTaskInstance.getDeleteReason());
		dto.setDescription(historicTaskInstance.getDescription());
		dto.setDueDate(historicTaskInstance.getDueDate());
		dto.setDurationInMillis(historicTaskInstance.getDurationInMillis());
		dto.setEndTime(historicTaskInstance.getEndTime());
		dto.setExecutionId(historicTaskInstance.getExecutionId());
		dto.setFormKey(historicTaskInstance.getFormKey());
		dto.setId(historicTaskInstance.getId());
		dto.setName(historicTaskInstance.getName());
		dto.setOwner(historicTaskInstance.getOwner());
		dto.setParentTaskId(historicTaskInstance.getParentTaskId());
		dto.setPriority(historicTaskInstance.getPriority());
		dto.setProcessDefinitionId(historicTaskInstance.getProcessDefinitionId());
		dto.setProcessInstanceId(historicTaskInstance.getProcessInstanceId());
		dto.setStartTime(historicTaskInstance.getStartTime());
		dto.setTaskDefinitionKey(historicTaskInstance.getTaskDefinitionKey());
		dto.setWorkTimeInMillis(historicTaskInstance.getWorkTimeInMillis());
		return dto;
	}
}
