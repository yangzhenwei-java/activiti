package com.beebank.controller;


import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;

import com.beebank.dao.entity.Tenant;
import com.beebank.dao.mapper.TenantMapper;

public class TenantMapperTest extends BaseTest{
	
	
	@Resource
	TenantMapper mapper;
	@Test
	public void addTenant(){
		
		try {
			Tenant record  = new Tenant() ;
			
			record.setAge(19);
			record.setName("zhansan");
			record.setId(1);
//			mapper.insert(record);
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			System.err.println(e.getClass());
			if(e instanceof DuplicateKeyException){
				System.err.println("捕获到了正确的异常");
			}
		}

	}
	

}
