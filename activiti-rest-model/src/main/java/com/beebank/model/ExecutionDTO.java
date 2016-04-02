package com.beebank.model;

import java.util.List;

public class ExecutionDTO {
	
	
	private String  id;
	private boolean suspended;
	
	private boolean ended;
	
	private String activityId;
	 
	private String processInstanceId;

	private String parentId;

	private String tenantId;
	
	private List<TaskDTO> activeActivity;
	
	private ProcessDefinitionDTO processDefinition;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public boolean isEnded() {
		return ended;
	}

	public void setEnded(boolean ended) {
		this.ended = ended;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public List<TaskDTO> getActiveActivity() {
		return activeActivity;
	}

	public void setActiveActivity(List<TaskDTO> activeActivity) {
		this.activeActivity = activeActivity;
	}

	public ProcessDefinitionDTO getProcessDefinition() {
		return processDefinition;
	}

	public void setProcessDefinition(ProcessDefinitionDTO processDefinition) {
		this.processDefinition = processDefinition;
	}
	
	
	
	

}
