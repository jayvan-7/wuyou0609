package com.zb.mapper;

import com.zb.pojo.EnterpriseUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface EnterpriseUserMapper {

	public EnterpriseUser getEnterpriseUserById(@Param(value = "id") Integer id)throws Exception;

	public List<EnterpriseUser>	getEnterpriseUserListByMap(Map<String, Object> param)throws Exception;

	public Integer getEnterpriseUserCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertEnterpriseUser(EnterpriseUser enterpriseUser)throws Exception;

	public Integer updateEnterpriseUser(EnterpriseUser enterpriseUser)throws Exception;

	public Integer deleteEnterpriseUserById(@Param(value = "id") Integer id)throws Exception;

	public Integer batchDeleteEnterpriseUser(Map<String, List<String>> params);
	EnterpriseUser search(Map<String, Object> param)throws Exception;
	EnterpriseUser phoneLogin(@Param(value = "userphone") String  userphone)throws Exception;

}
