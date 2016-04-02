package com.beebank.model;

import java.util.List;


public class HistoricProcessInstanceDetailDTO {
	
	
	 private  List<HistoricActivityInstanceDTO> historicActivityInstances;
	 
	 private List<HistoricVariableInstanceDTO> HistoricVariableInstances;
	 
	 private List<HistoricDetailDTO> formProperties;

	 private  ProcessDefinitionDTO processDefinition;
	 
	 private HistoricProcessInstanceDTO historicProcessInstance;

	public List<HistoricActivityInstanceDTO> getHistoricActivityInstances() {
		return historicActivityInstances;
	}

	public void setHistoricActivityInstances(List<HistoricActivityInstanceDTO> historicActivityInstances) {
		this.historicActivityInstances = historicActivityInstances;
	}

	public List<HistoricVariableInstanceDTO> getHistoricVariableInstances() {
		return HistoricVariableInstances;
	}

	public void setHistoricVariableInstances(List<HistoricVariableInstanceDTO> historicVariableInstances) {
		HistoricVariableInstances = historicVariableInstances;
	}

	public List<HistoricDetailDTO> getFormProperties() {
		return formProperties;
	}

	public void setFormProperties(List<HistoricDetailDTO> formProperties) {
		this.formProperties = formProperties;
	}

	public ProcessDefinitionDTO getProcessDefinition() {
		return processDefinition;
	}

	public void setProcessDefinition(ProcessDefinitionDTO processDefinition) {
		this.processDefinition = processDefinition;
	}

	public HistoricProcessInstanceDTO getHistoricProcessInstance() {
		return historicProcessInstance;
	}

	public void setHistoricProcessInstance(HistoricProcessInstanceDTO historicProcessInstance) {
		this.historicProcessInstance = historicProcessInstance;
	}
	 
	
	 
}
