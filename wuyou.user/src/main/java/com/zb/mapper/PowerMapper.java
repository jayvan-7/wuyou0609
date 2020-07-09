package com.zb.mapper;

import com.zb.pojo.Power;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PowerMapper {

	public Power getPowerById(@Param(value = "id") Long id)throws Exception;

	public List<Power>	getPowerListByMap(Map<String, Object> param)throws Exception;

	public Integer getPowerCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertPower(Power power)throws Exception;

	public Integer updatePower(Power power)throws Exception;

	public Integer deletePowerById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeletePower(Map<String, List<String>> params);

	public List<Power>	getPowerAll(@Param(value = "id") Integer id)throws Exception;
}
