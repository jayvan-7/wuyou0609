package com.zb.mapper;

import com.zb.pojo.IndividualUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface IndividualUserMapper {

	public IndividualUser getIndividualUserById(@Param(value = "id") Integer id)throws Exception;

	public List<IndividualUser>	getIndividualUserListByMap(Map<String, Object> param)throws Exception;

	public Integer getIndividualUserCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertIndividualUser(IndividualUser individualUser)throws Exception;

	public Integer updateIndividualUser(IndividualUser individualUser)throws Exception;

	public Integer deleteIndividualUserById(@Param(value = "id") Integer id)throws Exception;

	public Integer batchDeleteIndividualUser(Map<String, List<String>> params);
	 IndividualUser search(Map<String, Object> param)throws Exception;

	 IndividualUser phoneLogin(@Param(value = "userphone") String  userphone)throws Exception;




}
