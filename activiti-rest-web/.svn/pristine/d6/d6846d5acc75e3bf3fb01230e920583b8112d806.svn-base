package com.beebank.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.task.Attachment;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.beebank.constants.ReturnCode;
import com.beebank.controller.base.AbstractController;
import com.beebank.model.RspDTO;

@Controller
@RequestMapping(value = "/attachment")
public class AttachmentController extends AbstractController {

	/**
	 * 文件类型的附件
	 */
	@RequestMapping(value = "new/file")
	public @ResponseBody  RspDTO newFile(@RequestParam("taskId") String taskId,
			@RequestParam("userId") String userId,
			@RequestParam(value = "processInstanceId", required = false) String processInstanceId,
			@RequestParam("attachmentName") String attachmentName,
			@RequestParam(value = "attachmentDescription", required = false) String attachmentDescription,
			@RequestParam("file") MultipartFile file) {
		try {
			String attachmentType = file.getContentType() + ";"
					+ FilenameUtils.getExtension(file.getOriginalFilename());
			identityService.setAuthenticatedUserId(userId);
			Attachment attachment = taskService.createAttachment(attachmentType, taskId, processInstanceId,
					attachmentName, attachmentDescription, file.getInputStream());
			taskService.saveAttachment(attachment);
			return new RspDTO();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存文件异常!taskId-->"+taskId+",userId-->"+userId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}
	}

	/**
	 * URL类型的附件
	 */
	@RequestMapping(value = "new/url")
	public @ResponseBody  RspDTO newUrl(@RequestParam("taskId") String taskId,
			@RequestParam("userId") String userId,
			@RequestParam(value = "processInstanceId", required = false) String processInstanceId,
			@RequestParam("attachmentName") String attachmentName,
			@RequestParam(value = "attachmentDescription", required = false) String attachmentDescription,
			@RequestParam("url") String url) {
		try{
			String attachmentType = "url";
			identityService.setAuthenticatedUserId(userId);
			 Attachment attachment = 
			taskService.createAttachment(attachmentType, taskId, processInstanceId, attachmentName, attachmentDescription,
					url);
			/*
			 * 如果要更新附件内容，先读取附件对象，然后设置属性（只能更新name和description），最后保存附件对象
			 */
			taskService.saveAttachment(attachment);
			return new RspDTO();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("保存URL类型的附件异常!taskId-->"+taskId+",userId-->"+userId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}
	}

	/**
	 * 删除附件
	 */
	@RequestMapping(value = "delete/{attachmentId}/{userId}")
	@ResponseBody
	public RspDTO delete(@PathVariable("attachmentId") String attachmentId,
			@PathVariable("userId") String userId) {
		try {
			//TODO 这里要做校验
			identityService.setAuthenticatedUserId(userId);
			taskService.deleteAttachment(attachmentId);
			return new RspDTO();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除附件异常!attachmentId-->"+attachmentId+",userId-->"+userId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}

	}

	/**
	 * 下载附件
	 *
	 * @throws IOException
	 */
	@RequestMapping(value = "download/{attachmentId}")
	public void downloadFile(@PathVariable("attachmentId") String attachmentId, HttpServletResponse response)
			throws IOException {
		Attachment attachment = taskService.getAttachment(attachmentId);
		InputStream attachmentContent = taskService.getAttachmentContent(attachmentId);
		String contentType = StringUtils.substringBefore(attachment.getType(), ";");
		response.addHeader("Content-Type", contentType + ";charset=UTF-8");
		String extensionFileName = StringUtils.substringAfter(attachment.getType(), ";");
		String fileName = attachment.getName() + "." + extensionFileName;
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		IOUtils.copy(new BufferedInputStream(attachmentContent), response.getOutputStream());
	}

}
