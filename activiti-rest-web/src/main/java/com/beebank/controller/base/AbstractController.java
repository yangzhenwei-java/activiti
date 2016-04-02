package com.beebank.controller.base;

import javax.annotation.Resource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象基类  提供公共的方法
 * @author yzw
 *  
 */
public abstract class AbstractController {

	protected  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
    protected RepositoryService repositoryService;
	@Resource
    protected RuntimeService runtimeService;
	@Resource
    protected TaskService taskService;
	@Resource
    protected HistoryService historyService;
	@Resource
    protected IdentityService identityService;
	@Resource
    protected ManagementService managementService;
	@Resource
    protected FormService formService;
}
