package com.beebank.model;

import java.util.List;



/**
 * @author yzw
 * 归档任务VO
 */
public class ArchivedTaskDTO extends RspDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 
	private HistoricTaskInstanceDTO task;
	
	// 父任务
	private HistoricTaskInstanceDTO parentTask;
	//子任务
	private  List<HistoricTaskInstanceDTO> subTasks;
	// 附件
	private List<AttachmentDTO>  attachments;
	public HistoricTaskInstanceDTO getTask() {
		return task;
	}
	public void setTask(HistoricTaskInstanceDTO task) {
		this.task = task;
	}
	public HistoricTaskInstanceDTO getParentTask() {
		return parentTask;
	}
	public void setParentTask(HistoricTaskInstanceDTO parentTask) {
		this.parentTask = parentTask;
	}
	public List<HistoricTaskInstanceDTO> getSubTasks() {
		return subTasks;
	}
	public void setSubTasks(List<HistoricTaskInstanceDTO> subTasks) {
		this.subTasks = subTasks;
	}
	public List<AttachmentDTO> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<AttachmentDTO> attachments) {
		this.attachments = attachments;
	}

	
	
	
}
