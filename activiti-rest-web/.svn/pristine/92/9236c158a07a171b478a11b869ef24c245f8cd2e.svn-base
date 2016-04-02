package com.beebank.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.beebank.constants.ReturnCode;
import com.beebank.controller.base.AbstractController;
import com.beebank.model.ArchivedTaskDTO;
import com.beebank.model.AttachmentDTO;
import com.beebank.model.HistoricTaskInstanceDTO;
import com.beebank.model.IdentityLinkDTO;
import com.beebank.model.RspDTO;
import com.beebank.model.TaskDTO;
import com.beebank.model.TaskDetaileDTO;
import com.beebank.utils.convert.ActivitiDtoConvertUtil;


/**
 * @author yzw
 * 任务处理类
 */
@Controller
@RequestMapping(value="/task")
public class TaskController extends AbstractController{
	
	

    /**
     * 读取启动流程的表单字段
     */
    @RequestMapping(value = "/list")
    public @ResponseBody List<TaskDTO> todoTasks(@RequestParam("userId") String userId,
    		@RequestParam("tenantId") String tenantId,
    		@RequestParam(value = "taskName", required = false) String taskName,
    		@RequestParam(value="page",required=false,defaultValue="0") int page,
    		@RequestParam(value="pageSize",required=false,defaultValue=Integer.MAX_VALUE+"") int pageSize) throws Exception {


    	TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userId).taskTenantId(tenantId);
    	
