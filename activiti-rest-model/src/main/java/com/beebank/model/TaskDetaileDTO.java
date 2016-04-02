package com.beebank.model;

import java.util.List;



public class TaskDetaileDTO {
	
	// 表单数据
	private FormDataDTO taskFormData;
	// 是否是外置表单
	private boolean hasFormKey;
	// 任务信息
	private TaskDTO task;
	// 是否是手动创建的任务
	private boolean  manualTask;
	// 参与人列表
	private List<IdentityLinkDTO> identityLinksForTask;
	// 子任务
	private List<HistoricTaskInstanceDTO>  subTasks;
	// 上级任务
	private HistoricTaskInstanceDTO parentTask;
	// 附件
	private List<AttachmentDTO>  attachments;
	// 外置表单内容
	private String html;
	
	
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public Object getTaskFormData() {
		return taskFormData;
	}
	public void setTaskFormData(FormDataDTO taskFormData) {
		this.taskFormData = taskFormData;
	}
	public boolean isHasFormKey() {
		return hasFormKey;
	}
	public void setHasFormKey(boolean hasFormKey) {
		this.hasFormKey = hasFormKey;
	}

	public TaskDTO getTask() {
		return task;
	}
	public void setTask(TaskDTO task) {
		this.task = task;
	}
	public boolean isManualTask() {
		return manualTask;
	}
	public void setManualTask(boolean manualTask) {
		this.manualTask = manualTask;
	}
	public List<IdentityLinkDTO> getIdentityLinksForTask() {
		return identityLinksForTask;
	}
	public void setIdentityLinksForTask(List<IdentityLinkDTO> identityLinksForTask) {
		this.identityLinksForTask = identityLinksForTask;
	}
	public List<HistoricTaskInstanceDTO> getSubTasks() {
		return subTasks;
	}
	public void setSubTasks(List<HistoricTaskInstanceDTO> subTasks) {
		this.subTasks = subTasks;
	}
	public HistoricTaskInstanceDTO getParentTask() {
		return parentTask;
	}
	public void setParentTask(HistoricTaskInstanceDTO parentTask) {
		this.parentTask = parentTask;
	}
	public List<AttachmentDTO> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<AttachmentDTO> attachments) {
		this.attachments = attachments;
	}

	

	
}
