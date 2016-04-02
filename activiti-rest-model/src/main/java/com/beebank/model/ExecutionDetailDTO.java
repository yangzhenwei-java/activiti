package com.beebank.model;

public class ExecutionDetailDTO extends HistoricProcessInstanceDetailDTO{
	
	private ExecutionDTO execution;
	
	private ProcessInstanceDTO parentProcessInstance;

	public ExecutionDTO getExecution() {
		return execution;
	}

	public void setExecution(ExecutionDTO execution) {
		this.execution = execution;
	}

	public ProcessInstanceDTO getParentProcessInstance() {
		return parentProcessInstance;
	}

	public void setParentProcessInstance(ProcessInstanceDTO parentProcessInstance) {
		this.parentProcessInstance = parentProcessInstance;
	}
	
	

}