    	if(StringUtils.isNotBlank(taskName)){
    		taskQuery.taskNameLikeIgnoreCase(taskName);
    	}
    	List<TaskDTO> listDto = new ArrayList<TaskDTO>();
    	List<Task> listPage = taskQuery.listPage(Integer.valueOf(page), Integer.valueOf(pageSize));
    	for(Task task:listPage){
    		listDto.add(ActivitiDtoConvertUtil.convert(task));
    	}
    	return listDto;

    }

    /**
     * 签收任务
     */
    @RequestMapping(value = "/claim/{id}/{userId}")
    public @ResponseBody RspDTO claim(@PathVariable("id") String taskId, @PathVariable("userId") String userId) {
    	try {
            taskService.claim(taskId, userId);
            return new RspDTO(ReturnCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("任务签收异常!taskId-->"+taskId+",userId-->"+userId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}


    }

    /**
     * 反签收任务
     */
    @RequestMapping(value = "unclaim/{id}/{userId}")
    public @ResponseBody RspDTO unclaim(@PathVariable("id") String taskId,@PathVariable("userId") String userId) {
    	try {
            // 反签收条件过滤
            List<IdentityLink> links = taskService.getIdentityLinksForTask(taskId);
            for (IdentityLink identityLink : links) {
                // 如果一个任务有相关的候选人、组就可以反签收
                if (StringUtils.equals(IdentityLinkType.CANDIDATE, identityLink.getType())) {
                    taskService.claim(taskId, null);
                    return 	new RspDTO(ReturnCode.SUCCESS);
                }
            }
            return 	new RspDTO(ReturnCode.FAIL, "该任务不允许反签收！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("反签收任务!taskId-->"+taskId+",userId-->"+userId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}

    }

    /**
     * 读取用户任务的表单字段
     */
    @RequestMapping(value = "/getform/{taskId}")
    public @ResponseBody TaskDetaileDTO readTaskForm(@PathVariable("taskId") String taskId) throws Exception {

    	TaskDetaileDTO vo  = new TaskDetaileDTO();

        TaskFormData taskFormData = formService.getTaskFormData(taskId);
        Task task = null;
        
        if(taskFormData!=null){
        	if(taskFormData.getFormKey() != null){    // 外置表单
        		Object renderedTaskForm = formService.getRenderedTaskForm(taskId);
        		vo.setHasFormKey(true);
        		vo.setHtml(String.valueOf(renderedTaskForm));;
        		task = taskService.createTaskQuery().taskId(taskId).singleResult();
        	}else{   // 动态表单
        		vo.setHasFormKey(false);
        		vo.setTaskFormData(ActivitiDtoConvertUtil.convert(taskFormData));
        		task = taskFormData.getTask();
        	}
        }else{  // 手动创建的任务（包括子任务）
        	task = taskService.createTaskQuery().taskId(taskId).singleResult();
        }
        vo.setTask(ActivitiDtoConvertUtil.convert(task));
        // 读取任务参与人列表
        List<IdentityLink> identityLinksForTask = taskService.getIdentityLinksForTask(taskId);
        List<IdentityLinkDTO> identityLinkDTOList = new ArrayList<IdentityLinkDTO>();
        for(IdentityLink identityLink:identityLinksForTask){
        	identityLinkDTOList.add(ActivitiDtoConvertUtil.convert(identityLink));
        }
        vo.setIdentityLinksForTask(identityLinkDTOList);
        // 读取子任务
        List<HistoricTaskInstance> subTasks = historyService.createHistoricTaskInstanceQuery().taskParentTaskId(taskId).list();
        
        List<HistoricTaskInstanceDTO> subTasksDto = new ArrayList<HistoricTaskInstanceDTO> ();
        for(HistoricTaskInstance historicTaskInstance: subTasks){
        	subTasksDto.add(ActivitiDtoConvertUtil.convert(historicTaskInstance));
        }
        vo.setSubTasks(subTasksDto);
        // 读取上级任务
        
        if (task != null && task.getParentTaskId() != null) {
            HistoricTaskInstance parentTask = historyService.createHistoricTaskInstanceQuery().taskId(task.getParentTaskId()).singleResult();
            vo.setParentTask(ActivitiDtoConvertUtil.convert(parentTask));
        }

        // 读取附件
        List<Attachment> attachments = null;
        if (task.getTaskDefinitionKey() != null) {
            attachments = taskService.getTaskAttachments(taskId);
        } else {
            attachments = taskService.getProcessInstanceAttachments(task.getProcessInstanceId());
        }
        
        List<AttachmentDTO> list = new ArrayList<AttachmentDTO>();
        for(Attachment attachment: attachments){
        	list.add(ActivitiDtoConvertUtil.convert(attachment));
        }
        vo.setAttachments(list);
        return vo;
    }

    /**
     * 查看已结束任务
     */
    @RequestMapping(value = "/archived{taskId}/{tenantId}")
    public @ResponseBody ArchivedTaskDTO viewHistoryTask(@PathVariable("taskId") String taskId,
    		@PathVariable("tenantId") String tenantId) throws Exception {
    	
    	ArchivedTaskDTO vo = new ArchivedTaskDTO();
        HistoricTaskInstance task = historyService.createHistoricTaskInstanceQuery().taskId(taskId).taskTenantId(tenantId).singleResult();
        if(task==null){
        	 vo.setRet(ReturnCode.FAIL);
        	 vo.setMsg("无此数据");
        	 return vo;
        }
        vo.setTask(ActivitiDtoConvertUtil.convert(task));
        if (task.getParentTaskId() != null) {
            HistoricTaskInstance parentTask = historyService.createHistoricTaskInstanceQuery().taskId(task.getParentTaskId()).singleResult();
            vo.setParentTask(ActivitiDtoConvertUtil.convert(parentTask));
        }

        // 读取子任务
        List<HistoricTaskInstance> subTasks = historyService.createHistoricTaskInstanceQuery().taskParentTaskId(taskId).list();
        
        List<HistoricTaskInstanceDTO> subTasksDto = new ArrayList<HistoricTaskInstanceDTO> ();
        for(HistoricTaskInstance historicTaskInstance: subTasks){
        	subTasksDto.add(ActivitiDtoConvertUtil.convert(historicTaskInstance));
        }
        vo.setSubTasks(subTasksDto);

        // 读取附件
        List<Attachment> attachments = null;
        if (task.getTaskDefinitionKey() != null) {
            attachments = taskService.getTaskAttachments(taskId);
        } else {
            attachments = taskService.getProcessInstanceAttachments(task.getProcessInstanceId());
        }
        List<AttachmentDTO> list = new ArrayList<AttachmentDTO>();
        for(Attachment attachment: attachments){
        	list.add(ActivitiDtoConvertUtil.convert(attachment));
        }
        vo.setAttachments(list);

        return vo;
    }

    /**
     * 读取启动流程的表单字段
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/complete")
    public @ResponseBody RspDTO completeTask(@RequestParam("taskId") String taskId,
    		@RequestParam("userId") String userId,
    		@RequestParam(value="jsonData",defaultValue="{}") String jsonData) throws Exception {

    	
    	Map<String,Object> variables = JSONObject.parseObject(jsonData, Map.class);

        // 设置当前操作人，对于调用活动可以获取到当前操作人
        identityService.setAuthenticatedUserId(userId);

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        // 如果任务的流程定义任务Key为空则认为是手动创建的任务
        if (StringUtils.isBlank(task.getTaskDefinitionKey())&&task.getDelegationState() != DelegationState.PENDING) {
            taskService.complete(taskId,variables);
            return new RspDTO();
        }

        
        // 权限检查-任务的办理人和当前人不一致不能完成任务
        if (!task.getAssignee().equals(userId)) {
            return new RspDTO(ReturnCode.FAIL,"没有权限，不能完成该任务！");
        }

        // 单独处理被委派的任务
        if (task.getDelegationState() == DelegationState.PENDING) {
            taskService.resolveTask(taskId,variables);
            return new RspDTO(ReturnCode.SUCCESS," 委派任务完成！");
        }

        TaskFormData taskFormData = formService.getTaskFormData(taskId);

        // 从请求中获取表单字段的值
        List<FormProperty> formProperties = taskFormData.getFormProperties();
        Map<String, String> formValues = new HashMap<String, String>();
        for (FormProperty formProperty : formProperties) {
            if (formProperty.isWritable()) {
            	Object obj = variables.get(formProperty.getId());
                String value = obj==null?null:obj.toString();
                formValues.put(formProperty.getId(), value);
            }
        }

        formService.submitTaskFormData(taskId, formValues);
        return new RspDTO();
    }

    /**
     * 更改任务属性
     *
     * @throws ParseException
     */
    @RequestMapping("/property/{taskId}/{tenantId}")
    @ResponseBody
    public RspDTO changeTaskProperty(@PathVariable("taskId") String taskId, 
    		@PathVariable("tenantId") String tenantId,
    		@RequestParam("propertyName") String propertyName, @RequestParam("value") String value)
            throws ParseException {
    	try {
    		Task task = taskService.createTaskQuery().taskId(taskId).taskTenantId(tenantId).singleResult();
    		if(task==null){
    			return new RspDTO(ReturnCode.FAIL,"无此数据");
    		}
    		// 更改到期日
    		if (StringUtils.equals(propertyName, "dueDate")) {
    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    			Date parse = sdf.parse(value);
    			task.setDueDate(parse);
    			taskService.saveTask(task);
    		} else if (StringUtils.equals(propertyName, "priority")) {
    			// 更改任务优先级
    			task.setPriority(Integer.parseInt(value));
    			taskService.saveTask(task);
    		} else if (StringUtils.equals(propertyName, "owner")) {
    			// 更改拥有人
    			task.setOwner(value);
    			taskService.saveTask(task);
    		} else if (StringUtils.equals(propertyName, "assignee")) {
    			// 更改办理人
    			task.setAssignee(value);
    			taskService.saveTask(task);
    		} else {
    			return new RspDTO(ReturnCode.FAIL,"不支持[" + propertyName + "]属性！");
    		}
    		return new RspDTO();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更改任务属性!taskId-->"+taskId+",tenantId-->"+tenantId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}
    }

    /**
     * 添加参与人
     */
    @RequestMapping("/participant/add/{taskId}/{userId}")
    @ResponseBody
    public RspDTO addParticipants(@PathVariable("taskId") String taskId, 
    		@PathVariable("userId") String userId,
    		@RequestParam("userId[]") String[] userIds, 
    		@RequestParam("type[]") String[] types,
            HttpServletRequest request) {
    	try {
			
    		// 设置当前操作人，对于调用活动可以获取到当前操作人
    		identityService.setAuthenticatedUserId(userId);
    		
    		for (int i = 0; i < userIds.length; i++) {
    			taskService.addUserIdentityLink(taskId, userIds[i], types[i]);
    		}
    		return new RspDTO();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更改任务属性!taskId-->"+taskId+",userId-->"+userId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}
    }

    /**
     * 删除参与人
     */
    @RequestMapping("/participant/delete/{taskId}/{userId}")
    @ResponseBody
    public RspDTO deleteParticipant(@PathVariable("taskId") String taskId, 
    		@PathVariable("userId") String userId,
    		@RequestParam(value = "participantId", required = false) String participantId,
            @RequestParam(value = "groupId", required = false) String groupId,
            @RequestParam("type") String type) {
    	try {
            // 设置当前操作人，对于调用活动可以获取到当前操作人
            identityService.setAuthenticatedUserId(userId);
            //  区分用户、组，使用不同的处理方式
            if (StringUtils.isNotBlank(groupId)) {
                taskService.deleteCandidateGroup(taskId, groupId);
            } else {
                taskService.deleteUserIdentityLink(taskId, participantId, type);
            }
            return new RspDTO();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除参与人!taskId-->"+taskId+",userId-->"+userId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}

    }

    /**
     * 添加候选人
     */
    @RequestMapping("/candidate/add/{taskId}/{userId}")
    @ResponseBody
    public RspDTO addCandidates(@PathVariable("taskId") String taskId,
    		@PathVariable("userId") String userId,
    		@RequestParam("userOrGroupIds[]") String[] userOrGroupIds,
    		@RequestParam("type[]") String[] types) {
    	
    	try {
			
    		// 设置当前操作人，对于调用活动可以获取到当前操作人
    		identityService.setAuthenticatedUserId(userId);
    		
    		for (int i = 0; i < userOrGroupIds.length; i++) {
    			String type = types[i];
    			if (StringUtils.equals("user", type)) {
    				taskService.addCandidateUser(taskId, userOrGroupIds[i]);
    			} else if (StringUtils.equals("group", type)) {
    				taskService.addCandidateGroup(taskId, userOrGroupIds[i]);
    			}
    		}
    		return new RspDTO() ;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加候选人!taskId-->"+taskId+",userId-->"+userId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());		
		}
    }

    /**
     * 添加子任务
     */
    @RequestMapping("/subtask/add/{taskId}/{userId}")
    @ResponseBody
    public RspDTO addSubTask(@PathVariable("taskId") String parentTaskId, 
    			@PathVariable("userId") String userId,
    			@RequestParam("taskName") String taskName,
                @RequestParam(value = "description", required = false) String description) {
    	try {
			
    		Task newTask = taskService.newTask();
    		newTask.setParentTaskId(parentTaskId);
    		newTask.setOwner(userId);
    		newTask.setAssignee(userId);
    		newTask.setName(taskName);
    		newTask.setDescription(description);
    		
    		taskService.saveTask(newTask);
    		return new RspDTO();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加子任务失败!parentTaskId-->"+parentTaskId+",userId-->"+userId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}
    }

    /**
     * 删除子任务
     */
    @RequestMapping("/delete/{taskId}/{userId}")
    @ResponseBody
    public RspDTO deleteSubTask(@PathVariable("taskId") String taskId,
    		@PathVariable("userId") String userId) {
    	try {
            taskService.deleteTask(taskId, "deleteByUser" + userId);
            return new RspDTO();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除子任务!taskId-->"+taskId+",userId-->"+userId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}

    }

    /**
     * 新任务
     */
    @RequestMapping("/new/{userId}")
    @ResponseBody
    public RspDTO newTask(@PathVariable("userId") String userId,
    		@RequestParam("taskName") String taskName, 
    		@RequestParam(value = "description", required = false) String description,
                          @RequestParam(value = "priority", required = false) int priority, 
                          @RequestParam(value = "dueDate", required = false) String dueDate) {
    	try {
            Task newTask = taskService.newTask();
            newTask.setOwner(userId);
            newTask.setAssignee(userId);
            newTask.setName(taskName);
            newTask.setDescription(description);
            if (StringUtils.isNotBlank(dueDate)) {
                newTask.setDueDate(java.sql.Date.valueOf(dueDate));

            }
            newTask.setPriority(priority);

            taskService.saveTask(newTask);
            return new RspDTO();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("创建任务失败!taskName-->"+taskName+",userId-->"+userId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}

        
    }

    /**
     * 任务委派
     *
     * @param taskId
     * @param delegateUserId
     */
    @RequestMapping("/delegate/{taskId}/{userId}")
    @ResponseBody
    public RspDTO delegate(@PathVariable("taskId") String taskId,
    		@PathVariable("userId") String userId,
    		@RequestParam("delegateUserId") String delegateUserId) {
    	try {
    		// 设置当前操作人，对于调用活动可以获取到当前操作人
    		identityService.setAuthenticatedUserId(userId);
    		 taskService.delegateTask(taskId, delegateUserId);
    		 return new RspDTO();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("任务委派失败!taskId-->"+taskId+",userId-->"+userId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}
    }


}
