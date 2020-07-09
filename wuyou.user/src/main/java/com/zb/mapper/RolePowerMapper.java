package com.zb.mapper;

import com.zb.pojo.RolePower;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RolePowerMapper {

	public RolePower getRolePowerById(@Param(value = "id") Long id)throws Exception;

	public List<RolePower>	getRolePowerListByMap(Map<String, Object> param)throws Exception;

	public Integer getRolePowerCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertRolePower(RolePower rolePower)throws Exception;

	public Integer updateRolePower(RolePower rolePower)throws Exception;

	public Integer deleteRolePowerById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeleteRolePower(Map<String, List<String>> params);

}
