package com.beebank.model;


public class StartFormDTO {
	
	private String html;
	
	private FormDataDTO startFormDataVo;
	
	private ProcessDefinitionDTO processDefinitionVo;
	
	private boolean hasStartFormKey;
	





	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public FormDataDTO getStartFormDataVo() {
		return startFormDataVo;
	}

	public void setStartFormDataVo(FormDataDTO startFormDataVo) {
		this.startFormDataVo = startFormDataVo;
	}

	public ProcessDefinitionDTO getProcessDefinitionVo() {
		return processDefinitionVo;
	}

	public void setProcessDefinitionVo(ProcessDefinitionDTO processDefinitionVo) {
		this.processDefinitionVo = processDefinitionVo;
	}

	public boolean getHasStartFormKey() {
		return hasStartFormKey;
	}

	public void setHasStartFormKey(boolean hasStartFormKey) {
		this.hasStartFormKey = hasStartFormKey;
	}

	


}
