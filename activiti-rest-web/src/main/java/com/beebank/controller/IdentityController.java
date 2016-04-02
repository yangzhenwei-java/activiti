package com.beebank.controller;

import java.util.ArrayList;
import java.util.List;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.beebank.constants.ReturnCode;
import com.beebank.controller.base.AbstractController;
import com.beebank.model.GroupDTO;
import com.beebank.model.PageDTO;
import com.beebank.model.RspDTO;
import com.beebank.model.UserDTO;
import com.beebank.utils.convert.ActivitiDtoConvertUtil;



@Controller
@RequestMapping(value="/identity")
public class IdentityController extends AbstractController {
	
	  /**
     * 组列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/group/list")
    @ResponseBody
    public PageDTO<GroupDTO> groupList(
    		@RequestParam(value="page",defaultValue="0") int page,
    		@RequestParam(value="pageSize",defaultValue=Integer.MAX_VALUE+"") int pageSize) {

        PageDTO<GroupDTO> pageDTO = new PageDTO<GroupDTO>();

        GroupQuery groupQuery = identityService.createGroupQuery();
        List<Group> groupList = groupQuery.listPage(page,pageSize );
        List<GroupDTO>  groupDtoList = new ArrayList<GroupDTO>();
        for(Group group:groupList){
        	groupDtoList.add(ActivitiDtoConvertUtil.convert(group));
        }
        pageDTO.setResult(groupDtoList);
        pageDTO.setTotalCount(groupQuery.count());

        return pageDTO;
    }

    /**
     * 保存Group
     *
     * @return
     */
    @RequestMapping(value = "/group/save", method = RequestMethod.POST)
    public @ResponseBody RspDTO saveGroup(@RequestParam("groupId") String groupId,
                            @RequestParam("groupName") String groupName,
                            @RequestParam("type") String type) {
    	try {
            Group group = identityService.createGroupQuery().groupId(groupId).singleResult();
            if (group == null) {
                group = identityService.newGroup(groupId);
            }
            group.setName(groupName);
            group.setType(type);
            identityService.saveGroup(group);
            return new RspDTO();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存Group!groupId-->"+groupId+",groupName-->"+groupName, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}

    }

    /**
     * 删除Group
     */
    @RequestMapping(value = "/group/delete/{groupId}", method = RequestMethod.GET)
    public @ResponseBody RspDTO deleteGroup(@PathVariable("groupId") String groupId) {
    	
    	try {
            identityService.deleteGroup(groupId);
            return new RspDTO();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存Group!groupId-->"+groupId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}
    	


    }

    /**
     * 用户列表
     *
     * @param 
     * @return
     */
    @RequestMapping("/user/list")
    public @ResponseBody PageDTO<UserDTO> userList( @RequestParam(value="page",defaultValue="0") int page,
    		@RequestParam(value="pageSize",defaultValue=Integer.MAX_VALUE+"") int pageSize) {


        PageDTO<UserDTO> pageDto = new PageDTO<UserDTO>();
        
        UserQuery userQuery = identityService.createUserQuery();
        List<User> userList = userQuery.listPage(page, pageSize);
        List<UserDTO> userDtoList  = new ArrayList<UserDTO>();

        for(User user:userList){
        	UserDTO dto =new UserDTO();
        	dto.setId(user.getId());
        }
        
        pageDto.setResult(userDtoList);
        pageDto.setTotalCount(userQuery.count());

        return pageDto;

    }

    /**
     * 保存User
     *
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public @ResponseBody RspDTO saveUser(@RequestParam("userId") String userId,
                           @RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam(value = "password", required = false) String password,
                           @RequestParam(value = "email", required = false) String email) {
    	try {
            User user = identityService.createUserQuery().userId(userId).singleResult();
            if (user == null) {
                user = identityService.newUser(userId);
            }
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            if (StringUtils.isNotBlank(password)) {
                user.setPassword(password);
            }
            identityService.saveUser(user);
            return new RspDTO();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存User失败!firstName-->"+firstName, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}


    }

    /**
     * 删除User
     */
    @RequestMapping(value = "user/delete/{userId}")
    public @ResponseBody RspDTO deleteUser(@PathVariable("userId") String userId) {
    	try {
            identityService.deleteUser(userId);
            return new RspDTO();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除User失败!userId-->"+userId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}

        
    }

    /**
     * 为用户设置所属组
     * @param userId
     * @param groupIds
     * @return
     */
    @RequestMapping(value = "group/set", method = RequestMethod.POST)
    public @ResponseBody RspDTO groupForUser(@RequestParam("userId") String userId, @RequestParam("group") String[] groupIds) {
    	
    	try {
            List<Group> groupInDb = identityService.createGroupQuery().groupMember(userId).list();
            for (Group group : groupInDb) {
                identityService.deleteMembership(userId, group.getId());
            }
            for (String group : groupIds) {
                identityService.createMembership(userId, group);
            }
            return new RspDTO();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("为用户设置所属组!userId-->"+userId, e);
			return new RspDTO(ReturnCode.FAIL,e.getMessage());
		}

        
    }


}
