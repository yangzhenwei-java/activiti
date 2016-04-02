package com.beebank.model;

import java.util.Date;



public class HistoricVariableInstanceDTO {

	
	private String id;
	
	private String variableName;
	
	private String variableTypeName;
	
	private Object value;
	
	private String processInstanceId;
	    
	private String taskId;
	  
	private Date createTime;
	
	private Date lastUpdatedTime;

	private Date time;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getVariableTypeName() {
		return variableTypeName;
	}

	public void setVariableTypeName(String variableTypeName) {
		this.variableTypeName = variableTypeName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	  
	
}
