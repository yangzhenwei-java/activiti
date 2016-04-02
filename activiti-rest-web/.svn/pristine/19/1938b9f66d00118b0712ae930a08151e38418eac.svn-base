package com.beebank.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Event;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beebank.constants.ReturnCode;
import com.beebank.controller.base.AbstractController;
import com.beebank.model.RspDTO;

@Controller
@RequestMapping(value="/comment")
public class CommentController extends AbstractController{
	
	 /**
     * 保存意见
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public RspDTO addComment(@RequestParam("taskId") String taskId, 
    						  @RequestParam(value = "processInstanceId", required = false) String processInstanceId,
                              @RequestParam("message") String message,
                              @RequestParam("userId") String userId) {
    	try {
            identityService.setAuthenticatedUserId(userId);
            taskService.addComment(taskId, processInstanceId, message);
            return new RspDTO();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存意见异常!taskId-->"+taskId+",userId-->"+userId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}

    }

    /**
     * 读取意见
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "processInstanceId", required = false) String processInstanceId,
                                    @RequestParam(value = "taskId", required = false) String taskId) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Map<String, Object> result = new HashMap<String, Object>();

        Map<String, Object> commentAndEventsMap = new HashMap<String, Object>();

    /*
     * 根据不同情况使用不同方式查询
     */
        if (StringUtils.isNotBlank(processInstanceId)) {
            List<Comment> processInstanceComments = taskService.getProcessInstanceComments(processInstanceId);
            for (Comment comment : processInstanceComments) {
                String commentId = (String) PropertyUtils.getProperty(comment, "id");
                commentAndEventsMap.put(commentId, comment);
            }

            // 提取任务任务名称
            List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();
            Map<String, String> taskNames = new HashMap<String, String>();
            for (HistoricTaskInstance historicTaskInstance : list) {
                taskNames.put(historicTaskInstance.getId(), historicTaskInstance.getName());
            }
            result.put("taskNames", taskNames);

        }

    /*
     * 查询所有类型的事件
     */
        if (StringUtils.isNotBlank(taskId)) { // 根据任务ID查询
            List<Event> taskEvents = taskService.getTaskEvents(taskId);
            for (Event event : taskEvents) {
                String eventId = (String) PropertyUtils.getProperty(event, "id");
                commentAndEventsMap.put(eventId, event);
            }
        }

        result.put("events", commentAndEventsMap.values());

        return result;
    }


}
