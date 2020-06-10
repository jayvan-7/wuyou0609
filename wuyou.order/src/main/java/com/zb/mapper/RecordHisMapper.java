package com.zb.mapper;

import com.zb.pojo.RecordHis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RecordHisMapper {

	public RecordHis getRecordHisById(@Param(value = "id") Long id)throws Exception;

	public List<RecordHis>	getRecordHisListByMap(Map<String, Object> param)throws Exception;

	public Integer getRecordHisCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertRecordHis(RecordHis recordHis)throws Exception;

	public Integer updateRecordHis(RecordHis recordHis)throws Exception;

	public Integer deleteRecordHisById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeleteRecordHis(Map<String, List<String>> params);

}
