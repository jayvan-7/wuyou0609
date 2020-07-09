package com.zb.mapper;

import com.zb.pojo.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminUserMapper {

	public AdminUser getAdminUserById(@Param(value = "id") Long id)throws Exception;

	public List<AdminUser>	getAdminUserListByMap(Map<String, Object> param)throws Exception;

	public Integer getAdminUserCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertAdminUser(AdminUser adminUser)throws Exception;

	public Integer updateAdminUser(AdminUser adminUser)throws Exception;

	public Integer deleteAdminUserById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeleteAdminUser(Map<String, List<String>> params);

	 AdminUser loginAdminUser(@Param("name") String name,@Param("pwd") String pwd );

}
