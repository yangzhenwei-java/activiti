package com.beebank.model;

import java.util.List;


public class FormDataDTO {
	
	
	private String formKey;
	
	private String deploymentId;
	
	private List<FormPropertyDTO>  formProperties;

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public List<FormPropertyDTO> getFormProperties() {
		return formProperties;
	}

	public void setFormProperties(List<FormPropertyDTO> formProperties) {
		this.formProperties = formProperties;
	}


	




}
