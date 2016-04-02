package com.beebank.dao.mapper;

import com.beebank.dao.entity.Tenant;
import com.beebank.dao.entity.TenantExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TenantMapper {
    int countByExample(TenantExample example);

    int deleteByExample(TenantExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tenant record);

    int insertSelective(Tenant record);

    List<Tenant> selectByExample(TenantExample example);

    Tenant selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tenant record, @Param("example") TenantExample example);

    int updateByExample(@Param("record") Tenant record, @Param("example") TenantExample example);

    int updateByPrimaryKeySelective(Tenant record);

    int updateByPrimaryKey(Tenant record);
}