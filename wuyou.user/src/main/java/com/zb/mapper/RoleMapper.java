package com.zb.mapper;

import com.zb.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper {

	 Role getRoleById(@Param(value = "id") Long id)throws Exception;

	 List<Role>	getRoleListByMap(Map<String, Object> param)throws Exception;

	 Integer getRoleCountByMap(Map<String, Object> param)throws Exception;

	 Integer insertRole(Role role)throws Exception;

	 Integer updateRole(Role role)throws Exception;

	 Integer deleteRoleById(@Param(value = "id") Long id)throws Exception;

	 Integer batchDeleteRole(Map<String, List<String>> params);

}
